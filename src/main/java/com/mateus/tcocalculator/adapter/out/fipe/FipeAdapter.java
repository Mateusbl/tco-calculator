package com.mateus.tcocalculator.adapter.out.fipe;

import java.math.BigDecimal;

public class FipeAdapter {
    public BigDecimal parsePrice(String rawPrice){
        String clean = rawPrice.replace("R$ ", "").replace(".", "").replace(",", ".");
        return new BigDecimal(clean);
    }

}
