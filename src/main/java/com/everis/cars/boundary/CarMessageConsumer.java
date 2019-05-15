package com.everis.cars.boundary;

import java.io.IOException;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.apache.log4j.Logger;

import com.everis.cars.entity.Car;
import com.fasterxml.jackson.databind.ObjectMapper;

@JMSDestinationDefinition(name = "jms/CarsApp_API_ManagementQueue", interfaceName = "javax.jms.Queue")
@MessageDriven(activationConfig = {
	@ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/CarsApp_API_ManagementQueue"),
	@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue") })
public class CarMessageConsumer implements MessageListener{

    private static final Logger LOGGER = Logger.getLogger(CarMessageConsumer.class);

    public CarMessageConsumer() {
    }

    @Override
    public void onMessage(Message message) {
	try {
	    System.out.println("Message received: " + message.getBody(String.class));
	    ObjectMapper objectMapper = new ObjectMapper();
	    Car car = objectMapper.readValue(message.getBody(String.class), Car.class);
	    System.out.println("Parsed car: " + car);
	    //TODO persist car with carService
	} catch (JMSException | IOException ex) {
	    LOGGER.info("Uy, no has podido crear un coche, inténtalo de nuevo!!");
	}
    }
}
