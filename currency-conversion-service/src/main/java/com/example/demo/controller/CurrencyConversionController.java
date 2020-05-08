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
import com.example.demo.service.CurrencyExchangeService;

@RestController
public class CurrencyConversionController {

	@Autowired
	private CurrencyExchangeService currencyExchangeService;
	
	
	
	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CuurencyConversionBean convertCurrency(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
		
		CuurencyConversionBean response = currencyExchangeService.getConvertedCurrency(from, to, quantity);
		
		return response;
	}
}
