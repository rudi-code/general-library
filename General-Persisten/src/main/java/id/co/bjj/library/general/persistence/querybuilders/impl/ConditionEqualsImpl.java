package id.co.bjj.library.general.persistence.querybuilders.impl;

import org.springframework.stereotype.Component;

import id.co.bjj.library.general.core.constants.ErrorConstant;
import id.co.bjj.library.general.core.exceptions.GeneralException;
import id.co.bjj.library.general.persistence.dtos.ParameterDto;
import id.co.bjj.library.general.persistence.enums.SeparatorEnum;
import id.co.bjj.library.general.persistence.querybuilders.WherePredicateBuilder;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Component("conditionEquals")
public class ConditionEqualsImpl<M> implements WherePredicateBuilder<M> {
	@Override
	public Predicate getPredicate(CriteriaBuilder criteriaBuilder, Root<M> root, ParameterDto parameter) {
		if (parameter.getSeparator().equals(SeparatorEnum.AND)) {
			return criteriaBuilder
					.and(criteriaBuilder.equal(root.get(parameter.getFieldName()), parameter.getFirstValue()));
		} else if (parameter.getSeparator().equals(SeparatorEnum.OR)) {
			return criteriaBuilder
					.or(criteriaBuilder.equal(root.get(parameter.getFieldName()), parameter.getFirstValue()));
		} else {
			throw new GeneralException(ErrorConstant.QUERY_SEPARATOR_INVALID);
		}
	}
}
