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
	
	
	public List <Car> getCar(final int id) {
		List <Car> oneCar = em.createNamedQuery("Car.findById", Car.class).getResultList();
		return  oneCar; 
		
	}
	
	public Car createCar(final Car car) throws CarNotFoundException {
		List <Car> listCars = em.createNamedQuery("Car.findById", Car.class).setParameter("id", car.getId()).getResultList();
		if(!listCars.contains(car)) {
			em.persist(car);
			return car;		
		}else {
			throw new CarNotFoundException(car.getId());
		}
		
	}
	
	/* Description.
	 * @public
	 * @param {com.everis.cars.Car} [car] The Car Entity with the updated data	
	 * @returns {com.everis.cars.Car} The updated Car Entity, merged into the Entity Manager
	 **/
	public Car updateCar(final Car car) throws DuplicatedCarException {
		List<Car> rList = em.createNamedQuery("Car.findById",Car.class).setParameter("id", car.getId()).getResultList();
		if(rList.size() > 0) {
			em.persist(car);
			return car;
		}else {
			throw new DuplicatedCarException(car.getId()); 
		}
	}
	
	public void deleteCar(final int id) throws CarNotFoundException {
		List<Car> carId = em.createNamedQuery("Car.findById", Car.class).getResultList();
		if(carId != null){
			em.remove(id);
		}else {
			throw new CarNotFoundException(id);
		}
		
		
		
	}
	


}


