package id.co.bjj.library.general.persistence.validations.fields;

import id.co.bjj.library.general.persistence.annotations.ValidEmail;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Validate email
 * 
 * @author Steve Sentosa, 28 October 2021
 */
public class ValidEmailValidator implements ConstraintValidator<ValidEmail, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext arg1) {
		if (value == null) {
			return true; // will handled by isRequired
		}

		return value.matches("[_a-zA-Z0-9]+(\\.[A-Za-z0-9]*)*@[A-Za-z0-9]+\\.[A-Za-z0-9]+(\\.[A-Za-z0-9]*)*");
	}

}
