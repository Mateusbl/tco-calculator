package com.mateus.tcocalculator.adapter.out.fipe;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

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

}
