package com.example.demo.feignClient;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.model.ExchangeValue;

@FeignClient(name="currency-exchange-service")
@RibbonClient(name = "currency-exchange-service")
public interface CurrencyExchangeFeign {
	
	@GetMapping("currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to);
}
