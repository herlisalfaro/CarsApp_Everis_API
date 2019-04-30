package com.everis.cars.control;

import javax.persistence.*;
import javax.ejb.Stateless;

import com.everis.cars.entity.Car;
import com.everis.cars.exceptions.*;

import java.util.List;

@Stateless
public class CarService {

	@PersistenceContext(unitName = "em_postgre")
	private EntityManager em;

	/**
	 * Method to get a list of Car Entities available
	 * 
	 * @return {com.everis.cars.CarService} [listCars] List of Car Entities
	 */
	public List<Car> getCars() {
		List<Car> listCars = em.createNamedQuery("Car.findAll", Car.class).getResultList();
		return listCars;
	}

	/**
	 * Method to get one Car Entity's info using its id
	 * 
	 * @param {com.everis.cars.Car} [id] Car Entity's id
	 * @return {com.everis.cars.CarService} [oneCar] Car Entity found by its id
	 * @throws CarNotFoundException Throws the exception if there's no car with the sent id
	 */
	public Car getCar(final int id) throws CarNotFoundException {

		Car oneCar = em.find(Car.class, id);
		if (oneCar != null) {
			return oneCar;

		} else {
			throw new CarNotFoundException(id);
			
		}

	}


	/**
	 * Method to create Car Entities
	 * 
	 * @param {com.everis.cars.Car} [car] Created Car Entity data
	 * @return {com.everis.cars.Car} Created Car Entity merged into the Entity
	 *         Manager
	 */
	public Car createCar(final Car car) {
		em.persist(car);
		return car;
	}

	/**
	 * Method to update Car Entities using its id
	 * 
	 * @param {com.everis.cars.Car} [id] Car Entity's id to be deleted
	 * @param {com.everis.cars.Car} [car] Car Entity itself to be updated
	 * @return {com.everis.cars.Car} Updated Car merged into the Entity
	 *         Manager
	 * @throws CarNotFoundException Car Entity's id not found
	 */
	public Car updateCar(final int id, final Car car) throws CarNotFoundException {
		getCar(id);
		em.merge(car);
		return car;

	}

	/**
	 * Method to delete Car Entities using its id
	 * 
	 * @param {com.everis.cars.Car} [id] Car Entity's id to be deleted
	 * @throws CarNotFoundException Car Entity's id not found
	 */
	public void deleteCar(final int id) throws CarNotFoundException {
		final Car oneCar = getCar(id);
		em.remove(oneCar);
	}

}
