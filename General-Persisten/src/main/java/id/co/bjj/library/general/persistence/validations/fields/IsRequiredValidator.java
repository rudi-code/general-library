package id.co.bjj.library.general.persistence.validations.fields;

import java.util.Collection;
import java.util.Map;

import id.co.bjj.library.general.persistence.annotations.IsRequired;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Validate field is required annotation
 * 
 * @author Steve Sentosa, 20 May 2024
 */
public class IsRequiredValidator implements ConstraintValidator<IsRequired, Object> {

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext arg1) {
		if (value != null) {
			if (value instanceof String string)
				return !string.isEmpty();
			else if (value instanceof Collection) {
				return !((Collection<?>) value).isEmpty();
			} else if (value instanceof Map) {
				return !((Map<?, ?>) value).isEmpty();
			} else {
				return true;
			}
		} else {
			return false;
		}
	}
}
