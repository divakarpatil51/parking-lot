package com.coditas.exception;

/**
 * Signals that the a command was not found in the available commands list.
 */
public class CommandNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CommandNotFoundException(String message) {
		super(message);
	}
}
