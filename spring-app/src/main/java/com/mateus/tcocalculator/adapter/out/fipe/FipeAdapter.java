package com.mateus.tcocalculator.adapter.out.fipe;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

import com.mateus.tcocalculator.domain.FuelType;
import com.mateus.tcocalculator.domain.VehicleDetails;
import com.mateus.tcocalculator.domain.port.out.VehiclePricePort;

public class FipeAdapter implements VehiclePricePort {

    private final RestClient restClient;
    public FipeAdapter(RestClient restClient){
        this.restClient = restClient;
    }

    public BigDecimal parsePrice(String rawPrice) {
        String clean = rawPrice.replace("R$ ", "").replace(".", "").replace(",", ".");
        return new BigDecimal(clean);
    }

    @Override
    public List<BigDecimal> findPriceHistory(Integer brandCode, Integer modelCode, List<String> yearCodes) {
        List<BigDecimal> prices = new ArrayList<>();

        for (String yearCode : yearCodes) {
            FipeVehicleResponse response = restClient.get()
                    .uri("/cars/brands/{brandCode}/models/{modelCode}/years/{yearCode}", brandCode, modelCode, yearCode)
                    .retrieve()
                    .body(FipeVehicleResponse.class);

            String rawPrice = response.price();

            BigDecimal price = parsePrice(rawPrice);

            prices.add(price);
        }

        return prices;
    }


    @Override
    public List<String> findAvailableYears(Integer brandCode, Integer modelCode){
        List<FipeYearResponse> yearResponses = restClient.get().uri("/cars/brands/{brandCode}/models/{modelCode}/years",brandCode,modelCode)
        .retrieve()
        .body(new ParameterizedTypeReference<List<FipeYearResponse>>() {});


        List<String> yearCodes = new ArrayList<>();
        for (FipeYearResponse yearResponse : yearResponses ){
            yearCodes.add(yearResponse.code());
        }

        return yearCodes;

    }

    @Override
    public VehicleDetails findVehicleDetails(Integer brandCode, Integer modelCode, String yearCode){
        FipeVehicleResponse response = restClient.get().uri("/cars/brands/{brandCode}/models/{modelCode}/years/{yearCode}",brandCode, modelCode, yearCode)
        .retrieve()
        .body(FipeVehicleResponse.class);

        FuelType fuelType = mapFuelType(response.fuel());

        return new VehicleDetails(response.brand(), response.model(),response.modelYear(),response.codeFipe(),fuelType);

    }

    private FuelType mapFuelType(String fuel) {
        return switch (fuel) {
            case "Gasolina" -> FuelType.GASOLINE;
            case "Álcool", "Etanol" -> FuelType.ETHANOL;
            case "Diesel" -> FuelType.DIESEL;
            case "Flex" -> FuelType.FLEX;
            default -> throw new IllegalArgumentException("Tipo de combustivel desconhecido: " + fuel);
        };
    }

}
