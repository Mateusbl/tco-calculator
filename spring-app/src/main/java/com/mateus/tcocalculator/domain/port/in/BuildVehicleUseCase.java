package com.mateus.tcocalculator.domain.port.in;

import java.math.BigDecimal;

import com.mateus.tcocalculator.domain.BrazilianState;
import com.mateus.tcocalculator.domain.Vehicle;

public interface BuildVehicleUseCase {
    Vehicle build(Integer brandCode, Integer modelCode, String yearCode, BrazilianState state);

}
