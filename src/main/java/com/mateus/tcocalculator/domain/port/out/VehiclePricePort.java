package com.mateus.tcocalculator.domain.port.out;

import java.math.BigDecimal;
import java.util.List;

public interface VehiclePricePort {
    List<BigDecimal> findPriceHistory(Integer brandCode, Integer modelCode, List<String> yearCodes);
    List<String> findAvailableYears(Integer brandCode, Integer modelCode);
    

}
