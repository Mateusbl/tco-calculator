package com.mateus.tcocalculator.adapter.out.fipe;

public record FipeVehicleResponse(
    Integer vehicleType,
    String price,
    String brand,
    String model,
    Integer modelYear,
    String fuel,
    String codeFipe,
    String referenceMonth,
    String fuelAcronym
) {

}
