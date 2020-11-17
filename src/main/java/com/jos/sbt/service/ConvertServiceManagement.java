package com.jos.sbt.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Service;

/**
 * Service.
 *
 */
@Service
public class ConvertServiceManagement {
    /**
     * List<ConvertService> convertServices.
     */
    private final List<ConvertService> convertServices;
    /**
     * Construct.
     * @param services - services
     */
    public ConvertServiceManagement(final List<ConvertService>
        services) {
        this.convertServices = services;
    }
    /**
     * convert.
     * @param target target
     * @return best rate
     */
    public BigDecimal convert(final String target) throws Exception {
        return convertServices.stream()
                .map(service -> service.findRate(target))
                .map(CompletableFuture::join)
                .max(BigDecimal::compareTo).orElseThrow(Exception::new);
    }

}
