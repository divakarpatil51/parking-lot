package com.parkingticketingsystem.exception;

/**
 * Signals that the command format is not as expected
 */
public class IllformedCommandException extends RuntimeException {

	private static final long serialVersionUID = -7916916889893987249L;

	public IllformedCommandException(String message) {
		super(message);
	}
}
