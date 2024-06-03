package id.co.bjj.library.general.persistence.validations.objects;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import id.co.bjj.library.general.core.constants.ErrorConstant;
import id.co.bjj.library.general.core.exceptions.GeneralException;
import id.co.bjj.library.general.persistence.annotations.DateRange;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DateRangeValidator implements ConstraintValidator<DateRange, Object> {

	private String datePattern;
	private String[] fieldNames;
	private Date startDate = null;
	private Date endDate = null;
	private Boolean flag = Boolean.TRUE;
	private static final String START_DATE = "StartDate";
	private static final String END_DATE = "EndDate";

	@Override
	public void initialize(DateRange dateRange) {
		datePattern = dateRange.pattern();
		fieldNames = dateRange.fieldNames();
	}

	@Override
	public boolean isValid(Object obj, ConstraintValidatorContext context) {
		context.disableDefaultConstraintViolation();
		for (String fieldName : fieldNames) {
			try {
				validate(fieldName, obj, obj.getClass());

				if (!startDate.before(endDate)) {
					flag = Boolean.FALSE;
					context.buildConstraintViolationWithTemplate(ErrorConstant.INVALID_DATE_RANGE_KEY)
							.addPropertyNode(fieldName.concat(START_DATE)).addConstraintViolation();
				}
			} catch (NullPointerException | ParseException e) {
				flag = Boolean.TRUE;
			} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
				flag = Boolean.FALSE;
				context.buildConstraintViolationWithTemplate(ErrorConstant.UNKNOWN_ERROR_KEY)
						.addPropertyNode(fieldName.concat(START_DATE)).addConstraintViolation();
			}
		}
		return flag;
	}

	private void validate(String fieldName, Object obj, Class<?> clazz) throws NoSuchFieldException, SecurityException,
			IllegalArgumentException, IllegalAccessException, ParseException {
		Field field = null;
		Class<?> superClass = clazz.getSuperclass();
		try {
			field = clazz.getDeclaredField(fieldName + END_DATE);
			field.setAccessible(true);
			if (field.getType() == Date.class) {
				if (field.get(obj) == null || field.get(obj).toString().equals("")) {
					throw new NullPointerException();
				}
				endDate = (Date) field.get(obj);

				field = clazz.getDeclaredField(fieldName + START_DATE);
				field.setAccessible(true);

				if (field.get(obj) == null) {
					throw new NullPointerException();
				}
				startDate = (Date) field.get(obj);
			} else if (field.getType() == String.class) {
				if (field.get(obj) == null || field.get(obj).toString().equals("")) {
					throw new NullPointerException();
				}
				String endDateString = (String) field.get(obj);
				endDate = new SimpleDateFormat(datePattern).parse(endDateString);

				field = clazz.getDeclaredField(fieldName + START_DATE);
				field.setAccessible(true);

				if (field.get(obj) == null) {
					throw new NullPointerException();
				}
				String startDateString = (String) field.get(obj);
				startDate = new SimpleDateFormat(datePattern).parse(startDateString);
			}
		} catch (NoSuchFieldException | SecurityException e) {
			if (!superClass.equals(Object.class)) {
				validate(fieldName, obj, superClass);
			} else {
				throw new GeneralException(e.getMessage(), e);
			}
		}
	}

}
