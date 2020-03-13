package com.parkingticketingsystem.exception;

/**
 * Signals that the file specified at runtime is not in expected format.
 */
public class InvalidFileException extends RuntimeException {

	private static final long serialVersionUID = 6916919302551682397L;

	public InvalidFileException(String message) {
		super(message);
	}

}
