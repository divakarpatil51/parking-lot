package com.parkingticketingsystem.command;

import java.util.EnumMap;
import java.util.Map;

import com.parkingticketingsystem.log.LogFactory;
import com.parkingticketingsystem.log.Logger;
import com.parkingticketingsystem.parking.ParkingLotManager;
import com.parkingticketingsystem.parking.ParkingLotManagerImpl;

/**
 * A factory for creating {@link AbstractCommand} objects.
 */
public final class CommandFactory {

	private static Map<Command, AbstractCommand> commands = new EnumMap<>(Command.class);
	private static final Logger LOGGER = LogFactory.getLogger();

	static {
		//TODO: Whose responsibility is to create ParkingLotManager?
		ParkingLotManager parkingLotManager = new ParkingLotManagerImpl();
		addCommand(Command.CREATE_PARKING_LOT, new CreateParkingLotCommand(parkingLotManager));
		addCommand(Command.PARK, new ParkCommand(parkingLotManager));
		addCommand(Command.LEAVE, new LeaveCommand(parkingLotManager));
		addCommand(Command.STATUS, new StatusCommand(parkingLotManager));
	}
	
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
		Command commandKey = params.getCommand();
		if (commands.containsKey(commandKey)) {
			AbstractCommand command = commands.get(commandKey);
			command.setCommandParameters(params);
			return command;
		}
		LOGGER.error("Please enter valid command");
		// For ill formed command, showing status of parking
		return commands.get(Command.STATUS);
	}
}
