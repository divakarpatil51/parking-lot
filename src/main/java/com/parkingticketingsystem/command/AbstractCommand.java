package com.parkingticketingsystem.command;

import com.parkingticketingsystem.log.LogFactory;
import com.parkingticketingsystem.log.Logger;
import com.parkingticketingsystem.parking.ParkingLotManager;

/**
 * The Class AbstractCommand.
 */
public abstract class AbstractCommand {

	protected static final Logger logger = LogFactory.getLogger();
	protected ParkingLotManager parkingLotManager;
	protected CommandParameters params;

	AbstractCommand(ParkingLotManager manager) {
		this.parkingLotManager = manager;
	}

	/**
	 * Executes the command.
	 *
	 */
	public abstract void execute();

	/**
	 * Verifies whether the parameters passed to a command are of expected length.
	 *
	 * @param parameters   the parameters
	 * @param expectedSize the expected size
	 * @return true, if parameters list is of expected size.
	 */
	protected boolean isParameterLengthValid(String[] parameters) {
		return parameters != null && parameters.length == allowedParamsLength();
	}

	/**
	 * Allowed parameter length for the command.
	 *
	 * @return the allowed parameter lengthn for the command
	 */
	protected abstract int allowedParamsLength();

	/**
	 * Command String.
	 *
	 * @return the command string
	 */
	protected abstract String name();

	public void setCommandParameters(CommandParameters params) {
		this.params = params;
	}
}
