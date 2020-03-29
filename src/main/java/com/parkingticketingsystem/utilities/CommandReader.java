package com.parkingticketingsystem.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

import com.parkingticketingsystem.command.AbstractCommand;
import com.parkingticketingsystem.command.CommandFactory;
import com.parkingticketingsystem.command.CommandParameters;

/**
 * Class to read Command from the file.
 */
public class CommandReader {

	private BufferedReader reader;

	public CommandReader(String pathname) {
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(pathname))));
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Please provide valid arguments." + e.getMessage());
		}
	}

	/**
	 * Returns the next command.
	 * 
	 * @return the next command.
	 */
	public Optional<AbstractCommand> getNextCommand() {
		try {
			String nextCommand = reader.readLine();
			if (nextCommand == null) {
				return Optional.empty();
			}
			CommandParameters parameters = new CommandParameters(nextCommand);
			return Optional.of(CommandFactory.getCommand(parameters));
		} catch (IOException e) {
			return null;
		}
	}
}
