package id.co.bjj.library.general.persistence.dtos;

import id.co.bjj.library.general.persistence.annotations.IsRequired;
import id.co.bjj.library.general.persistence.annotations.ValidParameter;
import id.co.bjj.library.general.persistence.enums.ConditionEnum;
import id.co.bjj.library.general.persistence.enums.SeparatorEnum;

@ValidParameter
public class ParameterDto {
	@IsRequired
	private String fieldName;

	@IsRequired
	private ConditionEnum condition;

	@IsRequired
	private Object firstValue;

	private Object secondValue;

	@IsRequired
	private SeparatorEnum separator;

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public ConditionEnum getCondition() {
		return condition;
	}

	public void setCondition(ConditionEnum condition) {
		this.condition = condition;
	}

	public Object getFirstValue() {
		return firstValue;
	}

	public void setFirstValue(Object firstValue) {
		this.firstValue = firstValue;
	}

	public Object getSecondValue() {
		return secondValue;
	}

	public void setSecondValue(Object secondValue) {
		this.secondValue = secondValue;
	}

	public SeparatorEnum getSeparator() {
		return separator;
	}

	public void setSeparator(SeparatorEnum separator) {
		this.separator = separator;
	}
}