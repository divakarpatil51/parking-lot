package com.coditas.command;

import com.coditas.exception.IllformedCommandException;
import com.coditas.parking.ParkingLotManager;

/**
 * Class to handle leave command.
 */
public class LeaveCommand implements Command {

	private ParkingLotManager parkingLotManager;

	public LeaveCommand(ParkingLotManager manager) {
		this.parkingLotManager = manager;
	}

	@Override
	public void execute(String[] params) {
		if (!areCommandParamsLengthValid(params, 3)) {
			throw new IllformedCommandException("Invalid number of params for leave command");
		}
		parkingLotManager.leaveParkingSlot(params);
	}

}
