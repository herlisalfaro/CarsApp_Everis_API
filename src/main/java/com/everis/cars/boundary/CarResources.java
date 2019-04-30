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

import javax.ws.rs.core.Response.Status;

import com.everis.cars.control.CarService;
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
	public Response getCar(final @PathParam("id") int id) {
		try {
			return Response.ok().entity(carService.getCar(id)).build();

		} catch (CarNotFoundException e) {
			return Response.status(Status.NOT_FOUND).entity("Car with id "+ id +" not found").build();
		}

	}

	@POST
	public Response createCar(final Car car) {

		final ArrayList<String> validatorsErrors = ValidatorUtil.validate(car);
		if (validatorsErrors.isEmpty()) {
			return Response.status(Status.CREATED).entity(carService.createCar(car)).build();
		} else {
			return Response.status(Status.BAD_REQUEST).entity(validatorsErrors).build();
		}

	}

	@PUT
	@Path("/{id}")
	public Response updateCar(final @PathParam("id") int id, final Car car) {
		final ArrayList<String> validatorsErrors = ValidatorUtil.validate(car);
		if (validatorsErrors.isEmpty()) {
			try {
				carService.updateCar(id, car);
				return Response.ok().entity(car).build();
			} catch (CarNotFoundException e) {
				return Response.status(Status.NOT_FOUND).entity(validatorsErrors).build();
			}

		} else {
			return Response.status(Status.BAD_REQUEST).entity(validatorsErrors).build();
		}
	}

	@DELETE
	@Path("/{id}")
	public Response deleteCar(final @PathParam("id") int id) {
		try {
			carService.deleteCar(id);
			return Response.ok().entity("Car Deleted Successfully").build();
		} catch (CarNotFoundException e) {
			return Response.status(Status.NOT_FOUND).entity("Car with id "+ id +" not found").build();
		}
	}
}
