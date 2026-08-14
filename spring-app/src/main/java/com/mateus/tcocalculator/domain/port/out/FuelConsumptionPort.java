package com.mateus.tcocalculator.domain.port.out;

import java.math.BigDecimal;

public interface FuelConsumptionPort {
    BigDecimal findConsumption(String make, String model);

}
