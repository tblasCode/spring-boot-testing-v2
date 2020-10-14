package com.jos.sbt.service;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

import com.github.tomakehurst.wiremock.WireMockServer;

@SpringBootTest
public class MoneyConvertServiceTest {

    WireMockServer wireMockServer;

    @BeforeEach
    public void setup () {
        wireMockServer = new WireMockServer(8989);
        
        wireMockServer.start();

        wireMockServer.stubFor(get(urlEqualTo("/api.exchangeratesapi.io/latest"))
                .willReturn(aResponse().withHeader("Content-Type", "text/plain")
                        .withStatus(200)
                        .withBodyFile("json/exchangeratesapi.json")));

        wireMockServer.stubFor(get(urlEqualTo("/api.ratesapi.io/api/latest"))
                .willReturn(aResponse().withHeader("Content-Type", "text/plain")
                        .withStatus(200)
                        .withBodyFile("json/ratesapi.json")));
        
        wireMockServer.stubFor(get(urlEqualTo("/api.frankfurter.app/latest"))
                .willReturn(aResponse().withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBodyFile("json/frankfurter.json")));
    }

    @AfterEach
    public void teardown () {
        wireMockServer.stop();
    }
    @Autowired
    private MoneyConvertService service;

    @Test
    public void get_frankdurt_data_when_call_frankfurt_rate_should_return_data() {
        service.findRate("http://localhost:8989/api.frankfurter.app/latest");
    }
}
