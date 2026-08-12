package com.mateus.tcocalculator.domain.port.out;

import java.math.BigDecimal;

public interface LicensingFeePort {
    BigDecimal findFee(String state);
}