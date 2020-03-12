package com.coditas.command;

import java.util.Optional;

import com.coditas.model.VehicleLeaveResponse;
import com.coditas.parking.ParkingLotManager;

/**
 * Class to handle leave command.
 */
public class LeaveCommand extends AbstractCommand {

	public LeaveCommand(ParkingLotManager manager) {
		super(manager);
	}

	@Override
	public void execute(CommandParameters params) {
		validateParameters(params.getParameters());
		String registrationNumber = params.nextParam();
		int timeSpent = Integer.parseInt(params.nextParam());
		Optional<VehicleLeaveResponse> vehicle = parkingLotManager.leaveParkingSlot(registrationNumber, timeSpent);

		if (vehicle.isPresent()) {
			logger.log(String.format("Registration number  %s with Slot Number %d is free with Charge %d",
					registrationNumber, vehicle.get().getSlotNumber(), vehicle.get().getParkingCharge()));
		} else {
			logger.log(String.format("Registration number %s not found", registrationNumber));
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
