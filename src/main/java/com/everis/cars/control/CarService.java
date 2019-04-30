package com.everis.cars.control;

import javax.persistence.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Stateless;

import com.everis.cars.entity.Car;
import com.everis.cars.exceptions.*;


import java.util.List;

@Stateless
public class CarService {

	private final static Logger LOGGER = LogManager.getLogger(CarService.class);
	
	@PersistenceContext(unitName = "em_postgre")
	private EntityManager em;

	/**
	 * Method to get a list of Car Entities available
	 * 
	 * @return {com.everis.cars.CarService} [listCars] List of Car Entities
	 */
	public List<Car> getCars() {
		
		LOGGER.info("Starting process for 'getCars()' : ");
		List<Car> listCars = em.createNamedQuery("Car.findAll", Car.class).getResultList();
		LOGGER.info("Car's List completed: " + listCars);
		return listCars;
		
	}

	/**
	 * Method to get one Car Entity's info using its id
	 * 
	 * @param {com.everis.cars.Car} [id] Car Entity's id
	 * @return {com.everis.cars.CarService} [oneCar] Car Entity found by its id
	 * @throws CarNotFoundException Throws the exception if there's no car with the sent id
	 */
	public Car getCarById(final int id) throws CarNotFoundException {

		LOGGER.info("Starting process for 'getCar()' : ");
		Car oneCar = em.find(Car.class, id);
		if (oneCar != null) {
			LOGGER.info("Car selected: "+ oneCar);
			return oneCar;
			

		} else {
			LOGGER.error("CarNotFoundException for id " + id + " thrown");
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
		
		LOGGER.info("Starting process for 'createCar()' : ");
		em.persist(car);
		LOGGER.info("Created Car: " + car);
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
		
		LOGGER.info("Starting process for 'updateCar()' : ");
		getCarById(id);
		em.merge(car);
		LOGGER.info("Updated Car: " + car);
		return car;

	}

	/**
	 * Method to delete Car Entities using its id
	 * 
	 * @param {com.everis.cars.Car} [id] Car Entity's id to be deleted
	 * @throws CarNotFoundException Car Entity's id not found
	 */
	public void deleteCar(final int id) throws CarNotFoundException {
		
		LOGGER.info("Starting process for 'updateCar()' : ");
		final Car oneCar = getCarById(id);
		LOGGER.info("Car's Id chosen for delete: " + id);
		em.remove(oneCar);
		LOGGER.info("Deleted Car: " + oneCar);
	}

}
