package com.coditas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import com.coditas.command.Command;
import com.coditas.command.CommandFactory;
import com.coditas.exception.InvalidFileException;
import com.coditas.parking.ParkingLotManager;

/**
 * Class to start the automated ticketing system application from a main method.
 */
public class AutomatedTicketingSystem {

	private static final String SEPARATOR = " ";
	public static final String INVALID_FILE_CONTENTS = "File contents cannot be empty";
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
			long linesCount = reader.lines().count();
			if (linesCount == 0) {
				throw new InvalidFileException(INVALID_FILE_CONTENTS);
			}
			ParkingLotManager manager = new ParkingLotManager();
			reader.lines().forEach(line -> {
				String[] commandData = line.split(SEPARATOR);
				Command command = CommandFactory.getCommand(commandData[0], manager);
				command.execute(commandData);
			});
		} catch (IOException ex) {
			throw new InvalidFileException("Error while reading file.");
		}

	}
}
