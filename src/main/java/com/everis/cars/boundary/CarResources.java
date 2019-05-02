package com.everis.cars.boundary;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import javax.ws.rs.core.Response.Status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.everis.cars.control.CarService;
import com.everis.cars.entity.Car;
import com.everis.cars.exceptions.CarNotFoundException;
import com.everis.cars.utils.ValidatorUtil;



@Path("cars")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CarResources {
	
	private final static Logger LOGGER = LogManager.getLogger(CarResources.class);
	
	@EJB
	CarService carService;

	@GET	
	public Response getCars() {
		
		LOGGER.info("Retrieving Car's List from Service: ");
		List<Car> cars = carService.getCars();
		LOGGER.info("Car's List Retrieved");
		return Response.ok().entity(cars).build();
		
	}

	@GET
	@Path("/{id}")
	public Response getCarById(final @PathParam("id") int id) {
		
		try {
			
			LOGGER.info("Getting Car by its Id: "+ id);
			return Response.ok().entity(carService.getCarById(id)).build();

		} catch (CarNotFoundException e) {
			LOGGER.error("Car with id " + id + "Not Found");
			return Response.status(Status.NOT_FOUND).entity("Car with id "+ id +" not found").build();
		}

	}

	@POST
	public Response createCar(final Car car) {

		LOGGER.info("Creating Car: ");
		final ArrayList<String> validatorsErrors = ValidatorUtil.validate(car);
		if (validatorsErrors.isEmpty()) {
			LOGGER.info("Car" + car + "Created");
			return Response.status(Status.CREATED).entity(carService.createCar(car)).build();
		} else {
			LOGGER.error("Failed to create new car");
			return Response.status(Status.BAD_REQUEST).entity(validatorsErrors).build();
		}

	}

	@PUT
	@Path("/{id}")
	public Response updateCar(final @PathParam("id") int id, final Car car) {
		
		LOGGER.info("Updating Car: ");
		final ArrayList<String> validatorsErrors = ValidatorUtil.validate(car);
		if (validatorsErrors.isEmpty()) {
			try {
				carService.updateCar(id, car);
				LOGGER.info(car + "with id: " + id + "Updated");
				return Response.ok().entity(car).build();
			} catch (CarNotFoundException e) {
				LOGGER.error("Car " + car + "with id: " + id + "Not Found");
				return Response.status(Status.NOT_FOUND).entity(validatorsErrors).build();
			}

		} else {
			LOGGER.error("Failed to update car with id "+ id);
			return Response.status(Status.BAD_REQUEST).entity(validatorsErrors).build();
		}
	}

	@DELETE
	@Path("/{id}")
	public Response deleteCar(final @PathParam("id") int id) {
		try {
			carService.deleteCar(id);
			LOGGER.info("Car with id: " + id + "Deleted");
			return Response.ok().entity("Car Deleted Successfully").build();
		} catch (CarNotFoundException e) {
			LOGGER.error("Failed to delete Car with id: " + id);
			return Response.status(Status.NOT_FOUND).entity("Car with id "+ id +" not found").build();
		}
	}
}
