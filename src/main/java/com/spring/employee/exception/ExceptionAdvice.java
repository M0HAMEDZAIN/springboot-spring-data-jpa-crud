package com.spring.employee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvice {

	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handler(Exception exception) {
		ErrorResponse error = new ErrorResponse();

		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(exception.getMessage());
		error.setTimeStamp(System.currentTimeMillis());

		return new ResponseEntity<ErrorResponse>(error, HttpStatus.NOT_FOUND);
	}
}
