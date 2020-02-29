package com.coditas.command;

import com.coditas.parking.ParkingLotManager;

public class StatusCommand implements Command {

	private ParkingLotManager parkingLotManager;

	public StatusCommand(ParkingLotManager manager) {
		this.parkingLotManager = manager;
	}

	@Override
	public void execute(Object[] data) {
		parkingLotManager.printStatus();
	}

}
