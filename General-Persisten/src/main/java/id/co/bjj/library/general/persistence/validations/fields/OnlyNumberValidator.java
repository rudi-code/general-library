package id.co.bjj.library.general.persistence.validations.fields;

import id.co.bjj.library.general.persistence.annotations.OnlyNumber;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Validate input only for number integer
 * 
 * @author Steve Sentosa, 28 October 2024
 */
public class OnlyNumberValidator implements ConstraintValidator<OnlyNumber, String> {

	private int min;
	private int max;

	@Override
	public void initialize(OnlyNumber onlyNumber) {
		if (onlyNumber.min() < onlyNumber.max()) {
			this.min = onlyNumber.min();
			this.max = onlyNumber.max();
		}
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext arg1) {
		if (value == null) {
			return true; // will handled by isRequired
		}

		if (value.matches("[0-9]+")) {
			if (min != 0 && max != 0) {
				Integer number = Integer.valueOf(value);
				if (number >= min && number <= max) {
					return true;
				} else {
					return false;
				}
			}
			return true;
		}
		return false;
	}

}
