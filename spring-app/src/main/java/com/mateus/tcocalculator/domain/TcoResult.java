package com.mateus.tcocalculator.domain;

import java.math.BigDecimal;

public record TcoResult(
    BigDecimal annualFuelCost,
    BigDecimal projectedDepreciation,
    BigDecimal ipvaCost,
    BigDecimal licensingCost

) {
    public BigDecimal total(){
        return annualFuelCost.add(projectedDepreciation).add(ipvaCost).add(licensingCost);
    }


}
