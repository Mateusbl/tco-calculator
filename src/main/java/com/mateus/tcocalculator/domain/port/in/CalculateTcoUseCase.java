package com.mateus.tcocalculator.domain.port.in;

import com.mateus.tcocalculator.domain.TcoResult;
import com.mateus.tcocalculator.domain.Vehicle;

public interface CalculateTcoUseCase {
    TcoResult calculate(Vehicle vehicle);

}
