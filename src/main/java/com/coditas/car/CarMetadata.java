package com.coditas.car;

/**
 * Class to hold Car metadata.
 */
public class CarMetadata {

	private String registrationNumber;
	private String color;
	private int slotNumber;

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getSlotNumber() {
		return slotNumber;
	}

	public void setSlotNumber(int slotNumber) {
		this.slotNumber = slotNumber;
	}

	@Override
	public String toString() {
		return slotNumber + "\t" + registrationNumber;
	}

}
