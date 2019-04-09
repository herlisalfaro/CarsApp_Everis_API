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
	
	public List<Car> getCars() {
		 List <Car> listCars = em.createNamedQuery("Car.findAll", Car.class).getResultList();
		 return listCars;
	}
	
	public Car getCar(final int id) {
		Car car = em.find(Car.class, id);
		return  car; 
		
	}
	
	public Car createCar(final Car car) throws CarNotFoundException {
		@SuppressWarnings("unchecked")
		List <Car> listCars = em.createNamedQuery("Car.findById").setParameter("id", car.getId()).getResultList();
		if(!listCars.contains(car)) {
			em.persist(car);
			return car;		
		}else {
			throw new CarNotFoundException(car.getId());
		}
		
	}
	
	public Car updateCar(final Car car) throws ExistingCarException {
		@SuppressWarnings("unchecked")
		List<Car> rList = em.createNamedQuery("Car.findById").setParameter("id", car.getId()).getResultList();
		if(rList.size() > 0) {
			em.persist(car);
			return car;
		}else {
			throw new ExistingCarException(car.getId()); 
		}
	}
	
	public void deleteCar(final int id) throws NonExistingCarException {
		Car carId = em.find(Car.class, id);
		if(carId != null){
			em.remove(id);
		}else {
			throw new NonExistingCarException(id);
		}
		
		
		
	}
	


}


