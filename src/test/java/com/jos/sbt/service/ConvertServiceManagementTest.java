package com.jos.sbt.service;


import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.concurrent.ExecutionException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.tomakehurst.wiremock.WireMockServer;

@SpringBootTest
public class ConvertServiceManagementTest {

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
    private ConvertServiceManagement convertServiceManagement;

    @Test
    void given_AllRest_when_call_convert_then_ok() throws Exception {
        //Given -> Setup
        
        //When
        BigDecimal result = convertServiceManagement.convert("USD");
        
        //Then
        assertThat(result).isEqualTo("1.2066");
    }
    
}
