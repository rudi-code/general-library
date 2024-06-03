package id.co.bjj.library.general.persistence.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import id.co.bjj.library.general.core.constants.ErrorConstant;
import id.co.bjj.library.general.persistence.validations.objects.DateRangeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = DateRangeValidator.class)
@Target( {ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface DateRange {

	String message() default ErrorConstant.INVALID_DATE_RANGE_KEY;
	
	String pattern();

	/**
	 * <pre>
	 * &#64;DateRange(fieldNames = {["xxx"]})
	 * public class Example{
	 * 		private Date xxxStartDate;
	 *  	private Date xxxEndDate;
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
