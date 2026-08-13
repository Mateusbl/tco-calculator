package com.mateus.tcocalculator.adapter.out.persistence;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.mateus.tcocalculator.adapter.out.fipe.FipeAdapter;
import com.mateus.tcocalculator.domain.port.out.VehiclePricePort;

public class CachedVehiclePriceAdapter implements VehiclePricePort {

    private final FipeAdapter fipeAdapter;
    private final VehiclePriceCacheRepository repository;

    public CachedVehiclePriceAdapter(FipeAdapter fipeAdapter, VehiclePriceCacheRepository repository){
        this.fipeAdapter = fipeAdapter;
        this.repository = repository;
    }

    @Override
    public List<String> findAvailableYears(Integer brandCode, Integer modelCode){
        return fipeAdapter.findAvailableYears(brandCode, modelCode);
    }

    @Override
    public List<BigDecimal> findPriceHistory(Integer brandCode, Integer modelCode,List<String> yearCodes ){
        List<BigDecimal> prices = new ArrayList<>();
        for (String yearCode: yearCodes){
            Optional<VehiclePriceCacheEntity> cached = repository.findByBrandCodeAndModelCodeAndYearCode(brandCode,modelCode,yearCode);

            if (cached.isPresent() && isRecent(cached.get())){
                prices.add(cached.get().getPrice());
            }
            else{
                BigDecimal freshPrice = fipeAdapter.findPriceHistory(brandCode, modelCode, List.of(yearCode)).get(0);
                saveToCache(brandCode,modelCode,yearCode,freshPrice);
                prices.add(freshPrice);
            }
        }


        return prices;
    }
    private boolean isRecent(VehiclePriceCacheEntity entity){
        LocalDate age = LocalDate.now().minusDays(30);
        return entity.getFetchedAt().isAfter(age);
    }

    private void saveToCache(Integer brandCode, Integer modelCode,String yearCode, BigDecimal price){
        VehiclePriceCacheEntity entity = new VehiclePriceCacheEntity();
        entity.setBrandCode(brandCode);
        entity.setModelCode(modelCode);
        entity.setYearCode(yearCode);
        entity.setPrice(price);
        entity.setFetchedAt(LocalDate.now());
        repository.save(entity);
    }


}
