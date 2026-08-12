package com.mateus.tcocalculator.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TcoResultTest {
    @Test
    @DisplayName("calcula a soma das perdas de valor do carro ")
    void calculate(){
        BigDecimal annualFuelCost = BigDecimal.valueOf(1000);
        BigDecimal projectedDepreciation = BigDecimal.valueOf(40000);
        BigDecimal ipvaCost = BigDecimal.valueOf(1500);
        BigDecimal licensingCost = BigDecimal.valueOf(500);

        TcoResult result = new TcoResult(annualFuelCost,projectedDepreciation,ipvaCost,licensingCost);
        BigDecimal total = result.total();

        assertThat(total).isEqualByComparingTo(BigDecimal.valueOf(43000));

    }



}
