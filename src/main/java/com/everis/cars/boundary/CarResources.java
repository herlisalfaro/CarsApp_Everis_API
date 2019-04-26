package com.everis.cars.boundary;

import java.util.ArrayList;

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
<<<<<<< HEAD
import javax.ws.rs.core.Response.ResponseBuilder;
=======

>>>>>>> 4398eea9a065ca11e858f212f463ed7e7c063c84
import javax.ws.rs.core.Response.Status;

import com.everis.cars.control.CarService;
import com.everis.cars.validatorUtils.*;
import com.everis.cars.entity.Car;
import com.everis.cars.exceptions.CarNotFoundException;
import com.everis.cars.utils.ValidatorUtil;


@Path("cars")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CarResources {
	
	@EJB
	CarService carService;

	@GET
	public Response getCars() {
		return Response.ok().entity(carService.getCars()).build();
	}

	@GET
	@Path("/{id}")
<<<<<<< HEAD
	public Response getCar(@PathParam("id") int carId) {
		final ArrayList<String> validatorsErrors = ValidatorUtil.validate(carId); 
		if(validatorsErrors.isEmpty()) {
			return Response.ok().entity(carService.getCar(carId)).build();
		}else {
			return Response.status(Status.BAD_REQUEST).build(); 
			  }
	
=======
	public Response getCar(final @PathParam("id") int id) {
		try {
			return Response.ok().entity(carService.getCar(id)).build();

		} catch (CarNotFoundException e) {
			return Response.status(Status.NOT_FOUND).build();
		}

>>>>>>> 4398eea9a065ca11e858f212f463ed7e7c063c84
	}

	@POST
<<<<<<< HEAD
	public Response createCar(Car car) {
		final ArrayList<String> validatorsErrors = ValidatorUtil.validate(car); 
		if(validatorsErrors.isEmpty()) {
			return Response.status(Status.CREATED).entity(carService.createCar(car)).build(); 
		}else {
			return Response.status(Status.BAD_REQUEST).build(); 	
			  }
		
		
=======
	public Response createCar(final Car car) {

		final ArrayList<String> validatorsErrors = ValidatorUtil.validate(car);
		if (validatorsErrors.isEmpty()) {
			return Response.status(Status.CREATED).entity(carService.createCar(car)).build();
		} else {
			return Response.status(Status.BAD_REQUEST).build();
		}

>>>>>>> 4398eea9a065ca11e858f212f463ed7e7c063c84
	}

	@PUT
	@Path("/{id}")
	public Response updateCar(final @PathParam("id") int id, final Car car) {
		final ArrayList<String> validatorsErrors = ValidatorUtil.validate(car);
		if (validatorsErrors.isEmpty()) {
			try {
				car.setId(id);
				carService.updateCar(id, car);
				return Response.ok().entity("Car Updated Succesfully").build();
			} catch (CarNotFoundException e) {
				return Response.status(Status.NOT_FOUND).build();
			}

		} else {
			return Response.status(Status.BAD_REQUEST).build();
		}
	}

	@DELETE
	@Path("/{id}")
<<<<<<< HEAD
	public Response deleteCar(@PathParam("id") int carId) {
			final ArrayList<String> validatorsErrors = ValidatorUtil.validate(carId); 
			if(validatorsErrors.isEmpty()) {
				return Response.ok().entity(carService.deleteCar(carId)).build();
				
		}
}

=======
	public Response deleteCar(final @PathParam("id") int id) {
		try {
			carService.deleteCar(id);
			return Response.ok().entity("Car Deleted Successfully").build();
		} catch (CarNotFoundException e) {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
>>>>>>> 4398eea9a065ca11e858f212f463ed7e7c063c84
}
