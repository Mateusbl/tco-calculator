package com.mateus.tcocalculator.domain.port.out;

import java.math.BigDecimal;
import java.util.List;

import com.mateus.tcocalculator.domain.VehicleDetails;

public interface VehiclePricePort {
    List<BigDecimal> findPriceHistory(Integer brandCode, Integer modelCode, List<String> yearCodes);
    List<String> findAvailableYears(Integer brandCode, Integer modelCode);
    VehicleDetails findVehicleDetails(Integer brandCode, Integer modelCode, String yearCode);
    

}
