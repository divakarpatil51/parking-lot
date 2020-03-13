package com.parkingticketingsystem.parking;

import java.util.Optional;

import com.parkingticketingsystem.model.VehicleDetails;
import com.parkingticketingsystem.model.VehicleLeaveResponse;

/**
 * The Interface ParkingLotManager.
 */
public interface ParkingLotManager {

	/**
	 * Creates the parking lot.
	 *
	 * @param size the size
	 */
	void createParkingLot(int size);

	/**
	 * Allocate parking slot.
	 *
	 * @param carData the car data
	 * @return slotNumber assigned to the car. If no slot present, then returns -1
	 */
	int allocateParkingSlot(VehicleDetails carData);

	/**
	 * Handles the deallocation of a parking slot.
	 *
	 * @param registrationNumber the registration number
	 * @param timeSpent          the time spent
	 * @return the vehicle metadata.
	 */
	Optional<VehicleLeaveResponse> leaveParkingSlot(String registrationNumber, int timeSpent);

	/**
	 * Prints the current status of the parking lot.
	 */
	void printStatus();

}
