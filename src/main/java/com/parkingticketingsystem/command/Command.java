package com.parkingticketingsystem.command;

import java.util.HashMap;
import java.util.Map;

/**
 * The Enum Command.
 */
public enum Command {

	CREATE_PARKING_LOT("create_parking_lot"), LEAVE("leave"), PARK("park"), STATUS("status");

	private String commandString;

	private static Map<String, Command> commands = new HashMap<>(4);

	static {
		for (Command command : values()) {
			commands.put(command.commandString, command);
		}
	}

	private Command(String commandString) {
		this.commandString = commandString;
	}

	public String getCommandString() {
		return commandString;
	}

	public static Command get(String commandString) {
		return commands.get(commandString);
	}

}
