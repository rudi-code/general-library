package id.co.bjj.library.general.persistence.querybuilders.impl;

import org.springframework.stereotype.Component;

import id.co.bjj.library.general.core.constants.ErrorConstant;
import id.co.bjj.library.general.core.exceptions.GeneralException;
import id.co.bjj.library.general.persistence.dtos.OrderDto;
import id.co.bjj.library.general.persistence.enums.OrderEnum;
import id.co.bjj.library.general.persistence.querybuilders.OrderBuilder;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Root;

@Component
public class OrderBuilderImpl<M> implements OrderBuilder<M> {
	@Override
	public Order getOrder(CriteriaBuilder criteriaBuilder, Root<M> root, OrderDto order) {
		try {
			Class<?> entityClass = root.getJavaType();
			entityClass.getDeclaredField(order.getFieldName());
			if (order.getOrder().equals(OrderEnum.ASC)) {
				return criteriaBuilder.asc(root.get(order.getFieldName()));
			} else if (order.getOrder().equals(OrderEnum.DESC)) {
				return criteriaBuilder.desc(root.get(order.getFieldName()));
			} else {
				throw new GeneralException();
			}
		} catch (Exception ex) {
			throw new GeneralException(ErrorConstant.QUERY_FIELD_NOT_FOUND);
		}
	}
}
