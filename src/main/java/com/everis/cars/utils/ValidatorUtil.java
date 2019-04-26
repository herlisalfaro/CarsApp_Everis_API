package com.everis.cars.utils;

import java.util.ArrayList;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 
 * Validates objects against the restrictions put in the Bean validations
 *
 */

public class ValidatorUtil {

	private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

	private static Validator validator = factory.getValidator();

	private final static Logger LOGGER = LogManager.getLogger(ValidatorUtil.class);

	/**
	 * 
	 * Validates an object against the restrictions put in the Bean validations
	 * 
	 * @param entity any entity that needs to be validated
	 * 
	 * @return an ArrayList of Strings containing all the error messages thrown by
	 *         the unsuccessful validations
	 * 
	 */

	public static <T> ArrayList<String> validate(T Entity) {

		LOGGER.info("Validating an entity: " + Entity);

		Set<ConstraintViolation<T>> violations = validator.validate(Entity);

		ArrayList<String> violationMessages = new ArrayList<String>();

		for (ConstraintViolation<T> violation : violations) {

			violationMessages.add(violation.getMessage());

		}

		return violationMessages;

	}

}
