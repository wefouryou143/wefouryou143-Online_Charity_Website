package com.capgemini.exception;
//Exception Defination
public class BankNotFoundException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BankNotFoundException(String message) {
		super(message);
	}


}
