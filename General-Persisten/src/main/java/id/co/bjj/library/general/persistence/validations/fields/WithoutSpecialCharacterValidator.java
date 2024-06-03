package id.co.bjj.library.general.persistence.validations.fields;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import id.co.bjj.library.general.persistence.annotations.WithoutSpecialCharacter;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class WithoutSpecialCharacterValidator implements ConstraintValidator<WithoutSpecialCharacter, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value == null) {
			return true;
		}
		
		Pattern regex = Pattern.compile("[^A-Za-z0-9]");
		Matcher matcher = regex.matcher(value);
		
		return !matcher.find();
	}
}
