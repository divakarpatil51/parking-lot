package com.coditas.utilities;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Test cases for {@code ParkingChargeCalculator} class.
 */
public class ParkingChargeCalculatorTest {

	/**
	 * Test parking charge for first 2 hours.
	 */
	@Test
	public void testParkingChargeForFirstTwoHours() {
		int timeSpent = 2;
		assertEquals(10, ParkingChargeCalculator.calculateCharge(timeSpent));
	}

	/**
	 * Test parking charge with additional charges.
	 */
	@Test
	public void testParkingChargeWithValidTime() {
		int timeSpent = 5;
		assertEquals(40, ParkingChargeCalculator.calculateCharge(timeSpent));
	}

}
