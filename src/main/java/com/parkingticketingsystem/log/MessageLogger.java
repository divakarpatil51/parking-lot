package com.parkingticketingsystem.log;

/**
 * Used to delegate logging to the currently configured default logging.
 */
public class MessageLogger implements Logger {

	@Override
	public void log(String message) {
		System.out.println(message);
	}

	@Override
	public void error(String message) {
		System.err.println(message);
	}

}