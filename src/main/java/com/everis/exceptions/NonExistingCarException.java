package com.everis.exceptions;

public class NonExistingCarException extends Exception{
	private final int carId;

	public NonExistingCarException(int carId) {
		super();
		this.carId = carId;
	}
	
	public String getMessage() {
		return this.carId + " identificator does not exist";
	}
	
	
}
