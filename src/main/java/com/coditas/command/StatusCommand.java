package com.coditas.command;

import com.coditas.parking.ParkingLotManager;

/**
 * Class to handle status command.
 */
public class StatusCommand extends AbstractCommand {

	public StatusCommand(ParkingLotManager manager) {
		super(manager);
	}

	@Override
	public void execute(CommandParameters params) {
		parkingLotManager.printStatus();
	}

	@Override
	protected int allowedParamsLength() {
		return 0;
	}

	@Override
	protected String name() {
		return "Status command";
	}

}
