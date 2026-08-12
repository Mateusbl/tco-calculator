package com.mateus.tcocalculator.domain.port.out;

import java.math.BigDecimal;

import com.mateus.tcocalculator.domain.Vehicle;

public interface IpvaRatePort {
    BigDecimal findRate(Vehicle vehicle, String state);
    

}


