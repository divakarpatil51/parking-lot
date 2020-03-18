package com.parkingticketingsystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import com.parkingticketingsystem.command.AbstractCommand;
import com.parkingticketingsystem.command.Command;
import com.parkingticketingsystem.command.CommandFactory;
import com.parkingticketingsystem.command.CommandParameters;
import com.parkingticketingsystem.command.CreateParkingLotCommand;
import com.parkingticketingsystem.command.LeaveCommand;
import com.parkingticketingsystem.command.ParkCommand;
import com.parkingticketingsystem.command.StatusCommand;
import com.parkingticketingsystem.exception.InvalidFileException;
import com.parkingticketingsystem.parking.ParkingLotManager;
import com.parkingticketingsystem.parking.ParkingLotManagerImpl;

/**
 * Class to start the automated ticketing system application.
 */
public class AutomatedTicketingSystem {

	private static final String SEPARATOR_REGEX = " ";
	public static final String INVALID_FILE_FORMAT = "Please provide valid file. Accepted format: .txt";
	public static final String INVALID_ARGUMENT_LIST = "Invalid argument list. Command usage: java AutomatedTicketingSystem <filename>";

	public static void main(String[] args) {

		validateArguments(args);

		try (BufferedReader reader = new BufferedReader(
				new InputStreamReader(new FileInputStream(new File(args[0]))));) {
			addCommands();
			reader.lines().forEach(AutomatedTicketingSystem::findAndExecCommand);
		} catch (IOException ex) {
			throw new InvalidFileException("Error while reading file." + ex);
		}
	}

	/**
	 * Finds the command and executes it.
	 *
	 * @param line the line
	 */
	private static void findAndExecCommand(String line) {
		CommandParameters parameters = new CommandParameters(line.split(SEPARATOR_REGEX));
		AbstractCommand command = CommandFactory.getCommand(parameters);
		command.execute(parameters);
	}

	/**
	 * Creates and adds the commands.
	 */
	private static void addCommands() {
		ParkingLotManager parkingLotManager = new ParkingLotManagerImpl();
		CommandFactory.addCommand(Command.CREATE_PARKING_LOT, new CreateParkingLotCommand(parkingLotManager));
		CommandFactory.addCommand(Command.PARK, new ParkCommand(parkingLotManager));
		CommandFactory.addCommand(Command.LEAVE, new LeaveCommand(parkingLotManager));
		CommandFactory.addCommand(Command.STATUS, new StatusCommand(parkingLotManager));
	}

	/**
	 * Validates the command line arguments.
	 *
	 * @param args the command line arguments list.
	 */
	private static void validateArguments(String[] args) {
		if (args == null || args.length != 1) {
			throw new InvalidFileException(INVALID_ARGUMENT_LIST);
		}

		String fileName = args[0];
		if (!fileName.endsWith(".txt")) {
			throw new InvalidFileException(INVALID_FILE_FORMAT);
		}
	}
}
