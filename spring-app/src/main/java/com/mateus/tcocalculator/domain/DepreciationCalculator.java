package com.mateus.tcocalculator.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class DepreciationCalculator {
    public BigDecimal calculate(List<Integer> months, List<BigDecimal> prices, Integer targetMonth) {
        int n = months.size();

        BigDecimal sumX = BigDecimal.ZERO;
        BigDecimal sumY = BigDecimal.ZERO;
        BigDecimal sumXY = BigDecimal.ZERO;
        BigDecimal sumX2 = BigDecimal.ZERO;

        for (int i = 0; i < n; i++) {
            BigDecimal x = BigDecimal.valueOf(months.get(i));
            BigDecimal y = prices.get(i);

            sumX = sumX.add(x);
            sumY = sumY.add(y);
            sumXY = sumXY.add(x.multiply(y));
            sumX2 = sumX2.add(x.multiply(x));

        }
        BigDecimal nDecimal = BigDecimal.valueOf(n);
        BigDecimal numerator = nDecimal.multiply(sumXY).subtract(sumX.multiply(sumY));
        BigDecimal denominator = nDecimal.multiply(sumX2).subtract(sumX.multiply(sumX));

        BigDecimal b = numerator.divide(denominator, 6,RoundingMode.HALF_UP );
        BigDecimal a = sumY.subtract(b.multiply(sumX)).divide(nDecimal, 6, RoundingMode.HALF_UP);


        
        BigDecimal result = a.add(b.multiply(BigDecimal.valueOf(targetMonth)));
        return result;
    }

}
