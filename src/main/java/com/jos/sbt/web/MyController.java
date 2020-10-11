package com.jos.sbt.web;

import com.jos.sbt.service.MoneyConvertService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@Slf4j
public class MyController {

    private final MoneyConvertService moneyConvertService;

    public MyController(MoneyConvertService moneyConvertService) {
        this.moneyConvertService = moneyConvertService;
    }

    @GetMapping("/convert/eur/usd/{amount}")
    public String convert(@PathVariable long amount) throws ExecutionException, InterruptedException {
        CompletableFuture<String> future1
                = CompletableFuture.supplyAsync(() -> {
            try {
                return moneyConvertService.findRate("https://api.frankfurter.app/latest").get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            return "";
        });
        CompletableFuture<String> future2
                = CompletableFuture.supplyAsync(() -> {
            try {
                return moneyConvertService.findRate("https://api.ratesapi.io/api/latest").get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            return "";
        });
        CompletableFuture<String> future3
                = CompletableFuture.supplyAsync(() -> {
            try {
                return moneyConvertService.findRate("https://api.exchangeratesapi.io/latest").get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            return "";
        });

        String combined = Stream.of(future1, future2, future3)
                .map(CompletableFuture::join)
                .collect(Collectors.joining(" "));

        return combined;
    }

}
