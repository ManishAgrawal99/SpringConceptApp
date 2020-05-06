package com.example.demo.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.ExchangeValue;
import com.example.demo.repository.ExchangeRepository;

@RestController
public class CurrencyExchangeController {

	@Autowired
	private Environment environment;
	
	@Autowired
	private ExchangeRepository exchangeRepository;

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
		
		//ExchangeValue exchangeValue = new ExchangeValue(1000L, from, to, BigDecimal.valueOf(65));
		ExchangeValue exchangeValue = exchangeRepository.findByFromAndTo(from, to);
		
		exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		return exchangeValue;
	}
	
	@GetMapping("/addValuesToDb")
	public List<ExchangeValue> addValuesToDb(){
		
		List<ExchangeValue> values = new ArrayList<>();
		
		ExchangeValue value = new ExchangeValue(10001L, "USD", "INR", BigDecimal.valueOf(75));
		values.add(exchangeRepository.save(value));
		
		value = new ExchangeValue(10002L, "EUR", "INR", BigDecimal.valueOf(80));
		values.add(exchangeRepository.save(value));
		
		value = new ExchangeValue(10003L, "AUD", "INR", BigDecimal.valueOf(25));
		values.add(exchangeRepository.save(value));
		
		return values;
		
	}
}
