package com.parkingticketingsystem.log;

/**
 * A factory for creating Log objects. Currently, we have only MessageLogger. In
 * future, we can setup multiple loggers as required.
 */
public class LogFactory {

	private LogFactory() {
	}

	/**
	 * Gets the logger.
	 *
	 * @return the logger
	 */
	public static Logger getLogger() {
		return new MessageLogger();
	}
}
