package id.co.bjj.library.general.persistence.querybuilders;

import id.co.bjj.library.general.persistence.dtos.ParameterDto;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public interface WherePredicateBuilder<M> {
	public Predicate getPredicate(CriteriaBuilder criteriaBuilder, Root<M> root, ParameterDto parameter);
}
