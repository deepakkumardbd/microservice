package com.microservices.currencyconversionservice.controller;

import com.microservices.currencyconversionservice.bean.Currency;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;


@RestController
public class Controller {

    @GetMapping(value= "currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public ResponseEntity<Object> convert(@PathVariable String from,
                                          @PathVariable String to, @PathVariable int quantity){

        HashMap<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from",from);
        uriVariables.put("to",to);
        ResponseEntity<Currency> entity = new RestTemplate().getForEntity("http://localhost:8001/currency-exchange/from/{from}/to/{to}",Currency.class,uriVariables);
        Currency curr = entity.getBody();
        System.out.println(curr.toString());
        curr.setQuantity(quantity);
        curr.setTotalCalculatedAmount((double) (curr.getConversionMultiple() * curr.getQuantity()));
        return new ResponseEntity<>(curr, HttpStatus.OK);
    }
}
