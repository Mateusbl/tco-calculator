package com.mateus.tcocalculator.adapter.in.web;

import java.math.BigDecimal;

public record TcoResponse(
    BigDecimal annualFuelCost,
    BigDecimal projectedDepreciation,
    BigDecimal ipvaCost,
    BigDecimal licensingCost,
    BigDecimal total
) {

}
