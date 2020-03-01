package com.coditas.command;

import com.coditas.exception.IllformedCommandException;
import com.coditas.parking.ParkingLotManager;

/**
 * Class to handle park command.
 */
public class ParkCommand implements Command {

	private ParkingLotManager parkingLotManager;

	public ParkCommand(ParkingLotManager manager) {
		this.parkingLotManager = manager;
	}

	@Override
	public void execute(String[] params) {
		if (!areCommandParamsLengthValid(params, 2)) {
			throw new IllformedCommandException("Invalid number of params for park command");
		}
		parkingLotManager.allocateParkingSlot(params);
	}

}
