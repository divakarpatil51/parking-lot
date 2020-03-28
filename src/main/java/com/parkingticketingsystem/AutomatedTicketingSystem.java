package com.parkingticketingsystem;

import java.util.Optional;

import com.parkingticketingsystem.command.AbstractCommand;
import com.parkingticketingsystem.utilities.CommandReader;

/**
 * Class to start the automated ticketing system application.
 */
public class AutomatedTicketingSystem {

	public static final String INVALID_ARGUMENT_LIST = "Invalid argument list. Command usage: java AutomatedTicketingSystem <filename>";

	public static void main(String[] args) {

		validateArguments(args);
		CommandReader reader = new CommandReader(args[0]);
		Optional<AbstractCommand> nextCommand;
		while ((nextCommand = reader.getNextCommand()).isPresent()) {
			nextCommand.get().execute();
		}
	}

	/**
	 * Validates the command line arguments.
	 *
	 * @param args the command line arguments list.
	 */
	private static void validateArguments(String[] args) {
		if (args == null || args.length != 1) {
			throw new IllegalArgumentException(INVALID_ARGUMENT_LIST);
		}
	}
}
