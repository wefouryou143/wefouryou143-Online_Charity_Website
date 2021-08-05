package com.capgemini.exception;

public class NoDonationFoundException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoDonationFoundException(String message) {
		super(message);
	}

}
