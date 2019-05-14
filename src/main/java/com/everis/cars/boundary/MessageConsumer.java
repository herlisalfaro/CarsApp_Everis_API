package com.everis.cars.boundary;

import javax.jms.*;

import org.apache.log4j.Logger;

import javax.ejb.*;

@JMSDestinationDefinition(name = "jms/CarsApp_API_ManagementQueue", interfaceName = "javax.jms.Queue")
@MessageDriven(activationConfig = {
	@ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/CarsApp_API_ManagementQueue"),
	@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue") })
public class MessageConsumer implements MessageListener {
    
    private final static Logger LOGGER = Logger.getLogger(MessageConsumer.class);

    public MessageConsumer() {
    }

    @Override
    public void onMessage(Message message) {
	try {
	    System.out.println("Message received: " + message.getBody(String.class));
	} catch (JMSException ex) {
	    LOGGER.info("Esto es una mierda y lo sabes");
	}
    }
}
