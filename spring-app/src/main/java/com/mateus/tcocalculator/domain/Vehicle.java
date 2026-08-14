package com.mateus.tcocalculator.domain;

import java.math.BigDecimal;

public record Vehicle(String make, String model, Integer year, String fipeCode, FuelType fuelType, Integer brandCode, Integer modelCode, BrazilianState state) {

}
