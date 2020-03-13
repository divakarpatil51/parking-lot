package com.parkingticketingsystem.utilities;

/**
 * Class to calculate Parking Charge for a vehicle.
 */
public class ParkingChargeCalculator {

	private static final int BASE_CHARGE = 10;
	private static final int MINIMUM_HOURS_CHARGED = 2;
	private static final int ADDITIONAL_HOUR_CHARGE = 10;

	private ParkingChargeCalculator() {
	}

	/**
	 * Calculates charge based on the time spent by a car in a parking slot.
	 *
	 * @param timeSpent the time spent
	 * @return the total parking charge.
	 */
	public static int calculateCharge(int timeSpent) {
		int additionalCharge = timeSpent < MINIMUM_HOURS_CHARGED ? 0 : ADDITIONAL_HOUR_CHARGE * (timeSpent - MINIMUM_HOURS_CHARGED);
		return BASE_CHARGE + additionalCharge;
	}

}
