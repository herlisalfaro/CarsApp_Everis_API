package com.everis.cars.boundary;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/cars")
public class CarResources {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getAllCars() {
		return null;
	
	}
	
	
}
