package com.everis.exceptions;

import java.lang.Exception;

@SuppressWarnings("serial")
public class CarNotFoundException extends Exception {
	private final int carId;

	public CarNotFoundException(int carId) {
		super();
		this.carId = carId;
	}
	public String getMessage() {
		return "Car with id " +this.carId+ " not found";
	}
	
	
}
