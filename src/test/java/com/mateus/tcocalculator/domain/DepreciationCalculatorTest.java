package com.mateus.tcocalculator.domain;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DepreciationCalculatorTest {
    @Test
    @DisplayName("deve calcular a depreciacao")
        void calculate (){
            List<BigDecimal> prices = List.of(
                BigDecimal.valueOf( 80000 ),
                BigDecimal.valueOf( 79000 ),
                BigDecimal.valueOf( 78200 )
            );
            List<Integer> months = List.of(0,1,2);
          
        

    Integer targetMonth = 2;
    
    DepreciationCalculator calculator = new DepreciationCalculator();

    BigDecimal precoProjetado = calculator.calculate(months,prices ,targetMonth );
    
    assertThat(precoProjetado).isCloseTo(BigDecimal.valueOf(78166.67), within(BigDecimal.valueOf(0.01)));
}
    
    

}
