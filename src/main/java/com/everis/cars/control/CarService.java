package com.everis.cars.control;

import javax.persistence.*;
import javax.ejb.Stateless;

import com.everis.cars.entity.Car;
import java.util.List;

@Stateless
public class CarService {
	
	@PersistenceContext(unitName = "em_postgre")
	private EntityManager em;
	
	public void getCars() {
		 List <Car> listCars = em.createNamedQuery("Car.findAll", Car.class).getResultList();
	}
	
	public Car getCar(int id) {
		Car car = em.find(Car.class, id);
		return car; 
		
	}
	
	public Car createCar(Car car) {
		em.persist(car);
		return car;
	}
	
	public Car updateCar(Car car) {
		em.merge(car);
		return car;
	}
	
	public void deleteCar(int id) {
		em.remove(id);
	}
	


}


