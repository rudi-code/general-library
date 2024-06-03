package id.co.bjj.library.general.persistence.validations.fields;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import id.co.bjj.library.general.core.constants.ErrorConstant;
import id.co.bjj.library.general.core.exceptions.Error;
import id.co.bjj.library.general.core.exceptions.GeneralException;
import id.co.bjj.library.general.core.exceptions.MultipleError;
import id.co.bjj.library.general.core.exceptions.MultipleGeneralException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class ValidationCheck {
	private ValidationCheck() {
	}

	public static <O> void hasValidate(O object) {
		List<Error> errors = new ArrayList<>();
		ValidatorFactory validatorFactory = Validation.byDefaultProvider().providerResolver(new OsgiServiceDiscoverer())
				.configure().buildValidatorFactory();

		Validator validator = validatorFactory.getValidator();
		Set<ConstraintViolation<O>> violations = validator.validate(object);

		if (violations != null && !violations.isEmpty()) {
			for (ConstraintViolation<O> violation : violations) {
				String message = violation.getMessage();
				Error baseError = ErrorConstant.getError(message);
				Error error = new Error(baseError.getCode(), baseError.getMessage(), baseError.getLocalizedMessage());

				Error tmp = errors.stream().filter(x -> error.getCode().equals(x.getCode())).findAny().orElse(null);

				if (tmp == null) {
					error.addField(violation.getPropertyPath().toString());
					errors.add(error);
				} else {
					errors.forEach(x -> {
						if (x.getCode().equals(tmp.getCode())) {
							x.addField(violation.getPropertyPath().toString());
						}
					});
				}
			}
			throw new GeneralException(errors, 400);
		}
	}

	public static <O, E extends ErrorConstant> void hasValidate(O object, Class<E> errorConstant) {
		ValidatorFactory validatorFactory = Validation.byDefaultProvider().providerResolver(new OsgiServiceDiscoverer())
				.configure().buildValidatorFactory();

		Validator validator = validatorFactory.getValidator();
		Set<ConstraintViolation<O>> violations = validator.validate(object);

		errorChecker(violations, errorConstant, Boolean.FALSE);
	}

	public static <O, E extends ErrorConstant> void hasValidate(List<O> object, Class<E> errorConstant) {
		List<MultipleError> multipleErrors = new ArrayList<>();

		for (int i = 0; i < object.size(); i++) {
			try {
				hasValidate(object.get(i), errorConstant);
			} catch (GeneralException e) {
				List<MultipleError> currentErrors = getError(e.getAllErrors());
				for (MultipleError multipleError : currentErrors) {
					multipleError.setRow(i + 1);
				}
				multipleErrors.addAll(currentErrors);
			}
		}

		if (!multipleErrors.isEmpty()) {
			throw new MultipleGeneralException(multipleErrors);
		}
	}

	private static <O, E extends ErrorConstant> void errorChecker(Set<ConstraintViolation<O>> violations,
			Class<E> errorConstant, Boolean isBulking) {
		Object obj = null;
		try {
			obj = errorConstant.getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException
				| NoSuchMethodException e1) {
			e1.printStackTrace();
		}
		if (violations != null && !violations.isEmpty()) {
			List<Error> errors = new ArrayList<>();
			for (ConstraintViolation<O> violation : violations) {
				String msg = violation.getMessage();
				Error baseError = null;
				try {
					Method method = errorConstant.getSuperclass().getDeclaredMethod("getError", String.class);
					method.setAccessible(true);
					baseError = (Error) method.invoke(obj, msg);
				} catch (SecurityException | IllegalAccessException | IllegalArgumentException | NoSuchMethodException
						| InvocationTargetException e) {
					e.printStackTrace();
				}

				if (baseError != null) {
					Error error = new Error(baseError.getCode(), baseError.getMessage(),
							baseError.getLocalizedMessage());
					Error tmp = errors.stream().filter(x -> error.getCode().equals(x.getCode())).findAny().orElse(null);
					if (tmp == null) {
						error.addField(violation.getPropertyPath().toString());
						errors.add(error);
					} else {
						errors.forEach(x -> {
							if (x.getCode().equals(tmp.getCode())) {
								x.addField(violation.getPropertyPath().toString());
							}
						});
					}
				}
			}
			throw new GeneralException(errors, 400);
		}
	}

	private static List<MultipleError> getError(List<Error> errors) {
		List<MultipleError> multipleErrors = new ArrayList<>();
		for (Error error : errors) {
			MultipleError multipleError = new MultipleError();
			multipleError.setCode(error.getCode());
			multipleError.setLocalizedMessage(error.getLocalizedMessage());
			multipleError.setMessage(error.getMessage());
			multipleError.setField(error.getField());
			multipleError.setHttpStatus(error.getHttpStatus());
			multipleErrors.add(multipleError);
		}
		return multipleErrors;
	}
}
