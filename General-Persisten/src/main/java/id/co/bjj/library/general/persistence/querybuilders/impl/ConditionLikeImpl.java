package id.co.bjj.library.general.persistence.querybuilders.impl;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;

import org.springframework.stereotype.Component;

import id.co.bjj.library.general.core.constants.ErrorConstant;
import id.co.bjj.library.general.core.exceptions.GeneralException;
import id.co.bjj.library.general.persistence.dtos.ParameterDto;
import id.co.bjj.library.general.persistence.enums.SeparatorEnum;
import id.co.bjj.library.general.persistence.querybuilders.ConditionComparer;
import id.co.bjj.library.general.persistence.querybuilders.WherePredicateBuilder;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Component("conditionLike")
public class ConditionLikeImpl<M> implements WherePredicateBuilder<M>, ConditionComparer<M> {
	@Override
	public Predicate getPredicate(CriteriaBuilder criteriaBuilder, Root<M> root, ParameterDto parameter) {
		try {
			Class<?> entityClass = root.getJavaType();
			Field field = entityClass.getDeclaredField(parameter.getFieldName());
			Class<?> dataType = field.getType();
			if (dataType.equals(String.class)) {
				return this.getPredicateString(criteriaBuilder, root, parameter);
			} else if (dataType.equals(Integer.class)) {
				return this.getPredicateInteger(criteriaBuilder, root, parameter);
			} else if (dataType.equals(BigInteger.class)) {
				return this.getPredicateBigInteger(criteriaBuilder, root, parameter);
			} else if (dataType.equals(BigDecimal.class)) {
				return this.getPredicateBigDecimal(criteriaBuilder, root, parameter);
			} else if (dataType.equals(Long.class)) {
				return this.getPredicateLong(criteriaBuilder, root, parameter);
			} else if (dataType.equals(Date.class)) {
				return this.getPredicateDate(criteriaBuilder, root, parameter);
			} else if (dataType.equals(Double.class)) {
				return this.getPredicateDouble(criteriaBuilder, root, parameter);
			} else if (dataType.equals(Float.class)) {
				return this.getPredicateFloat(criteriaBuilder, root, parameter);
			} else if (dataType.equals(Boolean.class)) {
				return this.getPredicateBoolean(criteriaBuilder, root, parameter);
			} else {
				throw new GeneralException(ErrorConstant.QUERY_DATA_TYPE_ERROR);
			}
		} catch (NoSuchFieldException | SecurityException e) {
			throw new GeneralException(ErrorConstant.QUERY_FIELD_NOT_FOUND);
		}
	}

	@Override
	public Predicate getPredicateString(CriteriaBuilder criteriaBuilder, Root<M> root, ParameterDto parameter) {
		try {
			String firstValue = parameter.getFirstValue().toString();
			if (parameter.getSeparator().equals(SeparatorEnum.AND)) {
				return criteriaBuilder.and(criteriaBuilder.like(root.get(parameter.getFieldName()), "%" + firstValue + "%"));
			} else if (parameter.getSeparator().equals(SeparatorEnum.OR)) {
				return criteriaBuilder.or(criteriaBuilder.like(root.get(parameter.getFieldName()), "%" + firstValue + "%"));
			} else {
				throw new GeneralException(ErrorConstant.QUERY_SEPARATOR_INVALID);
			}
		} catch (Exception ex) {
			throw new GeneralException(ErrorConstant.QUERY_DATA_TYPE_CONVERT_ERROR);
		}
	}

	@Override
	public Predicate getPredicateInteger(CriteriaBuilder criteriaBuilder, Root<M> root, ParameterDto parameter) {
		throw new GeneralException(ErrorConstant.QUERY_COMPARE_FIELD_INVALID);
	}

	@Override
	public Predicate getPredicateBigInteger(CriteriaBuilder criteriaBuilder, Root<M> root, ParameterDto parameter) {
		throw new GeneralException(ErrorConstant.QUERY_COMPARE_FIELD_INVALID);
	}

	@Override
	public Predicate getPredicateBigDecimal(CriteriaBuilder criteriaBuilder, Root<M> root, ParameterDto parameter) {
		throw new GeneralException(ErrorConstant.QUERY_COMPARE_FIELD_INVALID);
	}

	@Override
	public Predicate getPredicateLong(CriteriaBuilder criteriaBuilder, Root<M> root, ParameterDto parameter) {
		throw new GeneralException(ErrorConstant.QUERY_COMPARE_FIELD_INVALID);
	}

	@Override
	public Predicate getPredicateDate(CriteriaBuilder criteriaBuilder, Root<M> root, ParameterDto parameter) {
		throw new GeneralException(ErrorConstant.QUERY_COMPARE_FIELD_INVALID);
	}

	@Override
	public Predicate getPredicateDouble(CriteriaBuilder criteriaBuilder, Root<M> root, ParameterDto parameter) {
		throw new GeneralException(ErrorConstant.QUERY_COMPARE_FIELD_INVALID);
	}

	@Override
	public Predicate getPredicateFloat(CriteriaBuilder criteriaBuilder, Root<M> root, ParameterDto parameter) {
		throw new GeneralException(ErrorConstant.QUERY_COMPARE_FIELD_INVALID);
	}

	@Override
	public Predicate getPredicateBoolean(CriteriaBuilder criteriaBuilder, Root<M> root, ParameterDto parameter) {
		throw new GeneralException(ErrorConstant.QUERY_COMPARE_FIELD_INVALID);
	}

}
