package com.parkingticketingsystem.log;

/**
 * Logging Api to log messages.
 */
public interface Logger {

	/**
	 * Logs a message with info log level.
	 *
	 * @param message   log this message
	 * @param arguments arguments
	 */
	void info(String message, Object... arguments);

	/**
	 * Logs a message with error log level.
	 *
	 * @param message log this message
	 * @param arguments arguments
	 */
	void error(String message, Object... arguments);
}
