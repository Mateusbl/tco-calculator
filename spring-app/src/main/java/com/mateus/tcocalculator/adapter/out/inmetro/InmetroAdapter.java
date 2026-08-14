package com.mateus.tcocalculator.adapter.out.inmetro;

import java.io.IOException;
import java.math.BigDecimal;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.web.client.RestClient;

public class InmetroAdapter {
    private static final String PDF_URL = "https://www.gov.br/inmetro/pt-br/assuntos/regulamentacao/avaliacao-da-conformidade/programa-brasileiro-de-etiquetagem/tabelas-de-eficiencia-energetica/veiculos-automotivos-pbe-veicular/pbe-veicular-2024-1.pdf/@@download/file";
    private final RestClient restClient;
    
    public InmetroAdapter(RestClient restClient){
        this.restClient = restClient;
    }

    public byte [] downloadPDF(){
        return restClient.get().uri(PDF_URL).retrieve().body(byte[].class);
    }

    public String extractText(byte[] pdfBytes) throws IOException{
        var document = Loader.loadPDF(pdfBytes);
        var stripper = new PDFTextStripper();
        String text = stripper.getText(document);
        document.close();
        return text;

    }

    public BigDecimal parseConsumptionFromLine(String line){
        String[] tokens = line.trim().split("\\s+");
        String cidade = tokens[tokens.length - 9];
        return new BigDecimal(cidade.replace(",", "."));
    }

    public BigDecimal findconsumption(String text, String make, String model){
        String[] lines = text.split("\n");
        for (String line : lines ){
            if (line.contains(make) && line.contains(model)){
            return parseConsumptionFromLine(line);
            }
        }
    throw new IllegalArgumentException("Veiculo nao encontrado: "+make+""+model);
    } 

}
