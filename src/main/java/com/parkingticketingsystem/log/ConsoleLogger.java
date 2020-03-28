package com.parkingticketingsystem.log;

/**
 * Logs the messages to Console.
 */
public class ConsoleLogger implements Logger {

	@Override
	public void info(String message, Object... args) {
		System.out.println(String.format(message, args));
	}

	@Override
	public void error(String message, Object... args) {
		System.err.println(String.format(message, args));
	}

}
