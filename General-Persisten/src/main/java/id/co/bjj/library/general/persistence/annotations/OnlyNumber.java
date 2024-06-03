package id.co.bjj.library.general.persistence.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import id.co.bjj.library.general.core.constants.ErrorConstant;
import id.co.bjj.library.general.persistence.validations.fields.OnlyNumberValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

/**
 * Validating string value that must be numeric.
 *
 * @author taufik.muliahadi (&copy;Sep 14, 2018)
 */
@Documented
@Constraint(validatedBy = OnlyNumberValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface OnlyNumber {

	/**
	 * set minimum.. if minimum is greater than maximum, min & max is useless or not
	 * working.
	 *
	 * @author M Lukmanul Hakim (m.hakim &copy;Feb 19, 2019) <br>
	 *         for further info contact: <i>vickyhakimm@gmail.com</i>
	 * @return
	 */
	int min() default 0;

	/**
	 * set maximum.. if maximum is lower than minimum, min & max is useless or not
	 * working.
	 *
	 * @author M Lukmanul Hakim (m.hakim &copy;Feb 19, 2019) <br>
	 *         for further info contact: <i>vickyhakimm@gmail.com</i>
	 * @return
	 */
	int max() default 0;

	String message() default ErrorConstant.INVALID_NUMBER_FORMAT_KEY;

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	@interface List {
		OnlyNumber[] value();
	}
}
