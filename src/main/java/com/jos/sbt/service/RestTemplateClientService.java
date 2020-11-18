package com.jos.sbt.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

/**
 * Service.
 *
 */
@Service
@Slf4j
public class RestTemplateClientService implements RestClientService {

    /**
     * RestTemplate.
     */
    private final RestTemplate restTemplate;

    /**
     * MoneyConvertService.
     * @param restTemplateBuilder - restTemplateBuilder
     */
    public RestTemplateClientService(final RestTemplateBuilder
            restTemplateBuilder) {
        //JCE Unlimited Strength policy files for Java 11.
        System.setProperty("https.protocols", "TLSv1.2,TLSv1.1,TLSv1");
        RestTemplate rest = new RestTemplate();
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        MappingJackson2HttpMessageConverter converter =
                new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(
                Collections.singletonList(MediaType.ALL));
        messageConverters.add(converter);
        rest.setMessageConverters(messageConverters);
        this.restTemplate = rest;
    }

    /**
     * findRate.
     * @param url - url
     * @param <T> T
     * @param responseType the type of the return value
     * @return object
     */
    public <T> T execute(final String url,
            final Class<T> responseType) {
        log.info("Looking up " + url);
        return restTemplate.getForObject(url, responseType);
    }

}
