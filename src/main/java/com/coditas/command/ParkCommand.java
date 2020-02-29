package com.coditas.command;

import com.coditas.parking.ParkingLotManager;

public class ParkCommand implements Command {

	private ParkingLotManager parkingLotManager;

	public ParkCommand(ParkingLotManager manager) {
		this.parkingLotManager = manager;
	}

	@Override
	public void execute(Object[] data) {
		parkingLotManager.allocateParkingSlot(data);
	}

}
