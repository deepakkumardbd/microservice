package com.microservices.currencyexchangeservice.currencyexchangeservice.dao;

import com.microservices.currencyexchangeservice.currencyexchangeservice.entity.ConversionRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IConversionRateDAO extends JpaRepository<ConversionRate, Integer> {
    List<ConversionRate> findAll();
    List<ConversionRate> findByFromAndTo(String from, String to);
}
