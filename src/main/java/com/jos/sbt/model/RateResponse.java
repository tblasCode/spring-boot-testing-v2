package com.jos.sbt.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

import lombok.Data;

@Data
public class RateResponse {
    /** base changes.*/
    private String base;
    /** date.*/
    private LocalDate date;
    /** rates.*/
    private Map<String, BigDecimal> rates;
}
