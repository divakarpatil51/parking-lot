package com.coditas.command;

import com.coditas.parking.ParkingLotManager;

public class LeaveCommand implements Command {
	private ParkingLotManager parkingLotManager;

	public LeaveCommand(ParkingLotManager manager) {
		this.parkingLotManager = manager;
	}

	@Override
	public void execute(Object[] data) {
		parkingLotManager.deallocateParkingSpace(data);
	}

}
