package id.co.bjj.library.general.persistence.dtos;

import java.util.List;

import id.co.bjj.library.general.persistence.annotations.PositiveNumber;
import jakarta.validation.Valid;

public class FilterPagingRequest {
	@PositiveNumber
	private Integer pageNumber;
	
	@PositiveNumber
	private Integer pageSize;
	
	@Valid
	private List<ParameterDto> parameters;
	
	@Valid
	private List<OrderDto> orders;

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

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
