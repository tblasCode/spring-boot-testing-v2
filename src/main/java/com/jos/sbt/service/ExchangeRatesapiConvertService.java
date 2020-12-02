package com.jos.sbt.service;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

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
     * Timeout.
     */
    private static final int TIMEOUT = 60;
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
    public ExchangeRatesapiConvertService(
            final RestClientService aRestClientService) {
        this.restClientService = aRestClientService;
        this.url = "https://api.exchangeratesapi.io/latest";
    }
    /**
     * findRate.
     * @param target - restTemplateBuilder
     * @return rate
     */
    public final CompletableFuture<BigDecimal> findRate(final String target) {
        log.info("find rate ExchangeRatesapiConvertService.");
        return CompletableFuture
                .supplyAsync(()
                        -> restClientService.execute(url, RateResponse.class)
                        .getRates().get(target))
                .orTimeout(TIMEOUT,
                        TimeUnit.SECONDS)
                .handle((response, ex) -> {
                    if (!Objects.isNull(ex)) {
                        log.error(ex.getLocalizedMessage(), ex);
                    }
                    log.info("response ExchangeRatesapiConvertService");
                    return response;
                });
    }
}
