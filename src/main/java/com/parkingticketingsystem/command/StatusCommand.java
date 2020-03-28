package com.parkingticketingsystem.command;

import com.parkingticketingsystem.parking.ParkingLotManager;

/**
 * Class to handle status command.
 */
public class StatusCommand extends AbstractCommand {

	public StatusCommand(ParkingLotManager manager) {
		super(manager);
	}

	@Override
	public void execute() {
		parkingLotManager.printStatus();
	}

	@Override
	protected int allowedParamsLength() {
		return 0;
	}

	@Override
	protected String name() {
		return "status command";
	}

}
