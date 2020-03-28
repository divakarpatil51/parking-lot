package com.parkingticketingsystem.command;

import java.util.Optional;

import com.parkingticketingsystem.model.VehicleLeaveResponse;
import com.parkingticketingsystem.parking.ParkingLotManager;

/**
 * Class to handle leave command.
 */
public class LeaveCommand extends AbstractCommand {

	public LeaveCommand(ParkingLotManager manager) {
		super(manager);
	}

	@Override
	public void execute() {
		boolean isValid = isParameterLengthValid(params.getParameters());
		if (!isValid) {
			logger.error("Invalid params for the %s", name());
			return;
		}
		String registrationNumber = params.nextParam();
		int timeSpent = Integer.parseInt(params.nextParam());
		Optional<VehicleLeaveResponse> vehicle = parkingLotManager
				.leaveParkingSlot(registrationNumber, timeSpent);

		if (vehicle.isPresent()) {
			logger.info("Registration number  %s with Slot Number %d is free with Charge %d",
					registrationNumber, vehicle.get().getSlotNumber(),
					vehicle.get().getParkingCharge());
		} else {
			logger.info("Registration number %s not found", registrationNumber);
		}

	}

	@Override
	protected int allowedParamsLength() {
		return 2;
	}

	@Override
	protected String name() {
		return "Leave command";
	}

}
