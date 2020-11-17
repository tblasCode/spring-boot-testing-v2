package com.jos.sbt.service;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jos.sbt.model.RateResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * Service.
 *
 */
@Service
@Slf4j
public class RatesapiConvertService implements ConvertService {
    /**
     * Url endpoint.
     */
    private final String url;
    /**
     * RestTemplate.
     */
    private final RestTemplate restTemplate;
    /**
     * MoneyConvertService.
     * @param restTemplateBuilder - restTemplateBuilder
     * @param urlService - urlService
     */
    public RatesapiConvertService(
            final RestTemplateBuilder restTemplateBuilder,
            @Value(
                    "${api.rate.ratesapi:"
                    + "http://api.ratesapi.io/api/latest }")
            final String urlService) {
        this.restTemplate = restTemplateBuilder.build();
        this.url = urlService;
    }

    /**
     * findRate.
     * @param target - target
     * @return RateResponse
     */
    public CompletableFuture<BigDecimal> findRate(final String target) {
        log.info("find rate RatesapiConvertService.");
        return CompletableFuture.completedFuture(
                restTemplate.getForObject(url, RateResponse.class)
                .getRates().get(target));
    }

}
