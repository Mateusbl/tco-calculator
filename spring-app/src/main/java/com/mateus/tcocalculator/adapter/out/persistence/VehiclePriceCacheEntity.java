package com.mateus.tcocalculator.adapter.out.persistence;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Vehicle_price_cache")

public class VehiclePriceCacheEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer brandCode;
    private Integer modelCode;
    private String yearCode;
    private BigDecimal price;
    private LocalDate fetchedAt;


    public VehiclePriceCacheEntity(){

    }

    public  Long getId(){
        return id;
    }

    public void  setId(Long id ){
        this.id = id;
    }

    public Integer getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(Integer brandCode) {
        this.brandCode = brandCode;
    }

    public Integer getModelCode() {
        return modelCode;
    }

    public void setModelCode(Integer modelCode) {
        this.modelCode = modelCode;
    }

    public String getYearCode() {
        return yearCode;
    }

    public void setYearCode(String yearCode) {
        this.yearCode = yearCode;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getFetchedAt() {
        return fetchedAt;
    }

    public void setFetchedAt(LocalDate fetchedAt) {
        this.fetchedAt = fetchedAt;
    }


}
