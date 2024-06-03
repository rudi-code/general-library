package id.co.bjj.library.general.persistence.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import id.co.bjj.library.general.core.constants.ErrorConstant;
import id.co.bjj.library.general.persistence.validations.objects.MinMaxValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = MinMaxValidator.class)
@Target( {ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface MinMax {
	
	String message() default ErrorConstant.INVALID_MIN_MAX_KEY;

	/**
	 * <pre>
	 * &#64;MinMax(fieldNames = {["xxx", "yyy"]})
	 * public class Example{
	 * 		private Date xxxMin;
	 *  	private Date xxxMax;
	 *  	private Date yyyMin;
	 *  	private Date yyyMax;
	 * }
	 * </pre>
	 */
	String[] fieldNames();

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	@interface List {
		ValidDate[] value();
	}
}
