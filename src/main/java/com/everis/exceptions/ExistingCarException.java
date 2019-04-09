package com.everis.exceptions;

import java.lang.Exception;



@SuppressWarnings("serial")
public class ExistingCarException extends Exception {
	private final int carId;

	public ExistingCarException(int carId) {
		super();
		this.carId = carId;
	}
	
	public String getMessage() {
		return "Already existing " +this.carId+ " car identificator";
	}
}
