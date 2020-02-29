package com.coditas.exception;

public class CommandNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CommandNotFoundException(String message) {
		super(message);
	}
}
