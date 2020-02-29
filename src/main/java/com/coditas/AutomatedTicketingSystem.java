package com.coditas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import com.coditas.exception.InvalidFileException;

/**
 * Class to start the automated ticketing system application from a main method.
 */
public class AutomatedTicketingSystem {

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
		} catch (IOException ex) {
			throw new InvalidFileException("Error while reading file.");
		}

	}
}
