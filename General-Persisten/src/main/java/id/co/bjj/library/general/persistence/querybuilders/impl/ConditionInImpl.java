package id.co.bjj.library.general.persistence.querybuilders.impl;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import id.co.bjj.library.general.core.constants.ErrorConstant;
import id.co.bjj.library.general.core.exceptions.GeneralException;
import id.co.bjj.library.general.persistence.dtos.ParameterDto;
import id.co.bjj.library.general.persistence.enums.SeparatorEnum;
import id.co.bjj.library.general.persistence.querybuilders.ConditionComparer;
import id.co.bjj.library.general.persistence.querybuilders.WherePredicateBuilder;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Component("conditionIn")
public class ConditionInImpl<M> implements WherePredicateBuilder<M>, ConditionComparer<M> {
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
	@SuppressWarnings("unchecked")
	public Predicate getPredicateString(CriteriaBuilder criteriaBuilder, Root<M> root, ParameterDto parameter) {
		try {
			if (parameter.getFirstValue() instanceof List<?>) {
				List<String> firstValue = (List<String>) parameter.getFirstValue();
				Expression<String> expression = root.get(parameter.getFieldName());
				if (parameter.getSeparator().equals(SeparatorEnum.AND)) {
					return criteriaBuilder.and(expression.in(firstValue));
				} else if (parameter.getSeparator().equals(SeparatorEnum.OR)) {
					return criteriaBuilder.or(expression.in(firstValue));
				} else {
					throw new GeneralException(ErrorConstant.QUERY_SEPARATOR_INVALID);
				}
			} else {
				throw new GeneralException(ErrorConstant.QUERY_VALUE_MUST_LIST);
			}
		} catch (Exception ex) {
			throw new GeneralException(ErrorConstant.QUERY_DATA_TYPE_CONVERT_ERROR);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public Predicate getPredicateInteger(CriteriaBuilder criteriaBuilder, Root<M> root, ParameterDto parameter) {
		try {
			if (parameter.getFirstValue() instanceof List<?>) {
				List<Integer> firstValue = (List<Integer>) parameter.getFirstValue();
				Expression<Integer> expression = root.get(parameter.getFieldName());
				if (parameter.getSeparator().equals(SeparatorEnum.AND)) {
					return criteriaBuilder.and(expression.in(firstValue));
				} else if (parameter.getSeparator().equals(SeparatorEnum.OR)) {
					return criteriaBuilder.or(expression.in(firstValue));
				} else {
					throw new GeneralException(ErrorConstant.QUERY_SEPARATOR_INVALID);
				}
			} else {
				throw new GeneralException(ErrorConstant.QUERY_VALUE_MUST_LIST);
			}
		} catch (Exception ex) {
			throw new GeneralException(ErrorConstant.QUERY_DATA_TYPE_CONVERT_ERROR);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public Predicate getPredicateBigInteger(CriteriaBuilder criteriaBuilder, Root<M> root, ParameterDto parameter) {
		try {
			if (parameter.getFirstValue() instanceof List<?>) {
				List<BigInteger> firstValue = (List<BigInteger>) parameter.getFirstValue();
				Expression<BigInteger> expression = root.get(parameter.getFieldName());
				if (parameter.getSeparator().equals(SeparatorEnum.AND)) {
					return criteriaBuilder.and(expression.in(firstValue));
				} else if (parameter.getSeparator().equals(SeparatorEnum.OR)) {
					return criteriaBuilder.or(expression.in(firstValue));
				} else {
					throw new GeneralException(ErrorConstant.QUERY_SEPARATOR_INVALID);
				}
			} else {
				throw new GeneralException(ErrorConstant.QUERY_VALUE_MUST_LIST);
			}
		} catch (Exception ex) {
			throw new GeneralException(ErrorConstant.QUERY_DATA_TYPE_CONVERT_ERROR);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public Predicate getPredicateBigDecimal(CriteriaBuilder criteriaBuilder, Root<M> root, ParameterDto parameter) {
		try {
			if (parameter.getFirstValue() instanceof List<?>) {
				List<BigDecimal> firstValue = (List<BigDecimal>) parameter.getFirstValue();
				Expression<BigDecimal> expression = root.get(parameter.getFieldName());
				if (parameter.getSeparator().equals(SeparatorEnum.AND)) {
					return criteriaBuilder.and(expression.in(firstValue));
				} else if (parameter.getSeparator().equals(SeparatorEnum.OR)) {
					return criteriaBuilder.or(expression.in(firstValue));
				} else {
					throw new GeneralException(ErrorConstant.QUERY_SEPARATOR_INVALID);
				}
			} else {
				throw new GeneralException(ErrorConstant.QUERY_VALUE_MUST_LIST);
			}
		} catch (Exception ex) {
			throw new GeneralException(ErrorConstant.QUERY_DATA_TYPE_CONVERT_ERROR);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public Predicate getPredicateLong(CriteriaBuilder criteriaBuilder, Root<M> root, ParameterDto parameter) {
		try {
			if (parameter.getFirstValue() instanceof List<?>) {
				List<Long> firstValue = (List<Long>) parameter.getFirstValue();
				Expression<Long> expression = root.get(parameter.getFieldName());
				if (parameter.getSeparator().equals(SeparatorEnum.AND)) {
					return criteriaBuilder.and(expression.in(firstValue));
				} else if (parameter.getSeparator().equals(SeparatorEnum.OR)) {
					return criteriaBuilder.or(expression.in(firstValue));
				} else {
					throw new GeneralException(ErrorConstant.QUERY_SEPARATOR_INVALID);
				}
			} else {
				throw new GeneralException(ErrorConstant.QUERY_VALUE_MUST_LIST);
			}
		} catch (Exception ex) {
			throw new GeneralException(ErrorConstant.QUERY_DATA_TYPE_CONVERT_ERROR);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public Predicate getPredicateDate(CriteriaBuilder criteriaBuilder, Root<M> root, ParameterDto parameter) {
		try {
			if (parameter.getFirstValue() instanceof List<?>) {
				List<Date> firstValue = (List<Date>) parameter.getFirstValue();
				Expression<Date> expression = root.get(parameter.getFieldName());
				if (parameter.getSeparator().equals(SeparatorEnum.AND)) {
					return criteriaBuilder.and(expression.in(firstValue));
				} else if (parameter.getSeparator().equals(SeparatorEnum.OR)) {
					return criteriaBuilder.or(expression.in(firstValue));
				} else {
					throw new GeneralException(ErrorConstant.QUERY_SEPARATOR_INVALID);
				}
			} else {
				throw new GeneralException(ErrorConstant.QUERY_VALUE_MUST_LIST);
			}
		} catch (Exception ex) {
			throw new GeneralException(ErrorConstant.QUERY_DATA_TYPE_CONVERT_ERROR);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public Predicate getPredicateDouble(CriteriaBuilder criteriaBuilder, Root<M> root, ParameterDto parameter) {
		try {
			if (parameter.getFirstValue() instanceof List<?>) {
				List<Double> firstValue = (List<Double>) parameter.getFirstValue();
				Expression<Double> expression = root.get(parameter.getFieldName());
				if (parameter.getSeparator().equals(SeparatorEnum.AND)) {
					return criteriaBuilder.and(expression.in(firstValue));
				} else if (parameter.getSeparator().equals(SeparatorEnum.OR)) {
					return criteriaBuilder.or(expression.in(firstValue));
				} else {
					throw new GeneralException(ErrorConstant.QUERY_SEPARATOR_INVALID);
				}
			} else {
				throw new GeneralException(ErrorConstant.QUERY_VALUE_MUST_LIST);
			}
		} catch (Exception ex) {
			throw new GeneralException(ErrorConstant.QUERY_DATA_TYPE_CONVERT_ERROR);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public Predicate getPredicateFloat(CriteriaBuilder criteriaBuilder, Root<M> root, ParameterDto parameter) {
		try {
			if (parameter.getFirstValue() instanceof List<?>) {
				List<Float> firstValue = (List<Float>) parameter.getFirstValue();
				Expression<Float> expression = root.get(parameter.getFieldName());
				if (parameter.getSeparator().equals(SeparatorEnum.AND)) {
					return criteriaBuilder.and(expression.in(firstValue));
				} else if (parameter.getSeparator().equals(SeparatorEnum.OR)) {
					return criteriaBuilder.or(expression.in(firstValue));
				} else {
					throw new GeneralException(ErrorConstant.QUERY_SEPARATOR_INVALID);
				}
			} else {
				throw new GeneralException(ErrorConstant.QUERY_VALUE_MUST_LIST);
			}
		} catch (Exception ex) {
			throw new GeneralException(ErrorConstant.QUERY_DATA_TYPE_CONVERT_ERROR);
		}
	}

	@Override
	public Predicate getPredicateBoolean(CriteriaBuilder criteriaBuilder, Root<M> root, ParameterDto parameter) {
		throw new GeneralException(ErrorConstant.QUERY_COMPARE_FIELD_INVALID);
	}
}
