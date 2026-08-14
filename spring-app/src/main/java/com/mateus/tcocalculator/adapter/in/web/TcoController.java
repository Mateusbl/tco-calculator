package com.mateus.tcocalculator.adapter.in.web;

import org.springframework.web.bind.annotation.RestController;

import com.mateus.tcocalculator.domain.TcoResult;
import com.mateus.tcocalculator.domain.Vehicle;
import com.mateus.tcocalculator.domain.port.in.BuildVehicleUseCase;
import com.mateus.tcocalculator.domain.port.in.CalculateTcoUseCase;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class TcoController {

    private final BuildVehicleUseCase buildVehicleUseCase;
    private final CalculateTcoUseCase calculateTcoUseCase;

    public TcoController(BuildVehicleUseCase buildVehicleUseCase, CalculateTcoUseCase calculateTcoUseCase){
        this.buildVehicleUseCase = buildVehicleUseCase;
        this.calculateTcoUseCase = calculateTcoUseCase;
    }

    @PostMapping("/tco/calcular")
    public TcoResponse calculate(@RequestBody CalculateTcoRequest request) {
        Vehicle vehicle = buildVehicleUseCase.build(request.brandCode(), request.modelCode(), request.yearCode(), request.state());

        TcoResult result = calculateTcoUseCase.calculate(vehicle, request.kmPerYear(),request.pricePerLiter());

        
        return new TcoResponse(result.annualFuelCost(),result.projectedDepreciation(),result.ipvaCost(),result.licensingCost(),result.total());
    }
    

}
