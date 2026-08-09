package com.mateus.tcocalculator.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FuelCostCalculator {
    public BigDecimal calcular(BigDecimal kmrodadosAno,BigDecimal consumoKMporLitro,BigDecimal precoPorLitro){
        return kmrodadosAno.divide(consumoKMporLitro, 4, RoundingMode.HALF_UP ).multiply(precoPorLitro);
    }

}
