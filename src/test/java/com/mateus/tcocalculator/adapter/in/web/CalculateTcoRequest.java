package com.mateus.tcocalculator.adapter.in.web;

import java.math.BigDecimal;

import com.mateus.tcocalculator.domain.BrazilianState;
import com.mateus.tcocalculator.domain.FuelType;

public record CalculateTcoRequest( String make,
    String model,
    Integer year,
    BigDecimal inmetroConsumption,
    String fipeCode,
    FuelType fuelType,
    Integer brandCode,
    Integer modelCode,
    BrazilianState state,
    BigDecimal kmPerYear,
    BigDecimal pricePerLiter) {

}
