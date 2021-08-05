package com.capgemini.exception;

public class UniqueConstraintViolationException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UniqueConstraintViolationException(String message) {
		super(message);
	}

}
