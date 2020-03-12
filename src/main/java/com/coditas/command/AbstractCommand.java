package com.coditas.command;

import com.coditas.exception.IllformedCommandException;
import com.coditas.log.LogFactory;
import com.coditas.log.Logger;
import com.coditas.parking.ParkingLotManager;

/**
 * The Class AbstractCommand.
 */
public abstract class AbstractCommand {

	protected static final Logger logger = LogFactory.getLogger();
	protected ParkingLotManager parkingLotManager;

	public AbstractCommand(ParkingLotManager manager) {
		this.parkingLotManager = manager;
	}

	/**
	 * Executes the command.
	 *
	 * @param parameters the command parameters
	 */
	public abstract void execute(CommandParameters parameters);

	/**
	 * Verifies whether the parameters passed to a command are of expected length.
	 *
	 * @param parameters   the parameters
	 * @param expectedSize the expected size
	 * @return true, if parameters list is of expected size.
	 */
	protected void validateParameters(String[] parameters) {
		if(parameters == null || parameters.length != allowedParamsLength()) {
			throw new IllformedCommandException("Invalid number of params for " + name());
		}
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
}
