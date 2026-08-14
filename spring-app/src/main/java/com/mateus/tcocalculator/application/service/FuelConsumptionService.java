package com.mateus.tcocalculator.application.service;

import java.io.IOException;
import java.math.BigDecimal;


import com.mateus.tcocalculator.adapter.out.inmetro.InmetroAdapter;
import com.mateus.tcocalculator.domain.port.out.FuelConsumptionPort;

public class FuelConsumptionService implements FuelConsumptionPort {
    private final InmetroAdapter inmetroAdapter;
    public FuelConsumptionService(InmetroAdapter inmetroAdapter){
        this.inmetroAdapter = inmetroAdapter;
    }

    @Override
    public BigDecimal findConsumption(String make, String model){
        try{
            byte[] pdfBytes = inmetroAdapter.downloadPDF();
            String text = inmetroAdapter.extractText(pdfBytes);
            return inmetroAdapter.findConsumption(text, make, model);
        } catch(IOException e){
            throw new RuntimeException("Erro ao processar PDF do Inmetro", e);
        }
    }

}
