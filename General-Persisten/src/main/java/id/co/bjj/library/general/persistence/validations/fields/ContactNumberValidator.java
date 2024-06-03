package id.co.bjj.library.general.persistence.validations.fields;

import id.co.bjj.library.general.persistence.annotations.ContactNumber;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Validate contact number
 * 
 * @author Steve Sentosa, 24 May 2024
 */
public class ContactNumberValidator implements ConstraintValidator<ContactNumber, String> {

	private boolean required;

	@Override
	public void initialize(ContactNumber contactNumber) {
		required = contactNumber.required();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext arg1) {
		if (required) {
			return (value == null || value.isEmpty());
		}

		return value != null && value.matches("\\d+") && (value.length() > 7 && value.length() <= 13);
	}

}
