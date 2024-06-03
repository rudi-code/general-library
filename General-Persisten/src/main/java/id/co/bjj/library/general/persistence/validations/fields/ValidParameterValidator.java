package id.co.bjj.library.general.persistence.validations.fields;

import id.co.bjj.library.general.persistence.annotations.ValidParameter;
import id.co.bjj.library.general.persistence.dtos.ParameterDto;
import id.co.bjj.library.general.persistence.enums.ConditionEnum;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidParameterValidator implements ConstraintValidator<ValidParameter, ParameterDto> {

	@Override
	public boolean isValid(ParameterDto value, ConstraintValidatorContext context) {
		if (value != null) {
			return this.validateSecondValue(value.getCondition(), value.getSecondValue());
		} else {
			return Boolean.FALSE;
		}
	}

	private Boolean validateSecondValue(ConditionEnum conditionEnum, Object secondValue) {
		if (conditionEnum != null) {
			if (conditionEnum.equals(ConditionEnum.BETWEEN) && (secondValue == null || secondValue.equals(""))) {
				return Boolean.FALSE;
			}
		}
		return Boolean.TRUE;
	}
}
