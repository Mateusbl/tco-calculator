package com.mateus.tcocalculator.domain;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FuelCostCalculatorTest {

    @Test
    @DisplayName("Deve calcular o custo anual de combustível corretamente")
    void deveCalcularCustoAnualDeCombustivel(){
        BigDecimal kmPerYear = BigDecimal.valueOf(12000);
        BigDecimal consumptionKmPerLiter = BigDecimal.valueOf(12);
        BigDecimal pricePerLiter = BigDecimal.valueOf(5.80);
        FuelCostCalculator calculator = new FuelCostCalculator();

        BigDecimal annualCost = calculator.calculate(
            kmPerYear,
            consumptionKmPerLiter,
            pricePerLiter
        );

        assertThat(annualCost).isEqualByComparingTo(BigDecimal.valueOf(5800.00));
    }

    @Test
    @DisplayName("Deve calcular sem lançar exceção quando a divisão não é exata")
    void deveCalcularCustoAnualQuandoDivisaoNaoEExata(){
        BigDecimal kmRodadosAno = BigDecimal.valueOf(12000);
        BigDecimal consumoKmporLitro = BigDecimal.valueOf(13);
        BigDecimal precoPorLitro = BigDecimal.valueOf(5.80);
        FuelCostCalculator calculator = new FuelCostCalculator();

        BigDecimal custoAnual = calculator.calculate(
            kmRodadosAno,
            consumoKmporLitro,
            precoPorLitro
        );

        assertThat(custoAnual).isNotNull().isGreaterThan(BigDecimal.ZERO);
    }
}