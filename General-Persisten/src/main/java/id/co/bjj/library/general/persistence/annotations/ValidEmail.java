package id.co.bjj.library.general.persistence.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import id.co.bjj.library.general.core.constants.ErrorConstant;
import id.co.bjj.library.general.persistence.validations.fields.ValidEmailValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

/**
 * Annotation for validate email
 * @author Steve Sentosa, 20 May 2024
 */
@Documented
@Constraint(validatedBy = ValidEmailValidator.class)
@Target( {ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidEmail {

	String message() default ErrorConstant.INVALID_EMAIL_VALUE_KEY;
	
	Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    @Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
    	ValidEmail[] value();
    }
}
