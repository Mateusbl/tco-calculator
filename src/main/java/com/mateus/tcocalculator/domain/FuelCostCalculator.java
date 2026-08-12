package com.mateus.tcocalculator.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FuelCostCalculator {
    public BigDecimal calculate(BigDecimal kmPerYear, BigDecimal consumptionKmPerLiter, BigDecimal pricePerLiter) {
        return kmPerYear.divide(consumptionKmPerLiter, 4, RoundingMode.HALF_UP).multiply(pricePerLiter);
    }

}
