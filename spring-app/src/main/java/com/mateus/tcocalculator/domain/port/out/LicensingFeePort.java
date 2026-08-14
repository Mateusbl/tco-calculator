package com.mateus.tcocalculator.domain.port.out;

import java.math.BigDecimal;

import com.mateus.tcocalculator.domain.BrazilianState;

public interface LicensingFeePort {
    BigDecimal findFee(BrazilianState state);
}