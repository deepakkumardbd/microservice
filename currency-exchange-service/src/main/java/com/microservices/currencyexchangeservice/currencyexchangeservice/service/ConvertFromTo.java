package com.microservices.currencyexchangeservice.currencyexchangeservice.service;

import com.microservices.currencyexchangeservice.currencyexchangeservice.dao.IConversionRateDAO;
import com.microservices.currencyexchangeservice.currencyexchangeservice.entity.ConversionRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ConvertFromTo {

    @Autowired
    private IConversionRateDAO conversionRateDAO;

    public ConversionRate add(ConversionRate conversionRate){
        conversionRate = conversionRateDAO.save(conversionRate);
        return conversionRate;
    }
    public List<ConversionRate> findById(String from, String to){
        return conversionRateDAO.findByFromAndTo(from,to);
    }
}
