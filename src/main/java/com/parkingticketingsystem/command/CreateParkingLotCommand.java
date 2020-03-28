package com.parkingticketingsystem.command;

import com.parkingticketingsystem.parking.ParkingLotManager;

/**
 * Class to handle create_parking_lot command.
 */
public class CreateParkingLotCommand extends AbstractCommand {

	public CreateParkingLotCommand(ParkingLotManager manager) {
		super(manager);
	}

	@Override
	public void execute() {
		boolean isValid = isParameterLengthValid(params.getParameters());
		if (!isValid) {
			logger.error("Invalid params for the %s", name());
			return;
		}
		int parkingSize = Integer.parseInt(params.nextParam());
		parkingLotManager.createParkingLot(parkingSize);
		logger.info("Created parking lot with %d slots", parkingSize);
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
