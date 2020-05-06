package com.example.demo.exceptions;

public class CurrencyNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8222113106812001236L;
	
	public CurrencyNotFoundException(String message) {
		super(message);
	}

}
