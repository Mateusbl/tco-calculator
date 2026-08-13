package com.mateus.tcocalculator.adapter.out.persistence;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.mateus.tcocalculator.adapter.out.fipe.FipeAdapter;

public class CachedVehiclePriceAdapterTest {
    @Test
    @DisplayName("test for the cahed values api when there is a valid cache")
    void shouldUseCachedPriceWhenRecent(){
        FipeAdapter fipeAdapter = mock(FipeAdapter.class);
        VehiclePriceCacheRepository repository = mock(VehiclePriceCacheRepository.class);
        
        VehiclePriceCacheEntity cachedEntity = new VehiclePriceCacheEntity();
        cachedEntity.setPrice(BigDecimal.valueOf(50000));
        cachedEntity.setFetchedAt(LocalDate.now());
        when(repository.findByBrandCodeAndModelCodeAndYearCode(59, 5940, "2022-3")).thenReturn(Optional.of(cachedEntity));

        CachedVehiclePriceAdapter adapter = new CachedVehiclePriceAdapter(fipeAdapter, repository);
        List<BigDecimal> result = adapter.findPriceHistory(59, 5940, List.of("2022-3"));

        assertThat(result).containsExactly(BigDecimal.valueOf(50000));
        verify(fipeAdapter, never()).findPriceHistory(any(),any(),any());

    }
    @Test
    @DisplayName("testa a funcao de fetch caso cahe miss ")
    void shouldFecthfromApiWhenCacheInvalid(){
    FipeAdapter fipeAdapter = mock(FipeAdapter.class);
    VehiclePriceCacheRepository repository = mock(VehiclePriceCacheRepository.class);
    when(repository.findByBrandCodeAndModelCodeAndYearCode(59, 5940, "2022-3")).thenReturn(Optional.empty());
    when(fipeAdapter.findPriceHistory(59, 5940, List.of("2022-3"))).thenReturn(List.of(BigDecimal.valueOf(149800)));

    CachedVehiclePriceAdapter adapter = new CachedVehiclePriceAdapter(fipeAdapter, repository);
    List<BigDecimal> result = adapter.findPriceHistory(59, 5940, List.of("2022-3"));

    assertThat(result).containsExactly(BigDecimal.valueOf(149800));
    verify(repository).save(any(VehiclePriceCacheEntity.class));
    }

}
