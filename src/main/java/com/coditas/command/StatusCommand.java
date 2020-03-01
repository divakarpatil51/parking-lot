package com.coditas.command;

import com.coditas.exception.IllformedCommandException;
import com.coditas.parking.ParkingLotManager;

/**
 * Class to handle status command.
 */
public class StatusCommand implements Command {

	private ParkingLotManager parkingLotManager;

	public StatusCommand(ParkingLotManager manager) {
		this.parkingLotManager = manager;
	}

	@Override
	public void execute(String[] params) {
		if (!areCommandParamsLengthValid(params, 1)) {
			throw new IllformedCommandException("Invalid number of params for status command");
		}
		parkingLotManager.printStatus();
	}

}
