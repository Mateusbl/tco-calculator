package com.mateus.tcocalculator.adapter.in.web;

import java.math.BigDecimal;

import com.mateus.tcocalculator.domain.BrazilianState;

public record CalculateTcoRequest(
        Integer brandCode,
    Integer modelCode,
    String yearCode,
    BrazilianState state,
    BigDecimal inmetroConsumption,
    BigDecimal kmPerYear,
    BigDecimal pricePerLiter
) {

}
