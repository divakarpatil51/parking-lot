package com.parkingticketingsystem.command;

import com.parkingticketingsystem.exception.InvalidInputException;
import com.parkingticketingsystem.model.VehicleDetails;
import com.parkingticketingsystem.parking.ParkingLotManager;

/**
 * Class to handle park command.
 */
public class ParkCommand extends AbstractCommand {

	public ParkCommand(ParkingLotManager manager) {
		super(manager);
	}

	@Override
	public void execute(CommandParameters params) {
		validateParameters(params.getParameters());
		VehicleDetails carData = new VehicleDetails();
		carData.setRegistrationNumber(params.nextParam());
		try {
			int slotNumber = parkingLotManager.allocateParkingSlot(carData);
			if (slotNumber == -1) {
				logger.log("Sorry, parking lot is full");
			} else {
				logger.log(String.format("Allocated slot number: %d", slotNumber));
			}
		} catch (InvalidInputException ex) {
			logger.error(ex.getMessage());
		}

	}

	@Override
	protected int allowedParamsLength() {
		return 1;
	}

	@Override
	protected String name() {
		return "Park command";
	}

}
