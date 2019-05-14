package com.everis.cars.control;

import java.sql.Timestamp;
import java.util.List;

import javax.ejb.Stateless;
import javax.jms.Message;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import com.everis.cars.entity.Car;
import com.everis.cars.exceptions.CarNotFoundException;
import com.everis.cars.boundary.CarMessageConsumer;
import com.everis.cars.boundary.CarMessageProducer;

@Stateless
public class CarService {

    private final static Logger LOGGER = Logger.getLogger(CarService.class);

    @PersistenceContext(unitName = "em_postgre")
    private EntityManager em;

    /**
     * Method to get a list of Car Entities available
     * 
     * @return {com.everis.cars.CarService} [listCars] List of Available Cars
     */
    public List<Car> getCars() {

	LOGGER.info("Getting all Cars' List: ");
	List<Car> listCars = em.createNamedQuery("Car.findAll", Car.class).getResultList();
	LOGGER.info("Car's List completed: " + listCars);
	return listCars;

    }

    /**
     * Method to get one Car Entity's info using its id
     * 
     * @param {com.everis.cars.Car} [id] Car Entity's id
     * @return {com.everis.cars.CarService} [oneCar] Car Entity found by its id
     * @throws CarNotFoundException Throws the exception if there's no car with the
     *                              sent id
     */
    public Car getCarById(final int id) throws CarNotFoundException {

	LOGGER.info("Getting Car's Id... ");
	Car oneCar = em.createNamedQuery("Car.findById", Car.class).setParameter("id", id).getSingleResult();
	if (oneCar != null) {
	    LOGGER.info("Car selected: " + oneCar);
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

	LOGGER.info("Creating Car... ");
	em.persist(car);
	LOGGER.info("Created Car: " + car);
	return car;
    }

    /**
     * Method to update Car Entities using its id
     * 
     * @param {com.everis.cars.Car} [id] Car Entity's id to be updated
     * @param {com.everis.cars.Car} [car] Car Entity itself to be updated
     * @return {com.everis.cars.Car} Updated Car merged into the Entity Manager
     * @throws CarNotFoundException Car Entity's id not found
     */
    public Car updateCar(final int id, Car car) throws CarNotFoundException {

	LOGGER.info("Updating Car...");
	em.merge(car);
	LOGGER.info("Selected Car for Updating: " + car);
	return car;

    }
    
    

    /**
     * Method to mark cars to be deleted
     * 
     * @param {com.everis.cars.Car} [id] Car Entity's id to be marked
     * @return {com.everis.cars.Car} [oneCar] Car Entity already marked
     * @throws CarNotFoundException Car Entity's id not found
     */
    public Car softDeleteCar(final int id) throws CarNotFoundException {

	Timestamp currentDate = new Timestamp(System.currentTimeMillis());

	final Car oneCar = getCarById(id);
	LOGGER.info("Softdeleting Car with Id: " + id);
	oneCar.setSoftDeleted(true);
	oneCar.setSoftDeleted_at(currentDate);
	em.merge(oneCar);
	LOGGER.info("SoftDeleting Info: " + oneCar);
	return oneCar;

    }
    
    /**
     * Method to get cars that must be completely removed from application
     * 
     * @return {com.everis.cars.CarService} [listDeletedCars] List of Cars ready to be removed
     */
    public List<Car> getReadyForDeletion() {

   	LOGGER.info("Getting all Cars' Ready for Deletion: ");
   	List<Car> listDeletedCars = em.createNamedQuery("Car.findAllDeleted", Car.class).getResultList();
   	LOGGER.info("Cars Ready for Deletion: " + listDeletedCars);
   	return listDeletedCars;

       }

    /**
     * Method to delete Car Entities using its id
     * 
     * @param {com.everis.cars.Car} [id] Car Entity's id to be deleted
     * @throws CarNotFoundException Car Entity's id not found
     */
    public void hardDeleteCar(final int id) throws CarNotFoundException {

	LOGGER.info("Deleting Car... ");
	final Car oneCar = getCarById(id);
	LOGGER.info("Car's Id chosen for delete: " + id);
	em.remove(oneCar);
	LOGGER.info("Deleted Car: " + oneCar);
    }

}
