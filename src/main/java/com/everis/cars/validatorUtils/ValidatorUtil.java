package com.everis.cars.validatorUtils;

import java.util.ArrayList;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.apache.log4j.Logger;

import com.everis.cars.control.CarService;



/**
 
* Validates objects against the restrictions put in the Bean validations
 
*
 
*/


public class ValidatorUtil {

	
private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	
private static Validator validator = factory.getValidator();
	
	
private final static Logger LOGGER = Logger.getLogger(CarService.class);

	

/**
	 
* Validates an object against the restrictions put in the Bean validations
	 
* @param entity any entity that needs to be validated
	 
* @return an ArrayList of Strings containing all the error messages thrown by the unsuccessful validations
	
* 
	 
*/
	

public static <T> ArrayList<String> validate(T Car) {
		
		

LOGGER.info("Validating an entity: " + Car);
		
		
Set<ConstraintViolation<T>> violations = validator.validate(Car);
		
ArrayList<String> violationMessages = new ArrayList<String>();
		
for (ConstraintViolation<T> violation : violations) {
			
violationMessages.add(violation.getMessage());
		
}
		
		
return violationMessages;
	
}



public static ValidatorFactory getFactory() {
	return factory;
}



public static void setFactory(ValidatorFactory factory) {
	ValidatorUtil.factory = factory;
}



public static Validator getValidator() {
	return validator;
}



public static void setValidator(Validator validator) {
	ValidatorUtil.validator = validator;
}



public static Logger getLogger() {
	return LOGGER;
}

}