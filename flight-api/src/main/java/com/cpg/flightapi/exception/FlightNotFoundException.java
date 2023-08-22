package com.cpg.flightapi.exception;

public class FlightNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FlightNotFoundException(String message) {
		super(message);
	}

}
