package id.co.bjj.library.general.persistence.validations.objects;

import java.lang.reflect.Field;

import id.co.bjj.library.general.core.constants.ErrorConstant;
import id.co.bjj.library.general.persistence.annotations.MinMax;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MinMaxValidator implements ConstraintValidator<MinMax, Object> {

	private String[] fieldNames;
	private Number max = null;
	private Number min = null;
	private static final String MAX = "Max";
	private static final String MIN = "Min";

	@Override
	public void initialize(MinMax minMax) {
		fieldNames = minMax.fieldNames();
	}

	@Override
	public boolean isValid(Object obj, ConstraintValidatorContext context) {
		Boolean flag = Boolean.TRUE;
		context.disableDefaultConstraintViolation();
		for (String fieldName : fieldNames) {
			try {
				validate(fieldName, obj);

				if (max.doubleValue() < min.doubleValue()) {
					flag = Boolean.FALSE;
					context.buildConstraintViolationWithTemplate(ErrorConstant.INVALID_MIN_MAX_KEY)
							.addPropertyNode(fieldName.concat(MAX)).addConstraintViolation();
				}
			} catch (NullPointerException e) {
				flag = Boolean.FALSE;
				context.buildConstraintViolationWithTemplate(ErrorConstant.REQUIRED_FIELD_KEY)
						.addPropertyNode(fieldName.concat(MAX)).addConstraintViolation();
			} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
				flag = Boolean.FALSE;
				context.buildConstraintViolationWithTemplate(ErrorConstant.UNKNOWN_ERROR_KEY)
						.addPropertyNode(fieldName.concat(MAX)).addConstraintViolation();
			}
		}
		return flag;
	}

	private void validate(String fieldName, Object obj)
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {

		Field field = obj.getClass().getDeclaredField(fieldName + MAX);
		field.setAccessible(true);
		if (field.get(obj) instanceof Number) {
			max = (Number) field.get(obj);

			field = obj.getClass().getDeclaredField(fieldName + MIN);
			field.setAccessible(true);

			min = (Number) field.get(obj);
		}
	}
}
