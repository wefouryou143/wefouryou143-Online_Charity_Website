package com.capgemini.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class UserExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = NoSuchUserFoundException.class)
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "User Not Found")
	public void handleException(NoSuchUserFoundException e) {

	}

	@ExceptionHandler(value = InvalidCredentialsException.class)
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Invalid  Credentials")
	public void handleException(InvalidCredentialsException e) {

	}

	@ExceptionHandler(value = NoDonationFoundException.class)
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "No Donations Yet")
	public void handleException(NoDonationFoundException e) {

	}

	@ExceptionHandler(value = NoSuchNgoFoundException.class)
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "No Such Ngo Found")
	public void handleException(NoSuchNgoFoundException e) {

	}

	@ExceptionHandler(value = NoSuchEventFoundException.class)
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "No Such Event Found")
	public void handleException(NoSuchEventFoundException e) {

	}
	@ExceptionHandler(value = UniqueConstraintViolationException.class)
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Already Exits.Please provide new Data")
	public void handleException(UniqueConstraintViolationException e) {
		
	}
	

}
