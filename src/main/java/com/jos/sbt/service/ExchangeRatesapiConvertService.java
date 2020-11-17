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
public class ExchangeRatesapiConvertService implements ConvertService {
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
     * @param urlService - url
     */
    public ExchangeRatesapiConvertService(
            final RestTemplateBuilder restTemplateBuilder,
            @Value(
                    "${api.rate.exchangeratesapi:"
                    + "http://api.exchangeratesapi.io/latest}"
                    ) final String urlService) {
        this.restTemplate = restTemplateBuilder.build();
        this.url = urlService;
    }
    /**
     * findRate.
     * @param target - restTemplateBuilder
     * @return rate
     */
    public final CompletableFuture<BigDecimal> findRate(final String target) {
        log.info("find rate ExchangeRatesapiConvertService.");
        return CompletableFuture.completedFuture(restTemplate
                .getForObject(url, RateResponse.class).getRates().get(target));
    }
}
