package com.mateus.tcocalculator.adapter.out.fipe;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FipeAdapterTest {

    @Test
    @DisplayName("Test for String conversion logic")
    void clean(){
        String rawprice = "R$ 149.800,00";
        FipeAdapter adapter = new FipeAdapter();
        BigDecimal result = adapter.parsePrice(rawprice);

        assertThat(result).isEqualByComparingTo(BigDecimal.valueOf(149800.00));
    }

}
