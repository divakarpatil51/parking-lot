package com.coditas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import com.coditas.command.Command;
import com.coditas.command.CommandFactory;
import com.coditas.exception.InvalidFileException;
import com.coditas.parking.ParkingLotManager;

/**
 * Class to start the automated ticketing system application from a main method.
 */
public class AutomatedTicketingSystem {

	private static final String SEPARATOR = " ";
	public static final String INVALID_FILE_FORMAT = "Please provide valid file. Accpeted format: .txt";
	public static final String INVALID_ARGUMENT_LIST = "Invalid argument list. Command usage: java AutomatedTicketingSystem <filename>";

	public static void main(String[] args) {

		if (args == null || args.length != 1) {
			throw new InvalidFileException(INVALID_ARGUMENT_LIST);
		}

		String fileName = args[0];
		if (!fileName.endsWith(".txt")) {
			throw new InvalidFileException(INVALID_FILE_FORMAT);
		}

		File file = new File(fileName);
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));) {
			ParkingLotManager parkingLotManager = new ParkingLotManager();
			reader.lines().forEach(line -> {
				String[] commandParameters = line.split(SEPARATOR);
				Command command = CommandFactory.getCommand(commandParameters[0], parkingLotManager);
				command.execute(commandParameters);
			});
		} catch (IOException ex) {
			throw new InvalidFileException("Error while reading file.");
		}

	}
}
