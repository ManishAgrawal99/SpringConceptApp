package com.example.demo.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.CuurencyConversionBean;
import com.example.demo.model.ExchangeValue;

@RestController
public class CurrencyConversionController {

	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CuurencyConversionBean convertCurrency(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
		
		ExchangeValue exchange = restTemplate.getForObject("http://localhost:8000/currency-exchange/from/"+from+"/to/"+to, ExchangeValue.class);
		
		
		return new CuurencyConversionBean(exchange.getId(), from, to, exchange.getConversionMultiple(), quantity, quantity.multiply(exchange.getConversionMultiple()), exchange.getPort());

	}
}
