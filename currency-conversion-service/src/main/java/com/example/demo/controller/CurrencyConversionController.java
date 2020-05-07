package com.example.demo.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.exception.CurrencyNotFoundException;
import com.example.demo.model.CuurencyConversionBean;
import com.example.demo.model.ExchangeValue;

@RestController
public class CurrencyConversionController {

	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public Object convertCurrency(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
		
		//ExchangeValue exchange = restTemplate.getForObject("http://localhost:8000/currency-exchange/from/"+from+"/to/"+to, ExchangeValue.class);
		
		try {
			ResponseEntity<ExchangeValue> response = restTemplate.getForEntity("http://localhost:8000/currency-exchange/from/"+from+"/to/"+to, ExchangeValue.class);
			System.out.println(response.getStatusCode());
			ExchangeValue exchange = response.getBody();
			return new CuurencyConversionBean(exchange.getId(), from, to, exchange.getConversionMultiple(), quantity, quantity.multiply(exchange.getConversionMultiple()), exchange.getPort());
		}
		catch(RuntimeException ex) {
			return ex.getMessage();
		}
		
		//System.out.println(response.getBody());

		
		//return "Hello";
		//return new CuurencyConversionBean(exchange.getId(), from, to, exchange.getConversionMultiple(), quantity, quantity.multiply(exchange.getConversionMultiple()), exchange.getPort());

	}
}
