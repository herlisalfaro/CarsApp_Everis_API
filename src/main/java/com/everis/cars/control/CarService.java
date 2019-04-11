package com.everis.cars.control;

import javax.persistence.*;
import javax.ejb.Stateless;

import com.everis.cars.entity.Car;
import java.util.List;
import com.everis.exceptions.*;


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
		 List <Car> listCars = em.createNamedQuery("Car.findAll", Car.class).getResultList();
		 return listCars;
	}
	
	
	/**
	 * Method to get one Car Entity's info using its id 
	 * 
	 * @param {com.everis.cars.Car} [id] Car Entity's id
	 * @return {com.everis.cars.CarService} [oneCar] Car Entity found by its id
	 */
	public List <Car> getCar(final int id) {
		List <Car> oneCar = em.createNamedQuery("Car.findById", Car.class).getResultList();
		return  oneCar; 
		
	}
	
	/**
	 * Method to create Car Entities
	 * 
	 * @param {com.everis.cars.Car} [car] Created Car Entity data
	 * @return {com.everis.cars.Car} Created Car Entity merged into the Entity Manager
	 */
	public Car createCar(final Car car)  {
		em.persist(car);
		return car;
	}
	
	
	/**
	 * Method to update Car Entities using its id 
	 * 
	 * @param {com.everis.cars.Car} [car] Updated Car Entity data
	 * @return {com.everis.cars.Car} Updated Car Entity merged into the Entity Manager
	 * @throws DuplicatedCarException Car Entity's id duplication
	 */
	public Car updateCar(final Car car) throws CarNotFoundException {
		List<Car> rList = em.createNamedQuery("Car.findById",Car.class).setParameter("id", car.getId()).getResultList();
		if(rList.size() > 0) {
			em.persist(car);
			return car;
		}else {
			throw new CarNotFoundException(car.getId()); 
		}
	}
	
	/**
	 * Method to delete Car Entities using its id 
	 * 
	 * @param {com.everis.cars.Car} [id] Car Entity's id 
	 * @throws CarNotFoundException Car Entity's id not found 
	 */
	public void deleteCar(final int id) throws CarNotFoundException {
		List<Car> carId = em.createNamedQuery("Car.findById", Car.class).getResultList();
		if(carId != null){
			em.remove(id);
		}else {
			throw new CarNotFoundException(id);
		}
		
		
		
	}
	


}


