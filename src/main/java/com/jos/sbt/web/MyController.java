package com.jos.sbt.web;

import java.math.BigDecimal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.jos.sbt.service.ConvertServiceManagement;

import lombok.extern.slf4j.Slf4j;

/**
 * Controller.
 *
 */
@RestController
@Slf4j
public class MyController {

    /**
     * ConvertService.
     */
    private final ConvertServiceManagement convertServiceManagement;

    /**
     * MyController.
     * @param service - service
     */
    public MyController(final ConvertServiceManagement service) {
        this.convertServiceManagement = service;
    }

    /**
     * convert.
     * @param amount - amount
     * @return String
     */
    @GetMapping("/convert/eur/usd/{amount}")
    public BigDecimal convert(
            @PathVariable final long amount
            ) throws Exception {
        return convertServiceManagement
                .convert(
                        "USD")
                .multiply(
                        new BigDecimal(amount));
    }

}
