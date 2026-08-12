package com.mateus.tcocalculator.adapter.out.licensing;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StaticLicensingAdapterTest {

    @Test
    @DisplayName("teste se os estados estao com as fees certas")
    void calculatelicensingfee(){
    StaticLicensingAdapter adapter = new StaticLicensingAdapter();
    BigDecimal fee = adapter.findFee("MG");
    assertThat(fee).isEqualByComparingTo(BigDecimal.valueOf(35.62));
    }



    @Test
    @DisplayName("falha com estado invalido")
    void calculatlicensingfeeexception(){
        StaticLicensingAdapter adapter = new StaticLicensingAdapter();

        assertThatThrownBy(() -> adapter.findFee("XX"))
        .isInstanceOf(IllegalArgumentException.class);
    }
}
