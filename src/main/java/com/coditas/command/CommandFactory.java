package com.coditas.command;

import com.coditas.exception.CommandNotFoundException;
import com.coditas.parking.ParkingLotManager;

/**
 * A factory for creating {@code Command} objects.
 */
public class CommandFactory {

	private CommandFactory() {
	}

	public static Command getCommand(String command, ParkingLotManager manager) {
		switch (command) {
		case "create_parking_lot":
			return new CreateParkingLotCommand(manager);
		case "park":
			return new ParkCommand(manager);
		case "leave":
			return new LeaveCommand(manager);
		case "status":
			return new StatusCommand(manager);
		default:
			throw new CommandNotFoundException("Please provide valid command");
		}

	}
}
