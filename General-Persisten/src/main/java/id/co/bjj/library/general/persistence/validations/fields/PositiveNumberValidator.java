package id.co.bjj.library.general.persistence.validations.fields;

import id.co.bjj.library.general.persistence.annotations.PositiveNumber;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Validate input only for number positive
 * 
 * @author Steve Sentosa, 28 October 2024
 */
public class PositiveNumberValidator implements ConstraintValidator<PositiveNumber, Object> {

	boolean includeZero;

	@Override
	public void initialize(PositiveNumber positiveNumber) {
		includeZero = positiveNumber.includeZero();
	}

	@Override
	public boolean isValid(Object object, ConstraintValidatorContext context) {
		if (object == null || object.toString().equals("")) {
			return true; // handle with is required.
		}

		if (object instanceof Number nmbr) {
			Number number = nmbr;
			if ((number.doubleValue() >= 0 && includeZero) || (number.doubleValue() > 0 && !includeZero)) {
				return true;
			}
		}

		return false;
	}
}
