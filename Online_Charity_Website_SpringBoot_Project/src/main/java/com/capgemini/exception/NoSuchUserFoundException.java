package com.capgemini.exception;
//Exception Defination
public class NoSuchUserFoundException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoSuchUserFoundException(String message) {
		super(message);
	}

}
