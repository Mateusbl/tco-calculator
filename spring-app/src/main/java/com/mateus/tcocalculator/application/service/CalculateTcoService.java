package com.mateus.tcocalculator.application.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.mateus.tcocalculator.domain.DepreciationCalculator;
import com.mateus.tcocalculator.domain.FuelCostCalculator;
import com.mateus.tcocalculator.domain.TcoResult;
import com.mateus.tcocalculator.domain.Vehicle;
import com.mateus.tcocalculator.domain.port.in.CalculateTcoUseCase;
import com.mateus.tcocalculator.domain.port.out.FuelConsumptionPort;
import com.mateus.tcocalculator.domain.port.out.IpvaRatePort;
import com.mateus.tcocalculator.domain.port.out.LicensingFeePort;
import com.mateus.tcocalculator.domain.port.out.VehiclePricePort;

public class CalculateTcoService implements CalculateTcoUseCase {
    private final FuelCostCalculator fuelCostCalculator;
    private final DepreciationCalculator depreciationCalculator;
    private final VehiclePricePort vehiclePricePort;
    private final IpvaRatePort ipvaRatePort;
    private final LicensingFeePort licensingFeePort;
    private final FuelConsumptionPort fuelConsumptionPort;

    public CalculateTcoService(
        FuelCostCalculator fuelCostCalculator,
        DepreciationCalculator depreciationCalculator,
        VehiclePricePort vehiclePricePort,
        IpvaRatePort ipvaRatePort,
        LicensingFeePort licensingFeePort,
        FuelConsumptionPort fuelConsumptionPort
    ){
        this.fuelCostCalculator = fuelCostCalculator;
        this.depreciationCalculator = depreciationCalculator;
        this.vehiclePricePort = vehiclePricePort;
        this.ipvaRatePort = ipvaRatePort;
        this.licensingFeePort = licensingFeePort;
        this.fuelConsumptionPort = fuelConsumptionPort;
    }

    @Override

    public TcoResult calculate(Vehicle vehicle, BigDecimal kmPerYear, BigDecimal pricePerLiter){

        List<String> yearCodes = vehiclePricePort.findAvailableYears(vehicle.brandCode(), vehicle.modelCode());
        List<BigDecimal> priceHistory = vehiclePricePort.findPriceHistory(vehicle.brandCode(), vehicle.modelCode(), yearCodes);
        List<Integer> months = new ArrayList<>();
for (int i = 0; i < priceHistory.size(); i++) {
    months.add(i);

}

BigDecimal projectedDepreciation = depreciationCalculator.calculate(months, priceHistory, priceHistory.size());
BigDecimal consumption = fuelConsumptionPort.findConsumption(vehicle.make(), vehicle.model());
BigDecimal annualFuelCost = fuelCostCalculator.calculate(kmPerYear, consumption, pricePerLiter);
BigDecimal vehicleValue = priceHistory.get(0);
BigDecimal ipvaRate = ipvaRatePort.findRate(vehicle, vehicle.state());
BigDecimal ipvaCost = vehicleValue.multiply(ipvaRate);BigDecimal licensingCost = licensingFeePort.findFee(vehicle.state());

    

return new TcoResult(annualFuelCost,projectedDepreciation,ipvaCost,licensingCost);

    

}
}