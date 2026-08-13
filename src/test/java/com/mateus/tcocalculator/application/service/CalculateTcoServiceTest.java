package com.mateus.tcocalculator.application.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.mateus.tcocalculator.domain.BrazilianState;
import com.mateus.tcocalculator.domain.DepreciationCalculator;
import com.mateus.tcocalculator.domain.FuelCostCalculator;
import com.mateus.tcocalculator.domain.FuelType;
import com.mateus.tcocalculator.domain.TcoResult;
import com.mateus.tcocalculator.domain.Vehicle;
import com.mateus.tcocalculator.domain.port.out.IpvaRatePort;
import com.mateus.tcocalculator.domain.port.out.LicensingFeePort;
import com.mateus.tcocalculator.domain.port.out.VehiclePricePort;

public class CalculateTcoServiceTest {
    @Test
    @DisplayName("Calculado de tco completo com todso os custos ")
    void shouldCalculateFullTco(){
        FuelCostCalculator fuelCostCalculator = mock(FuelCostCalculator.class);
        DepreciationCalculator depreciationCalculator = mock(DepreciationCalculator.class);
        VehiclePricePort vehiclePricePort = mock(VehiclePricePort.class);
        IpvaRatePort ipvaRatePort = mock(IpvaRatePort.class);
        LicensingFeePort licensingFeePort = mock(LicensingFeePort.class);
        
        Vehicle vehicle = new Vehicle("Toyota","Etios",2023,BigDecimal.valueOf(12.5),"001004-9",FuelType.GASOLINE,59,5940,BrazilianState.SP);
        
        when(vehiclePricePort.findAvailableYears(59,5940)).thenReturn(List.of("2022-3", "2021-3", "2020-3"));

        when(vehiclePricePort.findPriceHistory(59,5940,List.of("2022-3", "2021-3", "2020-3"))).thenReturn(List.of(BigDecimal.valueOf(80000),BigDecimal.valueOf(79000),BigDecimal.valueOf(78200)));
        
        when(depreciationCalculator.calculate(any(), any(), anyInt())).thenReturn(BigDecimal.valueOf(75000));
        when(fuelCostCalculator.calculate(any(), any(), any())).thenReturn(BigDecimal.valueOf(75000));
        when(ipvaRatePort.findRate(vehicle, BrazilianState.SP)).thenReturn(BigDecimal.valueOf(0.04));
        when(licensingFeePort.findFee(BrazilianState.SP)).thenReturn(BigDecimal.valueOf(174.08));

        CalculateTcoService service = new CalculateTcoService(fuelCostCalculator, depreciationCalculator, vehiclePricePort, ipvaRatePort, licensingFeePort);
        TcoResult result = service.calculate(vehicle, BigDecimal.valueOf(12000), BigDecimal.valueOf(5.80));

        assertThat(result.total()).isEqualByComparingTo(BigDecimal.valueOf(153374.08));
    }

}
