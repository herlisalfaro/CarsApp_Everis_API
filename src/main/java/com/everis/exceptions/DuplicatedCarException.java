package com.everis.exceptions;

import java.lang.Exception;



@SuppressWarnings("serial")
public class DuplicatedCarException extends Exception {
	private final int carId;

	public DuplicatedCarException(int carId) {
		super();
		this.carId = carId;
	}
	
	public String getMessage() {
		return "Duplicated indetificator: " +this.carId;
	}
}
