package com.parkingticketingsystem.parking;

import java.util.Arrays;
import java.util.Optional;

import com.parkingticketingsystem.exception.InvalidInputException;
import com.parkingticketingsystem.log.LogFactory;
import com.parkingticketingsystem.log.Logger;
import com.parkingticketingsystem.model.Slot;
import com.parkingticketingsystem.model.VehicleDetails;
import com.parkingticketingsystem.model.VehicleLeaveResponse;
import com.parkingticketingsystem.utilities.ParkingChargeCalculator;

/**
 * Class to perform operations on Parking lot.
 */
public class ParkingLotManagerImpl implements ParkingLotManager {

	private static Logger logger = LogFactory.getLogger();

	private Slot[] slots;

	@Override
	public void createParkingLot(int size) {
		if (size <= 0) {
			throw new InvalidInputException("Please provide valid size for the parking lot");
		}
		slots = new Slot[size];
		for (int slotNumber = 0; slotNumber < slots.length; slotNumber++) {
			Slot slot = new Slot();
			slot.setSlotNumber(slotNumber + 1);
			slots[slotNumber] = slot;
		}
	}

	@Override
	public int allocateParkingSlot(VehicleDetails vehicleDetails) {
		validateSlotsStatus();

		if (vehicleDetails == null || vehicleDetails.getRegistrationNumber() == null
				|| vehicleDetails.getRegistrationNumber().isEmpty()) {
			throw new InvalidInputException("Please provide valid data for the car");
		}

		Optional<Slot> freeSlot = getNearestFreeSlot();
		if (!freeSlot.isPresent()) {
			return -1;
		}

		Slot slot = freeSlot.get();
		slot.parkVehicle(vehicleDetails);
		return slot.getSlotNumber();
	}

	@Override
	public Optional<VehicleLeaveResponse> leaveParkingSlot(String registrationNumber, int timeSpent) {
		validateSlotsStatus();

		// Get the slot in which the vehicle is parked
		Optional<Slot> vehicleSlot = Arrays.stream(slots)
				.filter(slot -> !slot.isSlotFree()
						&& slot.getParkedVehicleDetails().getRegistrationNumber().equals(registrationNumber))
				.findFirst();

		if (!vehicleSlot.isPresent()) {
			return Optional.empty();
		}

		int freedSlot = vehicleSlot.get().getSlotNumber();
		slots[freedSlot - 1].unparkVehicle();

		int charge = ParkingChargeCalculator.calculateCharge(timeSpent);
		VehicleLeaveResponse response = new VehicleLeaveResponse();
		response.setParkingCharge(charge);
		response.setSlotNumber(freedSlot);

		return Optional.of(response);
	}

	@Override
	public void printStatus() {
		validateSlotsStatus();
		logger.info("Slot No. Registration No.");
		Arrays.stream(slots).filter(slot -> !slot.isSlotFree()).forEach(car -> logger.info("%d\t%s",
				car.getSlotNumber(), car.getParkedVehicleDetails().getRegistrationNumber()));
	}

	/**
	 * Validates car metadata.
	 */
	private void validateSlotsStatus() {
		if (slots == null) {
			throw new InvalidInputException("Please create parking lot first with command: create_parking_lot <size>");
		}
	}

	/**
	 * Calculates the nearest slot based on the current parking status.
	 *
	 * @return the nearest free slot
	 */
	private Optional<Slot> getNearestFreeSlot() {
		return Arrays.stream(slots).filter(Slot::isSlotFree).findFirst();
	}

	/**
	 * Visible for testing purpose
	 * 
	 * @return the carMetadata
	 */
	public Slot[] getCarMetadata() {
		return slots;
	}

	public static void setLogger(Logger logger) {
		ParkingLotManagerImpl.logger = logger;
	}

}
