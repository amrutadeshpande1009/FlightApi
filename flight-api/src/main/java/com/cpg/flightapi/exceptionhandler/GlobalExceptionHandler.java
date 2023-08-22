package com.cpg.flightapi.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cpg.flightapi.exception.FlightNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(FlightNotFoundException.class)
	public ResponseEntity<String> handleFlightNotFoundException(FlightNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());

	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleGeneralException(Exception ex) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error Occcured");

	}

}
