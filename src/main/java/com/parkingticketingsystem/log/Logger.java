package com.parkingticketingsystem.log;

/**
 * Logging Api to log messages.
 */
public interface Logger {

	/**
	 * Logs a message with info log level.
	 *
	 * @param message log this message
	 */
	void log(String message);
	
	void error(String message);

}
