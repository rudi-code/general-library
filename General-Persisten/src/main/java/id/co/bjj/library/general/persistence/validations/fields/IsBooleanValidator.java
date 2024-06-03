package id.co.bjj.library.general.persistence.validations.fields;

import id.co.bjj.library.general.persistence.annotations.IsBoolean;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Validate boolean is true or false
 * 
 * @author Steve Sentosa, 21 May 2024
 */
public class IsBooleanValidator implements ConstraintValidator<IsBoolean, String> {
	private static final String FALSE = "false";
	private static final String TRUE = "true";

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value != null) {
			String text = value.trim();
			if (TRUE.equalsIgnoreCase(text) || FALSE.equalsIgnoreCase(text)) {
				return true;
			} else {
				return false;
			}
		} else {
			return true;
		}
	}

}
