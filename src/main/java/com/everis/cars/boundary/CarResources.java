package com.everis.cars.boundary;

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

import com.everis.cars.control.CarService;
import com.everis.cars.entity.Car;
import com.everis.exceptions.CarNotFoundException;

@Path("cars")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CarResources {
	@EJB
	CarService carService;
	
	@GET
	public Response getCars() {
		return Response.status(Status.ACCEPTED).entity(carService.getCars()).build(); 
	}
	
	@GET
	@Path("/{id}")
	public Response getCar(@PathParam("id") int id) {
		return Response.status(Status.ACCEPTED).entity(carService.getCar(id)).build();   
		
	
	}
	
	@POST
	public Response createCar(Car car) {
		return Response.status(Status.CREATED).entity(carService.createCar(car)).build(); 
	}
	
	@PUT
	@Path("/{id}")
	public Response updateCar(@PathParam("id") int id, Car car) {
		car.setId(id);
		try {
			return Response.status(Status.ACCEPTED).entity(carService.updateCar(car)).build();
		} catch (CarNotFoundException e) {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	    
	@DELETE
	@Path("/{id}")
	public Response deleteCar(@PathParam("id") int id) {
		try {
			carService.deleteCar(id);
			return Response.status(Status.ACCEPTED).build();
		} catch (CarNotFoundException e) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
	}
}
