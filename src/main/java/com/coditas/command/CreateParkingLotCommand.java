package com.coditas.command;

import com.coditas.parking.ParkingLotManager;

/**
 * Class to handle create_parking_lot command.
 */
public class CreateParkingLotCommand extends AbstractCommand {

	public CreateParkingLotCommand(ParkingLotManager manager) {
		super(manager);
	}

	@Override
	public void execute(CommandParameters params) {
		validateParameters(params.getParameters());
		int parkingSize = Integer.parseInt(params.nextParam());
		parkingLotManager.createParkingLot(parkingSize);
		logger.log(String.format("Created parking lot with %d slots", parkingSize));
	}

	@Override
	protected int allowedParamsLength() {
		return 1;
	}

	@Override
	protected String name() {
		return "Create Parking Lot command";
	}
}
