package com.parkingticketingsystem.command;

import java.util.Arrays;

/**
 * The Class CommandParameters.
 */
//TODO: Find good name
public class CommandParameters {

	private Command command;
	private String[] parameters;
	private int currentIndex;

	public CommandParameters(String[] commandParams) {
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
