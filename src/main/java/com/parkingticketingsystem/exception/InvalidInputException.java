package com.parkingticketingsystem.exception;

/**
 * Signals that the input present in the file is invalid
 */
public class InvalidInputException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidInputException(String message) {
		super(message);
	}
}
