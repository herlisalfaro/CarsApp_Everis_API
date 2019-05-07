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

import org.apache.log4j.Logger;

import com.everis.cars.control.CarService;
import com.everis.cars.entity.Car;
import com.everis.cars.exceptions.CarNotFoundException;
import com.everis.cars.utils.ValidatorUtil;

import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Path("cars")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@OpenAPIDefinition(info = @Info(title = "CarsApp_API", version = "0.0", description = "Car' CRUD Functionality"))
public class CarResources {

	private final static Logger LOGGER = Logger.getLogger(CarResources.class);

	@EJB
	CarService carService;

	@GET
	@Operation(description = "Get a list of cars")
	@ApiResponse(responseCode = "200", description = "Returns List of Cars Available")
	public Response getCars() {

		LOGGER.info("Retrieving Car's List from Service: ");
		List<Car> cars = carService.getCars();
		LOGGER.info("Car's List Retrieved");
		return Response.ok().entity(cars).build();

	}

	@GET
	@Path("/{id}")
	@Operation(description = "Pick a Car by its Id", responses = {
			@ApiResponse(responseCode = "200", description = "Returns an entity Car by its Id"),
			@ApiResponse(responseCode = "404", description = "Car with such id doesn't exists") })
	@Parameter(description = "Car's Id selected by the user", required = true)
	public Response getCarById(final @PathParam("id") int id) {

		try {

			LOGGER.info("Getting Car by its Id: " + id);
			return Response.ok().entity(carService.getCarById(id)).build();

		} catch (CarNotFoundException e) {
			LOGGER.error("Car with id " + id + " Not Found");
			return Response.status(Status.NOT_FOUND).entity("Car with id " + id + " not found").build();
		}

	}

	@POST
	@Operation(description = "Create new car", responses = {
			@ApiResponse(responseCode = "200", description = "Car has been created"),
			@ApiResponse(responseCode = "400", description = "Could not create a new car") }

	)
	@Parameter(description = "Object Car to be created", required = true)
	public Response createCar(final Car car) {

		LOGGER.info("Creating Car: ");
		final ArrayList<String> validatorsErrors = ValidatorUtil.validate(car);
		if (validatorsErrors.isEmpty()) {
			LOGGER.info("Car Created Info: " + car);
			return Response.status(Status.CREATED).entity(carService.createCar(car)).build();
		} else {
			LOGGER.error("Failed to create new car");
			return Response.status(Status.BAD_REQUEST).entity(validatorsErrors).build();
		}

	}

	@PUT
	@Path("/{id}")
	@Operation(description = "Update new car", responses = {
			@ApiResponse(responseCode = "200", description = "Car has been updated"),
			@ApiResponse(responseCode = "400", description = "Car cannot be udpated"),
			@ApiResponse(responseCode = "404", description = "Car with such id doesn't exists") }

	)
	@Parameter(description = "Car Id to be updated", required = true)
	@Parameter(description = "Object Car to be updated", required = true)
	public Response updateCar(final @PathParam("id") int id, final Car car) {

		LOGGER.info("Updating Car: ");
		final ArrayList<String> validatorsErrors = ValidatorUtil.validate(car);
		if (validatorsErrors.isEmpty()) {
			try {
				carService.updateCar(id, car);
				LOGGER.info("Car Successfully Updated: " + car + "Id: " + id);
				return Response.ok().entity(car).build();
			} catch (CarNotFoundException e) {
				LOGGER.error("Car with id " + id + " Not Found");
				return Response.status(Status.NOT_FOUND).entity("Car with id " + id + " not found").build();
			}

		} else {
			LOGGER.error("Failed to update car with id " + id);
			return Response.status(Status.BAD_REQUEST).entity(validatorsErrors).build();
		}
	}

	@DELETE
	@Path("/{id}")
	@Operation(description = "Delete existing car", responses = {
			@ApiResponse(responseCode = "200", description = "Car has been deleted"),
			@ApiResponse(responseCode = "404", description = "Car with such id doesn't exists") }

	)
	@Parameter(description = "Car's Id selected by the user", required = true)
	public Response deleteCar(final @PathParam("id") int id) {

		try {
			carService.deleteCar(id);
			LOGGER.info("Car with id " + id + " Deleted");
			return Response.ok().entity("Car Deleted Successfully").build();
		} catch (CarNotFoundException e) {
			LOGGER.error("Failed to delete Car with id " + id);
			return Response.status(Status.NOT_FOUND).entity("Car with id " + id + " not found").build();
		}
	}
}
