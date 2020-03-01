package com.coditas.command;

import com.coditas.exception.IllformedCommandException;
import com.coditas.parking.ParkingLotManager;

/**
 * Class to handle create_parking_lot command.
 */
public class CreateParkingLotCommand implements Command {

	private ParkingLotManager parkingLotManager;

	public CreateParkingLotCommand(ParkingLotManager manager) {
		this.parkingLotManager = manager;
	}

	@Override
	public void execute(String[] params) {
		if (!areCommandParamsLengthValid(params, 2)) {
			throw new IllformedCommandException("Invalid number of params for create_parking_lot command");
		}

		parkingLotManager.createParkingLot(Integer.valueOf(params[1]));
	}

}
