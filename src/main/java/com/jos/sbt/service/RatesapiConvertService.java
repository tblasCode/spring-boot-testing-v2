package com.jos.sbt.service;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Service;

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
     * RestClientService.
     */
    private final RestClientService restClientService;
    /**
     * MoneyConvertService.
     * @param aRestClientService - restClientService
     */
    public RatesapiConvertService(
            final RestClientService aRestClientService) {
        this.restClientService = aRestClientService;
        this.url = "https://api.ratesapi.io/api/latest";
    }

    /**
     * findRate.
     * @param target - target
     * @return RateResponse
     */
    public CompletableFuture<BigDecimal> findRate(final String target) {
        log.info("find rate RatesapiConvertService.");
        return CompletableFuture.completedFuture(
                restClientService.execute(url, RateResponse.class)
                .getRates().get(target));
    }

}
