package com.capgemini.exception;

public class NoSuchEventFoundException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoSuchEventFoundException(String message) {
		super(message);
	}
}
