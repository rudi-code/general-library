package id.co.bjj.library.general.persistence.querybuilders;

import id.co.bjj.library.general.persistence.dtos.ParameterDto;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public interface ConditionComparer<M> {
	public Predicate getPredicateString(CriteriaBuilder criteriaBuilder, Root<M> root, ParameterDto parameter);

	public Predicate getPredicateInteger(CriteriaBuilder criteriaBuilder, Root<M> root, ParameterDto parameter);

	public Predicate getPredicateBigInteger(CriteriaBuilder criteriaBuilder, Root<M> root, ParameterDto parameter);

	public Predicate getPredicateBigDecimal(CriteriaBuilder criteriaBuilder, Root<M> root, ParameterDto parameter);

	public Predicate getPredicateLong(CriteriaBuilder criteriaBuilder, Root<M> root, ParameterDto parameter);

	public Predicate getPredicateDate(CriteriaBuilder criteriaBuilder, Root<M> root, ParameterDto parameter);
	
	public Predicate getPredicateDouble(CriteriaBuilder criteriaBuilder, Root<M> root, ParameterDto parameter);
	
	public Predicate getPredicateFloat(CriteriaBuilder criteriaBuilder, Root<M> root, ParameterDto parameter);

	public Predicate getPredicateBoolean(CriteriaBuilder criteriaBuilder, Root<M> root, ParameterDto parameter);
}
