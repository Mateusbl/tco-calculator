package com.mateus.tcocalculator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

import com.mateus.tcocalculator.adapter.out.fipe.FipeAdapter;
import com.mateus.tcocalculator.adapter.out.inmetro.InmetroAdapter;
import com.mateus.tcocalculator.adapter.out.ipva.StaticIpvaAdapter;
import com.mateus.tcocalculator.adapter.out.licensing.StaticLicensingAdapter;
import com.mateus.tcocalculator.adapter.out.persistence.CachedVehiclePriceAdapter;
import com.mateus.tcocalculator.adapter.out.persistence.VehiclePriceCacheRepository;
import com.mateus.tcocalculator.application.service.BuildVehicleService;
import com.mateus.tcocalculator.application.service.CalculateTcoService;
import com.mateus.tcocalculator.application.service.FuelConsumptionService;
import com.mateus.tcocalculator.domain.DepreciationCalculator;
import com.mateus.tcocalculator.domain.FuelCostCalculator;
import com.mateus.tcocalculator.domain.VehicleFactory;
import com.mateus.tcocalculator.domain.port.in.BuildVehicleUseCase;
import com.mateus.tcocalculator.domain.port.in.CalculateTcoUseCase;
import com.mateus.tcocalculator.domain.port.out.FuelConsumptionPort;
import com.mateus.tcocalculator.domain.port.out.IpvaRatePort;
import com.mateus.tcocalculator.domain.port.out.LicensingFeePort;
import com.mateus.tcocalculator.domain.port.out.VehiclePricePort;

@Configuration
public class BeanConfig {

  



    @Bean
    public RestClient fipeRestClient() {
        return RestClient.create("https://fipe.parallelum.com.br/api/v2");
    }

    @Bean
    public FipeAdapter fipeAdapter(RestClient fipeRestClient) {
        return new FipeAdapter(fipeRestClient);
    }

    @Bean
    public VehiclePricePort vehiclePricePort(FipeAdapter fipeAdapter, VehiclePriceCacheRepository repository) {
        return new CachedVehiclePriceAdapter(fipeAdapter, repository);
    }

    @Bean
    public IpvaRatePort ipvaRatePort() {
        return new StaticIpvaAdapter();
    }

    @Bean
    public LicensingFeePort licensingFeePort() {
        return new StaticLicensingAdapter();
    }

    @Bean
    public FuelCostCalculator fuelCostCalculator() {
        return new FuelCostCalculator();
    }

    @Bean
    public DepreciationCalculator depreciationCalculator() {
        return new DepreciationCalculator();
    }

    @Bean
    public VehicleFactory vehicleFactory() {
        return new VehicleFactory();
    }

    @Bean
    public BuildVehicleUseCase buildVehicleUseCase(VehiclePricePort vehiclePricePort,FuelConsumptionPort fuelConsumptionPort , VehicleFactory vehicleFactory) {
        return new BuildVehicleService(vehiclePricePort, fuelConsumptionPort ,vehicleFactory);
    }

    @Bean
    public CalculateTcoUseCase calculateTcoUseCase(
        FuelCostCalculator fuelCostCalculator,
        DepreciationCalculator depreciationCalculator,
        VehiclePricePort vehiclePricePort,
        IpvaRatePort ipvaRatePort,
        LicensingFeePort licensingFeePort,
        FuelConsumptionPort fuelConsumptionPort
    ) {
        return new CalculateTcoService(fuelCostCalculator, depreciationCalculator, vehiclePricePort, ipvaRatePort, licensingFeePort , fuelConsumptionPort);
    }

    @Bean
    public InmetroAdapter inmetroAdapter(RestClient restClient){
        return new InmetroAdapter(restClient);
    }

    @Bean 
    public FuelConsumptionPort fuelConsumptionPort(InmetroAdapter inmetroAdapter){
        return new FuelConsumptionService(inmetroAdapter);
    }
}