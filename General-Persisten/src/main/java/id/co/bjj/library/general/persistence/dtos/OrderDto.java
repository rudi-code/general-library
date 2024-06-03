package id.co.bjj.library.general.persistence.dtos;

import id.co.bjj.library.general.persistence.annotations.IsRequired;
import id.co.bjj.library.general.persistence.enums.OrderEnum;

public class OrderDto {
	@IsRequired
	private String fieldName;
	
	@IsRequired	
	private OrderEnum order;

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public OrderEnum getOrder() {
		return order;
	}

	public void setOrder(OrderEnum order) {
		this.order = order;
	}
}
