package com.coditas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import com.coditas.command.AbstractCommand;
import com.coditas.command.Command;
import com.coditas.command.CommandFactory;
import com.coditas.command.CommandParameters;
import com.coditas.command.CreateParkingLotCommand;
import com.coditas.command.LeaveCommand;
import com.coditas.command.ParkCommand;
import com.coditas.command.StatusCommand;
import com.coditas.exception.InvalidFileException;
import com.coditas.parking.ParkingLotManager;
import com.coditas.parking.ParkingLotManagerImpl;

/**
 * Class to start the automated ticketing system application.
 */
public class AutomatedTicketingSystem {

	private static final String SEPARATOR_REGEX = " ";
	public static final String INVALID_FILE_FORMAT = "Please provide valid file. Accepted format: .txt";
	public static final String INVALID_ARGUMENT_LIST = "Invalid argument list. Command usage: java AutomatedTicketingSystem <filename>";

	public static void main(String[] args) {

		// TODO: Remove below line once testing is completed.
		//args[0] = AutomatedTicketingSystem.class.getResource("/parking_lot_file_inputs.txt").getFile();
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
		//TODO: Check how to improve below code
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
