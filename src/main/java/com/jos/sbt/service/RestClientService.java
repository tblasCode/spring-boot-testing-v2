package com.jos.sbt.service;

/**
 * Service.
 *
 */
public interface RestClientService {

    /**
     * findRate.
     * @param url - url
     * @param <T> T
     * @param responseType the type of the return value
     * @return the converted object
     */
    <T> T execute(String url, Class<T> responseType);
}
