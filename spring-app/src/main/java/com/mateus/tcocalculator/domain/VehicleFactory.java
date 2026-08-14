package com.mateus.tcocalculator.domain;

import java.math.BigDecimal;

public class VehicleFactory {
        public Vehicle create(VehicleDetails details, Integer brandCode, Integer modelCode, BrazilianState state ) {
        return new Vehicle(
            details.make(),
            details.model(),
            details.year(),
            details.fipeCode(),
            details.fuelType(),
            brandCode,
            modelCode,
            state
        );

}
}
