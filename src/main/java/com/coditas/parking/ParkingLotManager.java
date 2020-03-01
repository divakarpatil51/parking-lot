package com.coditas.parking;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

import com.coditas.car.CarMetadata;
import com.coditas.exception.InvalidInputException;
import com.coditas.utilities.ParkingChargeCalculator;

/**
 * Class to perform operations on Parking lot.
 */
public class ParkingLotManager {

	private CarMetadata[] carMetadata;
	private int availableSlots;

	/**
	 * Creates the parking lot.
	 *
	 * @param size the size
	 */
	public void createParkingLot(int size) {
		if (size <= 0) {
			throw new InvalidInputException("Please provide valid size for the parking lot");
		}
		this.availableSlots = size;
		carMetadata = new CarMetadata[size];
		System.out.println("Created parking lot with " + size + " slots");
	}

	/**
	 * Handles the allocation of a parking slot.
	 *
	 * @param params the parameters
	 */
	public void allocateParkingSlot(String[] params) {
		validateCarMetadata();

		if (isParkingFull()) {
			System.out.println("Sorry, parking lot is full");
			return;
		}

		CarMetadata carData = new CarMetadata();
		carData.setRegistrationNumber(params[1]);
		int freeSlot = getNearestFreeSlot();
		carData.setSlotNumber(freeSlot + 1);
		carMetadata[freeSlot] = carData;
		System.out.println("Allocated slot number: " + carData.getSlotNumber());
		availableSlots--;
	}

	/**
	 * Handles the deallocation of a parking slot.
	 *
	 * @param params the parameters
	 */
	public void leaveParkingSlot(String[] params) {
		validateCarMetadata();

		String registrationNumber = params[1];
		Optional<CarMetadata> car = Arrays.stream(carMetadata)
				.filter(carData -> carData != null && carData.getRegistrationNumber().equals(registrationNumber))
				.findFirst();

		if (!car.isPresent()) {
			System.out.println("Registration number " + registrationNumber + " not found");
			return;
		}

		CarMetadata carData = car.get();
		int freedSlot = carData.getSlotNumber();
		int charge = ParkingChargeCalculator.calculateCharge(Integer.valueOf(params[2]));
		carMetadata[freedSlot - 1] = null;

		System.out.println("Registration number " + registrationNumber + " with Slot Number " + freedSlot
				+ " is free with Charge " + charge);

		availableSlots++;
	}

	/**
	 * Prints the status.
	 */
	public void printStatus() {
		validateCarMetadata();
		System.out.println("Slot No. Registration No.");
		Arrays.stream(carMetadata).filter(Objects::nonNull).forEach(System.out::println);
	}

	/**
	 * Validates car metadata.
	 */
	private void validateCarMetadata() {
		if (carMetadata == null) {
			throw new InvalidInputException("Please create parking lot first with command: create_parking_lot <size>");
		}
	}

	/**
	 * Checks if parking is full.
	 *
	 * @return true, if parking is full
	 */
	private boolean isParkingFull() {
		return availableSlots == 0;
	}

	/**
	 * Calculates the nearest slot based on the current parking status.
	 *
	 * @return the nearest free slot
	 */
	private int getNearestFreeSlot() {
		for (int slot = 0; slot < carMetadata.length; slot++) {
			if (carMetadata[slot] == null) {
				return slot;
			}
		}
		return -1;
	}

}
