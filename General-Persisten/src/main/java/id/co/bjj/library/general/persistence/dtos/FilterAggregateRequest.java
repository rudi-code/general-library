package id.co.bjj.library.general.persistence.dtos;

import java.util.List;

import id.co.bjj.library.general.persistence.annotations.IsRequired;
import jakarta.validation.Valid;

public class FilterAggregateRequest {
	@IsRequired
	private String fieldName;
	
	@Valid
	private List<ParameterDto> parameters;

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public List<ParameterDto> getParameters() {
		return parameters;
	}

	public void setParameters(List<ParameterDto> parameters) {
		this.parameters = parameters;
	}
}
