package com.mateus.tcocalculator.adapter.out.licensing;

import java.math.BigDecimal;
import java.util.Map;

import com.mateus.tcocalculator.domain.BrazilianState;
import com.mateus.tcocalculator.domain.port.out.LicensingFeePort;

public class StaticLicensingAdapter implements LicensingFeePort {

    private static final Map<BrazilianState, BigDecimal> FEES_BY_STATE = Map.ofEntries(
        Map.entry(BrazilianState.AC, BigDecimal.valueOf(200.25)),
        Map.entry(BrazilianState.AL, BigDecimal.valueOf(36.03)),
        Map.entry(BrazilianState.AP, BigDecimal.valueOf(128.54)),
        Map.entry(BrazilianState.AM, BigDecimal.valueOf(122.88)),
        Map.entry(BrazilianState.BA, BigDecimal.valueOf(173.50)),
        Map.entry(BrazilianState.CE, BigDecimal.valueOf(150.00)),
        Map.entry(BrazilianState.DF, BigDecimal.valueOf(102.00)),
        Map.entry(BrazilianState.ES, BigDecimal.valueOf(100.00)),
        Map.entry(BrazilianState.GO, BigDecimal.valueOf(251.25)),
        Map.entry(BrazilianState.MA, BigDecimal.valueOf(100.00)),
        Map.entry(BrazilianState.MT, BigDecimal.valueOf(140.00)),
        Map.entry(BrazilianState.MS, BigDecimal.valueOf(235.28)),
        Map.entry(BrazilianState.MG, BigDecimal.valueOf(35.62)),
        Map.entry(BrazilianState.PA, BigDecimal.valueOf(288.08)),
        Map.entry(BrazilianState.PB, BigDecimal.valueOf(206.55)),
        Map.entry(BrazilianState.PR, BigDecimal.valueOf(90.94)),
        Map.entry(BrazilianState.PE, BigDecimal.valueOf(150.00)),
        Map.entry(BrazilianState.PI, BigDecimal.valueOf(129.60)),
        Map.entry(BrazilianState.RJ, BigDecimal.valueOf(281.29)),
        Map.entry(BrazilianState.RN, BigDecimal.valueOf(100.00)),
        Map.entry(BrazilianState.RS, BigDecimal.valueOf(109.27)),
        Map.entry(BrazilianState.RO, BigDecimal.valueOf(220.41)),
        Map.entry(BrazilianState.SC, BigDecimal.valueOf(150.00)),
        Map.entry(BrazilianState.SP, BigDecimal.valueOf(174.08)),
        Map.entry(BrazilianState.SE, BigDecimal.valueOf(207.36)),
        Map.entry(BrazilianState.TO, BigDecimal.valueOf(79.63))
    );

    @Override
    public BigDecimal findFee(BrazilianState state) {
        BigDecimal fee = FEES_BY_STATE.get(state);
        if (fee == null) {
            throw new IllegalArgumentException("Estado nao suportado: " + state);
        }
        return fee;
    }
}