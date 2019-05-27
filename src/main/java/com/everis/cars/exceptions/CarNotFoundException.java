package com.everis.cars.exceptions;

import javax.persistence.NoResultException;

@SuppressWarnings("serial")
public class CarNotFoundException extends NoResultException {
	private final int carId;


	public CarNotFoundException(int carId) {
		super();
		this.carId = carId;
	}
	

	/* (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	public String getMessage() {
		return "Car with id " + this.carId + " not found";
	}
	
	
	
}
