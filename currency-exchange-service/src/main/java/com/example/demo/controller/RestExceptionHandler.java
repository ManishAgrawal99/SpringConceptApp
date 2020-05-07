package com.example.demo.controller;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.demo.exceptions.CurrencyNotFoundException;
import com.example.demo.model.ApiError;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(CurrencyNotFoundException.class)
	protected ResponseEntity<Object> handleCurrencyNotFound(CurrencyNotFoundException ex){
		ApiError error = new ApiError(HttpStatus.NOT_FOUND);
		error.setMessage(ex.getMessage());
		error.setDebugMessage("Check if the currency you want to convert is available in our documentation");
		return buildResponseEntity(error);
	}
	
	private ResponseEntity<Object> buildResponseEntity(ApiError error) {
		return new ResponseEntity<>(error, error.getStatus());
	}
}
