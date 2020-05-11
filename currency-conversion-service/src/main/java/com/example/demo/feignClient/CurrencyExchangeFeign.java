package com.example.demo.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.model.ExchangeValue;

@FeignClient(name="currency-exchange-service", url = "http://localhost:8000/currency-exchange/")
public interface CurrencyExchangeFeign {
	
	@GetMapping("/from/{from}/to/{to}")
	public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to);
}
