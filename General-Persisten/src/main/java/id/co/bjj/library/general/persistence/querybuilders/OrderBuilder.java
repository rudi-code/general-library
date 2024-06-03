package id.co.bjj.library.general.persistence.querybuilders;

import id.co.bjj.library.general.persistence.dtos.OrderDto;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Root;

public interface OrderBuilder<M> {
	public Order getOrder(CriteriaBuilder criteriaBuilder, Root<M> root, OrderDto order);
}
