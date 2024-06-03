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

@Component("conditionIsNotNull")
public class ConditionIsNotNullImpl<M> implements WherePredicateBuilder<M> {

	@Override
	public Predicate getPredicate(CriteriaBuilder criteriaBuilder, Root<M> root, ParameterDto parameter) {
		try {
			if (parameter.getSeparator().equals(SeparatorEnum.AND)) {
				return criteriaBuilder.and(criteriaBuilder.isNotNull(root.get(parameter.getFieldName())));
			} else if (parameter.getSeparator().equals(SeparatorEnum.OR)) {
				return criteriaBuilder.or(criteriaBuilder.isNotNull(root.get(parameter.getFieldName())));
			} else {
				throw new GeneralException(ErrorConstant.QUERY_SEPARATOR_INVALID);
			}
		} catch (Exception ex) {
			throw new GeneralException(ErrorConstant.QUERY_DATA_TYPE_CONVERT_ERROR);
		}
	}
	
}
