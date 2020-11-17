package com.jos.sbt.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

/**
 * Service.
 *
 */
@Service
@Slf4j
public class MoneyConvertService {

    /**
     * RestTemplate.
     */
    private final RestTemplate restTemplate;

    /**
     * MoneyConvertService.
     * @param restTemplateBuilder - restTemplateBuilder
     */
    public MoneyConvertService(final RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    /**
     * findRate.
     * @param url - url
     * @return CompletableFuture<String>
     */
    @Async
    public CompletableFuture<String> findRate(final String url) {
        log.info("Looking up " + url);
        return CompletableFuture.completedFuture(
                restTemplate.getForObject(url, String.class));
    }

}
