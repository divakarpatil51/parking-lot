package com.coditas.model;

public class Slot {

	private int slotNumber;
	private VehicleDetails parkedVehicleDetails;

	/**
	 * @return the slotNumber
	 */
	public int getSlotNumber() {
		return slotNumber;
	}

	/**
	 * @param slotNumber the slotNumber to set
	 */
	public void setSlotNumber(int slotNumber) {
		this.slotNumber = slotNumber;
	}

	/**
	 * @return the vehicleMetadata
	 */
	public VehicleDetails getParkedVehicleDetails() {
		return parkedVehicleDetails;
	}

	/**
	 * @param vehicleMetadata the vehicleMetadata to set
	 */
	public void parkVehicle(VehicleDetails vehicleMetadata) {
		this.parkedVehicleDetails = vehicleMetadata;
	}

	/**
	 * Frees the parking slot.
	 */
	public void unparkVehicle() {
		this.parkedVehicleDetails = null;
	}

	/**
	 * Checks if the slot is free.
	 *
	 * @return true, if slot is free
	 */
	public boolean isSlotFree() {
		return this.parkedVehicleDetails == null;
	}

}
