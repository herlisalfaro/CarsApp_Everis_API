package com.everis.cars.boundary;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.core.classloader.annotations.SuppressStaticInitializationFor;
//import org.powermock.modules.junit4.rule.PowerMockRule;
import org.powermock.modules.junit4.rule.PowerMockRule;

import com.everis.cars.control.CarService;
import com.everis.cars.entity.Car;
import com.everis.cars.exceptions.CarNotFoundException;
import com.everis.cars.utils.ValidatorUtil;

//@RunWith(PowerMockRunner.class)
@PrepareForTest({ ValidatorUtil.class, CarResources.class })
@SuppressStaticInitializationFor("com.everis.cars.utils.ValidatorUtil")
public class CarResourcesTest {
    @Rule
    public PowerMockRule rule = new PowerMockRule();
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

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
    public void whenGettingCarById_returnOk() {

	final Car oneCar = new Car();
	oneCar.setBrand("Cool_car");
	oneCar.setCountry("Germany");

	try {
	    Mockito.when(carService.getCarById(1)).thenReturn(oneCar);
	    final Response response = carResources.getCarById(1);

	    Assert.assertNotNull(oneCar);
	    Assert.assertEquals(Status.OK.getStatusCode(), response.getStatus());
	    // Assert.assertEquals(oneCar, response.getEntity());
	} catch (CarNotFoundException e) {
	    Assert.fail();
	}

    }

    @Test
    public void whenWrongIdChosen_handlesCarNotFoundEx() {

	Mockito.when(carResources.getCarById(0)).thenThrow(CarNotFoundException.class);
	final Response response = carResources.getCarById(0);

	Assert.assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());

    }

    @Test
    public void whenDeletingRightCar_returnOk() {

	Mockito.mock(CarService.class);

	final Response response = carResources.deleteCar(1);

	Assert.assertEquals(Status.OK.getStatusCode(), response.getStatus());

    }

    @Test
    public void whenDeletingWithWrongId_handlesCarNotFoundEx() {

	Mockito.when(carResources.deleteCar(0)).thenThrow(CarNotFoundException.class);
	final Response response = carResources.deleteCar(0);

	Assert.assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());

    }

    @Test
    public void whenCreatingValidCar_returnOk() {

	final Car car = new Car();
	car.setBrand("BMW");
	car.setCountry("Germany");

	final ArrayList<String> errors = new ArrayList<>();

	// Mocks
	PowerMockito.mockStatic(ValidatorUtil.class);

	Mockito.when(ValidatorUtil.validate(car)).thenReturn(errors);
	Mockito.when(carService.createCar(car)).thenReturn(car);

	// Call method
	final Response response = carResources.createCar(car);

	// Assertions
	Assert.assertEquals(Status.CREATED.getStatusCode(), response.getStatus());
	Assert.assertEquals(car, response.getEntity());

    }

    @Test
    public void whenCreatingCarWithoutRequiredFields_returnBadRequest() {

	final Car car = new Car();
	car.setCountry("Germany");

	final ArrayList<String> errors = new ArrayList<>();
	errors.add("Brand Cannot Be Null");

	PowerMockito.mockStatic(ValidatorUtil.class);

	Mockito.when(ValidatorUtil.validate(car)).thenReturn(errors);

	final Response response = carResources.createCar(car);

	Assert.assertEquals(Status.BAD_REQUEST.getStatusCode(), response.getStatus());
	

    }

    @Test
    public void whenUpdatingRightCar_returnOk() {

	final int id = 1;
	final Car car = new Car();
	car.setBrand("Chachi_Way");
	car.setCountry("CarTown");

	final ArrayList<String> errors = new ArrayList<>();

	try {
	    PowerMockito.mockStatic(ValidatorUtil.class);
	    Mockito.when(ValidatorUtil.validate(car)).thenReturn(errors);
	    Mockito.when(carService.updateCar(id, car)).thenReturn(car);
	    final Response response = carResources.updateCar(id, car);
	    Assert.assertEquals(Status.OK.getStatusCode(), response.getStatus());
	    Assert.assertEquals(car, response.getEntity());
	} catch (CarNotFoundException e) {
	    Assert.fail();

	}

    }

    @Test
    public void whenUpdatingCarWithoutProperRequirements_returnsBadRequest() {
	final int id = 1;
	final Car car = new Car();
	car.setCountry("CarTown");
	final ArrayList<String> errors = new ArrayList<>();
	errors.add("Brand Cannot Be Null");
	
	PowerMockito.mockStatic(ValidatorUtil.class);
	Mockito.when(ValidatorUtil.validate(car)).thenReturn(errors);
	
	final Response response = carResources.updateCar(id,car);

	Assert.assertEquals(Status.BAD_REQUEST.getStatusCode(), response.getStatus());
	

    }
    @Test
    public void whenUpdatingCarWithWrongId_handlesCarNotFoundEx() {
	final int id = 0;
	final Car car = new Car();
	final ArrayList<String> errors = new ArrayList<>();
	
	
	PowerMockito.mockStatic(ValidatorUtil.class);
	Mockito.when(ValidatorUtil.validate(car)).thenReturn(errors);
	Mockito.when(carResources.updateCar(id, car)).thenThrow(CarNotFoundException.class);
	
	final Response response = carResources.updateCar(id, car);
	
	Assert.assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
	

    }

}
