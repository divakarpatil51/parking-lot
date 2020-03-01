package com.coditas.parking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.coditas.exception.InvalidInputException;

/**
 * The Class ParkinglotManagerTest.
 */
public class ParkinglotManagerTest {

	private ParkingLotManager manager = new ParkingLotManager();

	private static final PrintStream sysOut = System.out;
	private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@BeforeAll
	public static void init() throws UnsupportedEncodingException {
		System.setOut(new PrintStream(outContent, true, "UTF-8"));
	}

	@BeforeEach
	public void initBeforeEach() {
		outContent.reset();
	}

	/**
	 * Test parking slot creation success scenario.
	 */
	@Test
	public void testParkingSlotCreationSuccess() {
		manager.createParkingLot(6);
		assertEquals("Created parking lot with 6 slots\r\n", outContent.toString());
	}

	/**
	 * Test parking slot creation failure scenario.
	 */
	@Test
	public void testParkingSlotCreationFailure() {
		assertThrows(InvalidInputException.class, () -> manager.createParkingLot(0),
				() -> "Please provide valid size for the parking lot");
	}

	/**
	 * Test parking slot allocation if parking slot is available.
	 */
	@Test
	public void testParkingSlotAllocation() {
		manager.createParkingLot(1);
		outContent.reset();
		manager.allocateParkingSlot(new String[] { "park", "MH-01-EE-1111" });
		assertEquals("Allocated slot number: 1\r\n", outContent.toString());
	}

	/**
	 * Test parking slot allocation scenario if parking is full.
	 */
	@Test
	public void testParkingSlotAllocationIfParkingIsFull() {
		manager.createParkingLot(1);
		manager.allocateParkingSlot(new String[] { "park", "MH-01-EE-1111" });
		outContent.reset();
		manager.allocateParkingSlot(new String[] { "park", "MH-01-EE-1112" });
		assertEquals("Sorry, parking lot is full\r\n", outContent.toString());
	}

	/**
	 * Test parking slot allocation scenario if parking is not created.
	 */
	@Test
	public void testParkingSlotAllocationFailure() {
		assertThrows(InvalidInputException.class,
				() -> manager.allocateParkingSlot(new String[] { "park", "MH-01-EE-1111" }),
				() -> "Please create parking lot first with command: create_parking_lot <size>");
	}

	/**
	 * Test parking slot deallocation.
	 */
	@Test
	public void testParkingSlotDeallocation() {
		manager.createParkingLot(1);
		manager.allocateParkingSlot(new String[] { "park", "MH-01-EE-1111" });
		outContent.reset();

		manager.leaveParkingSlot(new String[] { "leave", "MH-01-EE-1111", "4" });
		assertEquals("Registration number MH-01-EE-1111 with Slot Number 1 is free with Charge 30\r\n",
				outContent.toString());
	}

	/**
	 * Test parking slot deallocation for invalid car details.
	 */
	@Test
	public void testParkingSlotDeallocationForInvalidCarDetails() {
		manager.createParkingLot(1);
		outContent.reset();

		manager.leaveParkingSlot(new String[] { "leave", "MH-01-EE-1112", "4" });
		assertEquals("Registration number MH-01-EE-1112 not found\r\n", outContent.toString());
	}

	/**
	 * Test parking slot current status.
	 */
	@Test
	public void testParkingSlotCurrentStatus() {
		manager.createParkingLot(1);
		manager.allocateParkingSlot(new String[] { "park", "MH-01-EE-1111" });
		outContent.reset();

		manager.printStatus();
		assertEquals("Slot No. Registration No.\r\n" + "1	MH-01-EE-1111\r\n", outContent.toString());
	}

	@AfterAll
	public static void destroy() {
		System.setOut(sysOut);
	}
}
