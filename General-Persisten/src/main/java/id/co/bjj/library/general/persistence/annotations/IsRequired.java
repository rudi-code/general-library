package id.co.bjj.library.general.persistence.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import id.co.bjj.library.general.core.constants.ErrorConstant;
import id.co.bjj.library.general.persistence.validations.fields.IsRequiredValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

/**
 * Annotation validate for data is required
 * @author Steve Sentosa, 28 October 2021
 */
@Documented
@Constraint(validatedBy = IsRequiredValidator.class)
@Target( {ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface IsRequired {

	String message() default ErrorConstant.REQUIRED_FIELD_KEY;
	Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    @Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
    	IsRequired[] value();
    }
}
