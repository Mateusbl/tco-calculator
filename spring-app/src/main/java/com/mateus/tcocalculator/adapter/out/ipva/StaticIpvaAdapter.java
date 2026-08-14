package com.mateus.tcocalculator.adapter.out.ipva;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

import com.mateus.tcocalculator.domain.BrazilianState;
import com.mateus.tcocalculator.domain.FuelType;
import com.mateus.tcocalculator.domain.Vehicle;
import com.mateus.tcocalculator.domain.port.out.IpvaRatePort;

public class StaticIpvaAdapter implements IpvaRatePort {

    private static final Map<BrazilianState, BigDecimal> RATES_BY_STATE = Map.ofEntries(
        Map.entry(BrazilianState.MG, BigDecimal.valueOf(0.04)),
        Map.entry(BrazilianState.RJ, BigDecimal.valueOf(0.04)),
        Map.entry(BrazilianState.SP, BigDecimal.valueOf(0.04)),
        Map.entry(BrazilianState.GO, BigDecimal.valueOf(0.0375)),
        Map.entry(BrazilianState.AL, BigDecimal.valueOf(0.03)),
        Map.entry(BrazilianState.CE, BigDecimal.valueOf(0.03)),
        Map.entry(BrazilianState.DF, BigDecimal.valueOf(0.03)),
        Map.entry(BrazilianState.MT, BigDecimal.valueOf(0.03)),
        Map.entry(BrazilianState.MS, BigDecimal.valueOf(0.03)),
        Map.entry(BrazilianState.RN, BigDecimal.valueOf(0.03)),
        Map.entry(BrazilianState.RS, BigDecimal.valueOf(0.03)),
        Map.entry(BrazilianState.RO, BigDecimal.valueOf(0.03)),
        Map.entry(BrazilianState.RR, BigDecimal.valueOf(0.03)),
        Map.entry(BrazilianState.BA, BigDecimal.valueOf(0.025)),
        Map.entry(BrazilianState.MA, BigDecimal.valueOf(0.025)),
        Map.entry(BrazilianState.PA, BigDecimal.valueOf(0.025)),
        Map.entry(BrazilianState.PB, BigDecimal.valueOf(0.025)),
        Map.entry(BrazilianState.PE, BigDecimal.valueOf(0.024)),
        Map.entry(BrazilianState.AC, BigDecimal.valueOf(0.02)),
        Map.entry(BrazilianState.AM, BigDecimal.valueOf(0.02)),
        Map.entry(BrazilianState.ES, BigDecimal.valueOf(0.02)),
        Map.entry(BrazilianState.SC, BigDecimal.valueOf(0.02)),
        Map.entry(BrazilianState.PR, BigDecimal.valueOf(0.019))
    );

    private BigDecimal findBaseRate(BrazilianState state){
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

    public BigDecimal findRate(Vehicle vehicle, BrazilianState state ){
     
        BigDecimal baserate = findBaseRate(state);
        return applyFuelTypeAdjustment(baserate, vehicle.fuelType());

    }

}