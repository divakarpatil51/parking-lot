package com.coditas.command;

/**
 * The Interface Command.
 */
public interface Command {

	/**
	 * Executes the command.
	 *
	 * @param parameters the command parameters
	 */
	public void execute(String[] parameters);

	/**
	 * Verifies whether the parameters passed to a command are of expected length.
	 *
	 * @param parameters   the parameters
	 * @param expectedSize the expected size
	 * @return true, if parameters list is of expected size.
	 */
	public default boolean areCommandParamsLengthValid(String[] parameters, int expectedSize) {
		return parameters != null && parameters.length == expectedSize;
	}
}
