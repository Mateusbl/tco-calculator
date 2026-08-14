package com.mateus.tcocalculator.application.service;

import java.math.BigDecimal;

import com.mateus.tcocalculator.domain.BrazilianState;
import com.mateus.tcocalculator.domain.Vehicle;
import com.mateus.tcocalculator.domain.VehicleDetails;
import com.mateus.tcocalculator.domain.VehicleFactory;
import com.mateus.tcocalculator.domain.port.in.BuildVehicleUseCase;
import com.mateus.tcocalculator.domain.port.out.FuelConsumptionPort;
import com.mateus.tcocalculator.domain.port.out.VehiclePricePort;

public class BuildVehicleService implements BuildVehicleUseCase{

    private final VehiclePricePort vehiclePricePort;
    private final FuelConsumptionPort fuelConsumptionPort;
    private final VehicleFactory vehicleFactory;

    public BuildVehicleService(VehiclePricePort vehiclePricePort, FuelConsumptionPort fuelConsumptionPort ,VehicleFactory vehicleFactory){
        this.vehiclePricePort = vehiclePricePort;
        this.fuelConsumptionPort = fuelConsumptionPort;
        this.vehicleFactory = vehicleFactory;
    }

    @Override
    public Vehicle build(Integer brandCode, Integer modelCode, String yearCode, BrazilianState state){
        VehicleDetails details = vehiclePricePort.findVehicleDetails(brandCode, modelCode, yearCode);
        BigDecimal consumption = fuelConsumptionPort.findConsumption(details.make(),details.model());
        return vehicleFactory.create(details, brandCode, modelCode, state );
    }

}
