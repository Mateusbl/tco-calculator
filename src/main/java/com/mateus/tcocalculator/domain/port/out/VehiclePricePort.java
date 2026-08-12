package com.mateus.tcocalculator.domain.port.out;

import java.math.BigDecimal;
import java.util.List;

public interface VehiclePricePort {
    List<BigDecimal> findPriceHistory(String brandCode, String modelCode, List<String> yearCodes);

}
