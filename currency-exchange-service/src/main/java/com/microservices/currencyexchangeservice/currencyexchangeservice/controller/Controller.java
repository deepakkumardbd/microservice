package com.microservices.currencyexchangeservice.currencyexchangeservice.controller;


import com.microservices.currencyexchangeservice.currencyexchangeservice.bean.CurrencyExchange;
import com.microservices.currencyexchangeservice.currencyexchangeservice.entity.ConversionRate;
import com.microservices.currencyexchangeservice.currencyexchangeservice.service.ConvertFromTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;


@RestController
public class Controller {

    @Autowired
    private Environment env;


    @Autowired
    private ConvertFromTo convertFromTo;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(@PathVariable String from,
                                                  @PathVariable String to){


        ConversionRate convertTo = convertFromTo.findById(from,to).get(0);
        CurrencyExchange currencyExchange = new CurrencyExchange(convertTo.getId(), convertTo.getFrom(), convertTo.getTo(), BigDecimal.valueOf(convertTo.getAmount()));
        currencyExchange.setEnv(env.getProperty("local.server.port"));
        return currencyExchange;
    }

    @PostMapping(value = "/currency-exchange/from/to")
    public ResponseEntity<Object> addCurrencyExchangeValue(@RequestBody ConversionRate conversionRate){
        conversionRate = convertFromTo.add(conversionRate);
        return new ResponseEntity<>(conversionRate, HttpStatus.CREATED);
    }
}
