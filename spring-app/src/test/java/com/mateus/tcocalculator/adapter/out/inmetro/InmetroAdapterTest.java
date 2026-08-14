package com.mateus.tcocalculator.adapter.out.inmetro;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestClient;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.math.BigDecimal;

public class InmetroAdapterTest {

    @Test
    @DisplayName("inmetro pdf download test")
    void shouldDownloadPdfSuccessfully(){
        RestClient restClient = RestClient.create();
        InmetroAdapter adapter = new InmetroAdapter(restClient);

        byte[] pdfBytes = adapter.downloadPDF();
        assertThat(pdfBytes).isNotEmpty();
        assertThat(pdfBytes.length).isGreaterThan(1000);

    }

    @Test
@DisplayName("deve extrair texto do PDF baixado")
void shouldExtractTextFromPdf() throws IOException {
    RestClient restClient = RestClient.create();
    InmetroAdapter adapter = new InmetroAdapter(restClient);

    byte[] pdfBytes = adapter.downloadPDF();
    String text = adapter.extractText(pdfBytes);

    assertThat(text).contains("BYD");
    assertThat(text).contains("DOLPHIN");
}

@Test
@DisplayName("deve parsear consumo cidade de uma linha conhecida")
void shouldParseConsumptionFromKnownLine() {
    RestClient restClient = RestClient.create();
    InmetroAdapter adapter = new InmetroAdapter(restClient);

    String line = "Extra Grande Mercedes-Benz CLA200 AMG LINE 1.3-16V Híbrido DCT-7 S E G 33 125 3 B \\ 104 \\ \\ \\ 12.0 14.0 \\ \\ 1.68 \\ C C -";

    BigDecimal result = adapter.parseConsumptionFromLine(line);

    assertThat(result).isEqualByComparingTo(BigDecimal.valueOf(12.0));
}


@Test
@DisplayName("test for real consumptiom from pdf ")
void shouldFindConsumptionForRealVehicle() throws IOException{
    RestClient restClient = RestClient.create();
    InmetroAdapter adapter = new InmetroAdapter(restClient);
    byte[] pdfBytes = adapter.downloadPDF();
    String text = adapter.extractText(pdfBytes);

    BigDecimal result = adapter.findconsumption(text, "Mercedes-Benz", "CLA200");

    assertThat(result).isEqualByComparingTo(BigDecimal.valueOf(12.0));
}

}
