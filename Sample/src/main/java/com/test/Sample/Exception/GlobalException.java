package com.test.Sample.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler {

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException bce, HttpStatus status) {
		ApiErrorResponse response = new ApiErrorResponse(401, bce.getMessage(), "Invalid Credentials");
		return ResponseEntity.badRequest().body(response);
	}
}
