package com.mateus.tcocalculator.adapter.out.licensing;

import java.math.BigDecimal;
import java.util.Map;

import com.mateus.tcocalculator.domain.port.out.LicensingFeePort;

public class StaticLicensingAdapter implements LicensingFeePort {

    private static final Map<String, BigDecimal> FEES_BY_STATE = Map.ofEntries(
        Map.entry("AC", BigDecimal.valueOf(200.25)),
        Map.entry("AL", BigDecimal.valueOf(36.03)),
        Map.entry("AP", BigDecimal.valueOf(128.54)),
        Map.entry("AM", BigDecimal.valueOf(122.88)),
        Map.entry("BA", BigDecimal.valueOf(173.50)),
        Map.entry("CE", BigDecimal.valueOf(150.00)),  // faixa "intermediária", sem valor exato divulgado
        Map.entry("DF", BigDecimal.valueOf(102.00)),
        Map.entry("ES", BigDecimal.valueOf(100.00)),  // "valores moderados", sem valor exato divulgado
        Map.entry("GO", BigDecimal.valueOf(251.25)),
        Map.entry("MA", BigDecimal.valueOf(100.00)),  // "valor intermediário", sem valor exato divulgado
        Map.entry("MT", BigDecimal.valueOf(140.00)),
        Map.entry("MS", BigDecimal.valueOf(235.28)),
        Map.entry("MG", BigDecimal.valueOf(35.62)),
        Map.entry("PA", BigDecimal.valueOf(288.08)),
        Map.entry("PB", BigDecimal.valueOf(206.55)),
        Map.entry("PR", BigDecimal.valueOf(90.94)),
        Map.entry("PE", BigDecimal.valueOf(150.00)),  // sem valor exato divulgado
        Map.entry("PI", BigDecimal.valueOf(129.60)),
        Map.entry("RJ", BigDecimal.valueOf(281.29)),
        Map.entry("RN", BigDecimal.valueOf(100.00)),  // sem valor exato divulgado
        Map.entry("RS", BigDecimal.valueOf(109.27)),
        Map.entry("RO", BigDecimal.valueOf(220.41)),
        Map.entry("SC", BigDecimal.valueOf(150.00)),  // sem valor exato divulgado
        Map.entry("SP", BigDecimal.valueOf(174.08)),
        Map.entry("SE", BigDecimal.valueOf(207.36)),
        Map.entry("TO", BigDecimal.valueOf(79.63))
    );


    @Override
    public BigDecimal findFee(String state) {
        BigDecimal fee = FEES_BY_STATE.get(state);
        if (fee == null) {
            throw new IllegalArgumentException("Estado nao suportado: " + state);
        }
        return fee;
    }
}