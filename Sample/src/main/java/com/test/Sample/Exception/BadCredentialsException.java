package com.test.Sample.Exception;

import org.springframework.http.HttpStatus;

public class BadCredentialsException  extends RuntimeException{

	private final HttpStatus httpStatus;
	
	private final String message;

	public BadCredentialsException(HttpStatus httpStatus, String message) {
		this.httpStatus = httpStatus;
		this.message = message;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public String getMessage() {
		return message;
	}
	
}
