package com.coditas.command;

import java.util.EnumMap;
import java.util.Map;

import com.coditas.log.LogFactory;
import com.coditas.log.Logger;

/**
 * A factory for creating {@link AbstractCommand} objects.
 */
public final class CommandFactory {

	private static Map<Command, AbstractCommand> commands = new EnumMap<>(Command.class);
	private static final Logger LOGGER = LogFactory.getLogger();

	private CommandFactory() {
	}

	/**
	 * Adds the command.
	 *
	 * @param commandString the command string
	 * @param command       the {@link AbstractCommand} object
	 */
	public static void addCommand(Command commandValue, AbstractCommand command) {
		commands.put(commandValue, command);
	}

	/**
	 * Gets the command as per command string.
	 *
	 * @param params the params
	 * @return the command
	 */
	public static AbstractCommand getCommand(CommandParameters params) {
		Command command = params.getCommand();
		if (commands.containsKey(command)) {
			return commands.get(command);
		}
		LOGGER.error("Please enter valid command");
		// For ill formed command, showing status of parking
		return commands.get(Command.STATUS);
	}
}
