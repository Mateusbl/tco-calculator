package com.mateus.tcocalculator.adapter.out.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VehiclePriceCacheRepository extends JpaRepository<VehiclePriceCacheEntity, Long> {

    Optional<VehiclePriceCacheEntity> findByBrandCodeAndModelCodeAndYearCode(
        Integer brandCode, Integer modelCode, String yearCode
    );
}