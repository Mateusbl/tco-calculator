package com.mateus.tcocalculator.adapter.out.ipva;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.mateus.tcocalculator.domain.BrazilianState;
import com.mateus.tcocalculator.domain.FuelType;
import com.mateus.tcocalculator.domain.Vehicle;

public class StaticIpvaAdapterTest {

    @Test
    @DisplayName("deve calular o ipva de carro a gasolina normal ")

    void calculateGasIpva(){
        Vehicle vehicle = new Vehicle("Toyota","etios",2023, BigDecimal.valueOf(12.5),"001004-9", FuelType.GASOLINE , 59, 5940, BrazilianState.RJ );
        StaticIpvaAdapter adapter = new StaticIpvaAdapter();
        BigDecimal rate = adapter.findRate(vehicle, BrazilianState.SP);
        assertThat(rate).isEqualByComparingTo(BigDecimal.valueOf(0.04));
    }

    @Test
    @DisplayName("calculo de ipva carro eletrico como 0")

    void calculateEletricIpva(){
            Vehicle vehicle = new Vehicle("Tesla", "Model 3", 2023, BigDecimal.valueOf(0), "999999-9", FuelType.ELECTRIC , 59,5940,BrazilianState.RJ);
            StaticIpvaAdapter adapter = new StaticIpvaAdapter();
            BigDecimal rate = adapter.findRate(vehicle, BrazilianState.SP);
            assertThat(rate).isEqualByComparingTo(BigDecimal.ZERO);
    }


    @Test
    @DisplayName("calculo de ipva carro Hibrido como metade")

    void calculateHybridIpva(){
            Vehicle vehicle = new Vehicle("Toyota", "Corolla", 2023, BigDecimal.valueOf(0), "999999-9", FuelType.HYBRID,59,5940,BrazilianState.RJ);
            StaticIpvaAdapter adapter = new StaticIpvaAdapter();
            BigDecimal rate = adapter.findRate(vehicle, BrazilianState.SP);
            assertThat(rate).isEqualByComparingTo(BigDecimal.valueOf(0.02));
    };
    

    @Test
@DisplayName("deve lancar excecao quando estado nao e suportado")
void shouldThrowWhenStateNotSupported(){
    Vehicle vehicle = new Vehicle("Toyota", "Etios", 2023, BigDecimal.valueOf(12.5), "001004-9", FuelType.GASOLINE,59,5940,BrazilianState.SE);
    StaticIpvaAdapter adapter = new StaticIpvaAdapter();

    assertThatThrownBy(() -> adapter.findRate(vehicle, BrazilianState.TO))
        .isInstanceOf(IllegalArgumentException.class);
}   

    


}
