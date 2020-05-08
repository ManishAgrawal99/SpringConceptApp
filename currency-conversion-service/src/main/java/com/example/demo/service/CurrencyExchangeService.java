package com.example.demo.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.CuurencyConversionBean;
import com.example.demo.model.ExchangeValue;

@Service
public class CurrencyExchangeService {

	@Autowired
	private RestTemplate restTemplate;

	public CuurencyConversionBean getConvertedCurrency(String from, String to, BigDecimal quantity) {

		try {
			ResponseEntity<ExchangeValue> response = restTemplate.getForEntity(
					"http://localhost:8000/currency-exchange/from/" + from + "/to/" + to, ExchangeValue.class);
			System.out.println(response.getStatusCode());
			ExchangeValue exchange = response.getBody();

			return new CuurencyConversionBean(exchange.getId(), from, to, exchange.getConversionMultiple(), quantity,
					quantity.multiply(exchange.getConversionMultiple()), exchange.getPort());

		} catch (HttpClientErrorException ex) {
			System.out.println(ex.getMessage());
			return new CuurencyConversionBean(1L, from, to, BigDecimal.ONE, quantity, BigDecimal.ONE, 8000);
		}

	}
}
