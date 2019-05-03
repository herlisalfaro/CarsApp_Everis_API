package com.everis.cars.boundary;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import com.everis.cars.control.CarService;
import com.everis.cars.entity.Car;
import com.everis.cars.exceptions.CarNotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class CarResourcesTest {

	@InjectMocks
	CarResources carResources;

	@Mock
	CarService carService;

	@Test
	public void whenGettingCarCollection_returnOk() {

		final List<Car> cars = new ArrayList<>();

		// Mocks
		Mockito.when(carService.getCars()).thenReturn(cars);

		// Call method
		final Response response = carResources.getCars();

		// Assertions
		Assert.assertEquals(Status.OK.getStatusCode(), response.getStatus());
		Assert.assertEquals(cars, response.getEntity());

	}

	@Test
	public void whenGettingCarById_returnOk() throws CarNotFoundException{

		final Car oneCar = new Car();
		oneCar.setBrand("Cool_car");
		oneCar.setCountry("Germany");

		Mockito.when(carService.getCarById(1)).thenReturn(oneCar);
		final Response response = carResources.getCarById(1);

		Assert.assertNotNull(oneCar);
		Assert.assertEquals(Status.OK.getStatusCode(), response.getStatus());
		Assert.assertEquals(oneCar, response.getEntity());

	}

	@Test
	public void whenWrongIdChosen_returnCarNotFoundEx() {

		Mockito.when(carResources.getCarById(0)).thenThrow(CarNotFoundException.class);
		final Response response = carResources.getCarById(0);

		Assert.assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());

	}

	@Ignore
	@Test
	public void whenCreatingValidCar_returnOk() {

		final Car car = new Car();
		car.setBrand("BMW");
		car.setCountry("Germany");

		final ArrayList<String> errors = new ArrayList<>();
		errors.add("el tamaño tiene que estar entre 5 y 20");

		// Mocks
		Mockito.when(carService.createCar(car)).thenReturn(car);
		// Mockito.when(validatorUtil.validate(car)).thenReturn(errors);

		// Call method
		final Response response = carResources.createCar(car);

		// Assertions
		Assert.assertEquals(Status.CREATED.getStatusCode(), response.getStatus());

	}

	// @Test
	// void whenCreatingInvalidCar_returnBadRequest() {
	// carResources.createCar(invalidCar);
	// }

}
