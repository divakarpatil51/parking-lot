package com.coditas.exception;

/**
 * Exception thrown in case of invalid inputs.
 */
public class InvalidInputException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidInputException(String message) {
		super(message);
	}
}
