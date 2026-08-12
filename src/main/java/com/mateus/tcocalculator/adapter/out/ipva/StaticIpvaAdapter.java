package com.mateus.tcocalculator.adapter.out.ipva;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

import com.mateus.tcocalculator.domain.FuelType;
import com.mateus.tcocalculator.domain.Vehicle;
import com.mateus.tcocalculator.domain.port.out.IpvaRatePort;

public class StaticIpvaAdapter implements IpvaRatePort {

    private static final Map<String, BigDecimal> RATES_BY_STATE = Map.ofEntries(
        Map.entry("MG", BigDecimal.valueOf(0.04)),
        Map.entry("RJ", BigDecimal.valueOf(0.04)),
        Map.entry("SP", BigDecimal.valueOf(0.04)),
        Map.entry("GO", BigDecimal.valueOf(0.0375)),
        Map.entry("AL", BigDecimal.valueOf(0.03)),
        Map.entry("CE", BigDecimal.valueOf(0.03)),
        Map.entry("DF", BigDecimal.valueOf(0.03)),
        Map.entry("MT", BigDecimal.valueOf(0.03)),
        Map.entry("MS", BigDecimal.valueOf(0.03)),
        Map.entry("RN", BigDecimal.valueOf(0.03)),
        Map.entry("RS", BigDecimal.valueOf(0.03)),
        Map.entry("RO", BigDecimal.valueOf(0.03)),
        Map.entry("RR", BigDecimal.valueOf(0.03)),
        Map.entry("BA", BigDecimal.valueOf(0.025)),
        Map.entry("MA", BigDecimal.valueOf(0.025)),
        Map.entry("PA", BigDecimal.valueOf(0.025)),
        Map.entry("PB", BigDecimal.valueOf(0.025)),
        Map.entry("PE", BigDecimal.valueOf(0.024)),
        Map.entry("AC", BigDecimal.valueOf(0.02)),
        Map.entry("AM", BigDecimal.valueOf(0.02)),
        Map.entry("ES", BigDecimal.valueOf(0.02)),
        Map.entry("SC", BigDecimal.valueOf(0.02)),
        Map.entry("PR", BigDecimal.valueOf(0.019))
    );


    private BigDecimal findBaseRate(String state){
        BigDecimal rate = RATES_BY_STATE.get(state);
        if (rate==null){
            throw new IllegalArgumentException("Estado nao suprtado: " +state);
        }
        return rate;
    }


private BigDecimal applyFuelTypeAdjustment(BigDecimal baseRate, FuelType fuelType) {
    // TODO: regras reais de IPVA para elétricos/híbridos variam por estado
    // e podem depender de preço do veículo, local de fabricação, tempo de posse
    // e specs técnicas do motor (ex: SP exige motor >=40kW e >=150V, teto de R$250mil).
    // Esta é uma aproximação simplificada, não a regra fiscal completa.
    return switch (fuelType) {
        case ELECTRIC -> BigDecimal.ZERO;
        case HYBRID -> baseRate.divide(BigDecimal.valueOf(2), 4, RoundingMode.HALF_UP);
        default -> baseRate;
    };
}


    
    @Override

    public BigDecimal findRate(Vehicle vehicle, String state){
     
        BigDecimal baserate = findBaseRate(state);
        return applyFuelTypeAdjustment(baserate, vehicle.fuelType());

    }

}