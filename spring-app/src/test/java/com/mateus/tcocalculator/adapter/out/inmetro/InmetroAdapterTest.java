package com.mateus.tcocalculator.adapter.out.inmetro;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestClient;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

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

}
