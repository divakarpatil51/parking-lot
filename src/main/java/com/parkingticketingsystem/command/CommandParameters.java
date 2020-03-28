package com.parkingticketingsystem.command;

import java.util.Arrays;

/**
 * The Class CommandParameters.
 */
public class CommandParameters {

	private static final String SEPARATOR_REGEX = " ";

	private Command command;
	private String[] parameters;
	private int currentIndex;

	public CommandParameters(String command) {
		String[] commandParams = command.split(SEPARATOR_REGEX);
		this.command = Command.get(commandParams[0]);
		if (commandParams.length > 1) {
			parameters = Arrays.copyOfRange(commandParams, 1, commandParams.length);
		}
	}

	public Command getCommand() {
		return command;
	}

	public String[] getParameters() {
		return parameters;
	}

	public String nextParam() {
		return parameters[currentIndex++];
	}
}
