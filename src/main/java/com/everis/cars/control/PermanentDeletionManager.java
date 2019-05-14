package com.everis.cars.control;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Timer;

import org.apache.log4j.Logger;

import com.everis.cars.entity.Car;
import com.everis.cars.control.CarService;


@Singleton
public class PermanentDeletionManager {
    
    private final static Logger LOGGER = Logger.getLogger(CarService.class);

    
    @EJB
    private CarService service;
    
    @Schedule(second = "0", minute = "1", hour = "0")
    public final void purgeOldEntries(Timer timer) {
	
	LOGGER.info("Deleting Cars ready for permanent removal...");
	List<Car> forDeletion = service.getReadyForDeletion();
	final int id = 0;
	for(Car car : forDeletion) {
	    service.hardDeleteCar(id);
	    LOGGER.info("Car chosen for deleting: " + car);
	}
	
	LOGGER.info("Permanent Deleting Process Completed");
    }
}

