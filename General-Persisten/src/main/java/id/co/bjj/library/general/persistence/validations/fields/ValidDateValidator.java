package id.co.bjj.library.general.persistence.validations.fields;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import id.co.bjj.library.general.persistence.annotations.ValidDate;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

/**
 * Check Validate Date
 * 
 * @author Steve Sentosa, 28 October 2021
 */
@Slf4j
public class ValidDateValidator implements ConstraintValidator<ValidDate, String> {
	private String datePattern;

	@Override
	public void initialize(ValidDate validDate) {
		datePattern = validDate.pattern();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null || "".equals(value)) {
			return true; // will handled by isRequired
		}

		SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
		dateFormat.setLenient(false);

		try {
			dateFormat.parse(value);
		} catch (ParseException ex) {
			log.error(ex.getMessage(), ex);
			return false;
		}

		return true;
	}
}
