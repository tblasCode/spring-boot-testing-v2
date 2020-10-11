package com.jos.sbt.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
public class MoneyConvertService {

    private static final Logger logger = LoggerFactory.getLogger(MoneyConvertService.class);

    private final RestTemplate restTemplate;

    public MoneyConvertService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Async
    public CompletableFuture<String> findRate(String url){
        logger.info("Looking up " + url);
        String results = restTemplate.getForObject(url, String.class);
        return CompletableFuture.completedFuture(results);
    }

}