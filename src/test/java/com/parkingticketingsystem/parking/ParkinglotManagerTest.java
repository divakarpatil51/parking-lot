package com.parkingticketingsystem.parking;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.util.Optional;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.parkingticketingsystem.exception.InvalidInputException;
import com.parkingticketingsystem.log.Logger;
import com.parkingticketingsystem.model.VehicleDetails;
import com.parkingticketingsystem.model.VehicleLeaveResponse;
import com.parkingticketingsystem.parking.ParkingLotManagerImpl;

/**
 * The Class ParkinglotManagerTest.
 */
public class ParkinglotManagerTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	private static final String REGISTRATION_NUMBER = "MH-01-EE-1111";

	@InjectMocks
	private ParkingLotManagerImpl manager;

	@Mock
	private Logger logger;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Test parking slot creation success scenario.
	 * 
	 * @throws IOException
	 */
	@Test
	public void testParkingSlotCreationSuccess() throws IOException {
		manager.createParkingLot(6);
		assertEquals(6, manager.getCarMetadata().length);
	}

	/**
	 * Test parking slot creation failure scenario.
	 */
	@Test
	public void testParkingSlotCreationFailure() {
		expectedException.expect(InvalidInputException.class);
		expectedException.expectMessage("Please provide valid size for the parking lot");
		manager.createParkingLot(0);
	}

	/**
	 * Test parking slot allocation if parking slot is available.
	 */
	@Test
	public void testParkingSlotAllocation() {
		manager.createParkingLot(1);
		manager.allocateParkingSlot(createCarData());

		assertNotNull(manager.getCarMetadata());
		assertEquals(REGISTRATION_NUMBER, manager.getCarMetadata()[0].getParkedVehicleDetails().getRegistrationNumber());
	}

	/**
	 * Test parking slot allocation scenario if parking is full.
	 */
	@Test
	public void testParkingSlotAllocationIfParkingIsFull() {
		manager.createParkingLot(1);
		VehicleDetails carData = createCarData();
		manager.allocateParkingSlot(carData);
		assertEquals(-1, manager.allocateParkingSlot(carData));
	}

	/**
	 * Test parking slot allocation scenario if parking is not created.
	 */
	@Test
	public void testParkingSlotAllocationFailure() {
		expectedException.expect(InvalidInputException.class);
		expectedException.expectMessage("Please create parking lot first with command: create_parking_lot <size>");
		manager.allocateParkingSlot(createCarData());
	}

	/**
	 * Test parking slot deallocation.
	 */
	@Test
	public void testParkingSlotDeallocation() {
		manager.createParkingLot(1);
		manager.allocateParkingSlot(createCarData());
		Optional<VehicleLeaveResponse> carData = manager.leaveParkingSlot(REGISTRATION_NUMBER, 4);

		assertTrue(carData.isPresent());
		assertEquals(1, carData.get().getSlotNumber());
	}

	/**
	 * Test parking slot deallocation for invalid car details.
	 */
	@Test
	public void testParkingSlotDeallocationForInvalidCarDetails() {
		manager.createParkingLot(1);
		Optional<VehicleLeaveResponse> carData = manager.leaveParkingSlot(REGISTRATION_NUMBER, 4);
		assertFalse(carData.isPresent());
	}

	/**
	 * Test parking slot current status.
	 */
	@Test
	public void testParkingSlotCurrentStatus() {
		ParkingLotManagerImpl.setLogger(logger);
		manager.createParkingLot(1);
		manager.allocateParkingSlot(createCarData());
		manager.printStatus();
		verify(logger, times(1)).info(Mockito.anyString());
	}

	private VehicleDetails createCarData() {
		VehicleDetails carData = new VehicleDetails();
		carData.setRegistrationNumber(REGISTRATION_NUMBER);
		return carData;
	}

}
