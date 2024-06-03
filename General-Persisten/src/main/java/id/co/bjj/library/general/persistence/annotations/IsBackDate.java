package id.co.bjj.library.general.persistence.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import id.co.bjj.library.general.core.constants.ErrorConstant;
import id.co.bjj.library.general.persistence.validations.fields.IsBackDateValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

/**
 * Annotation check date is not allowed below than date now
 * 
 * @author Steve Sentosa, 20 May 2024
 */
@Documented
@Constraint(validatedBy = IsBackDateValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface IsBackDate {

	String message() default ErrorConstant.DATE_CANNOT_BACKDATE_KEY;

	String pattern();

	boolean backdate();

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	@interface List {
		IsBackDate[] value();
	}
}
