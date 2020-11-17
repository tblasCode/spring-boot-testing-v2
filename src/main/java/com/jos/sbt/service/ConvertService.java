package com.jos.sbt.service;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;

public interface ConvertService {
    /**
     * findRate.
     * @param target - target
     * @return The rate found
     */
    CompletableFuture<BigDecimal> findRate(String target);
}
