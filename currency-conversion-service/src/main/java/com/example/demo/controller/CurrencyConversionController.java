package com.example.demo.controller;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.CuurencyConversionBean;
import com.example.demo.model.ExchangeValue;
import com.example.demo.service.CurrencyExchangeService;

@RestController
public class CurrencyConversionController {

	private Logger logger=LoggerFactory.getLogger(this.getClass()); 
	
	
	@Autowired
	private CurrencyExchangeService currencyExchangeService;


	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CuurencyConversionBean convertCurrency(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {

		CuurencyConversionBean response = currencyExchangeService.CurrencyConversionWithFeign(from, to, quantity);
		
		logger.info("{}", response);
		
		return response;
	}

	@GetMapping("/currency-converter/from/{from}/to/{to}/")
	public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
		
		System.out.println("I am Here");
		
		ExchangeValue exchangeValue = currencyExchangeService.currencyExchangeWithFeign(from, to);
		
		logger.info("{}", exchangeValue);
		
		System.out.println(exchangeValue);
		
		return exchangeValue;
		
	}

}
