package id.co.bjj.library.general.persistence.validations.fields;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import id.co.bjj.library.general.persistence.annotations.IsBackDate;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

/**
 * Validate date must be greater than date now
 * 
 * @author Steve Sentosa. 21 May 2024
 */
@Slf4j
public class IsBackDateValidator implements ConstraintValidator<IsBackDate, String> {

	private boolean backDate;
	private String datePattern;
	private Date now = new Date();

	@Override
	public void initialize(IsBackDate isBackDate) {
		datePattern = isBackDate.pattern();
		backDate = isBackDate.backdate();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		DateFormat sdf = new SimpleDateFormat(datePattern);
		Date currentDate = null;
		Date dateValue = null;

		if (value == null || "".equals(value)) {
			return true; // will handled by isRequired
		}

		try {
			dateValue = sdf.parse(value);
			currentDate = sdf.parse(sdf.format(now));

			if (!backDate || dateValue.before(currentDate)) {
				return false;
			}
		} catch (ParseException ex) {
			log.error(ex.getMessage(), ex);
			return false;
		}

		return true;
	}
}
