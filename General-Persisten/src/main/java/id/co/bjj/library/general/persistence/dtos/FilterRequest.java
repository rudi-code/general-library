package id.co.bjj.library.general.persistence.dtos;

import java.util.List;

import jakarta.validation.Valid;

public class FilterRequest {
	@Valid
	private List<ParameterDto> parameters;
	
	@Valid
	private List<OrderDto> orders;

	public List<ParameterDto> getParameters() {
		return parameters;
	}

	public void setParameters(List<ParameterDto> parameters) {
		this.parameters = parameters;
	}

	public List<OrderDto> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderDto> orders) {
		this.orders = orders;
	}
}
