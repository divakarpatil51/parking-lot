package com.coditas.command;

import com.coditas.parking.ParkingLotManager;

public class CreateParkingLotCommand implements Command {
	
	private ParkingLotManager parkingLotManager;

	public CreateParkingLotCommand(ParkingLotManager manager) {
		this.parkingLotManager = manager;
	}

	@Override
	public void execute(Object[] data) {
		parkingLotManager.createParkingLot((int)data[0]);
	}

}
