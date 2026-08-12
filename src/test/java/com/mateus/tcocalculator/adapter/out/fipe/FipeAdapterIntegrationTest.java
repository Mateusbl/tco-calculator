package com.mateus.tcocalculator.adapter.out.fipe;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestClient;
public class FipeAdapterIntegrationTest {
    @Test
    @DisplayName("deve buscar historico de precos via API")
    void shouldFetchPriceHistory(){
        RestClient.Builder builder = RestClient.builder().baseUrl("https://fipe.parallelum.com.br/api/v2");
        MockRestServiceServer mockServer = MockRestServiceServer.bindTo(builder).build();
        mockServer.expect(requestTo("https://fipe.parallelum.com.br/api/v2/cars/brands/59/models/5940/years/2022-3")).andRespond(withSuccess("{\"vehicleType\":1,\"price\":\"R$ 149.800,00\",\"brand\":\"VW\",\"model\":\"AMAROK\",\"modelYear\":2022,\"fuel\":\"Diesel\",\"codeFipe\":\"005340-6\",\"referenceMonth\":\"agosto de 2026\",\"fuelAcronym\":\"D\"}",
        MediaType.APPLICATION_JSON));

        RestClient restClient = builder.build();
        FipeAdapter adapter = new FipeAdapter(restClient);

        List<BigDecimal> prices = adapter.findPriceHistory("59", "5940", List.of("2022-3"));

        assertThat(prices).usingComparatorForType(BigDecimal::compareTo,BigDecimal.class).containsExactly(BigDecimal.valueOf(149800.00));
        mockServer.verify();
    }

}
