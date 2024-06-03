package id.co.bjj.library.general.persistence.daos.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import id.co.bjj.library.general.core.constants.ErrorConstant;
import id.co.bjj.library.general.core.exceptions.GeneralException;
import id.co.bjj.library.general.persistence.daos.GeneralDao;
import id.co.bjj.library.general.persistence.dtos.OrderDto;
import id.co.bjj.library.general.persistence.dtos.Paging;
import id.co.bjj.library.general.persistence.dtos.ParameterDto;
import id.co.bjj.library.general.persistence.enums.ConditionEnum;
import id.co.bjj.library.general.persistence.enums.StatusEnum;
import id.co.bjj.library.general.persistence.models.GeneralModel;
import id.co.bjj.library.general.persistence.querybuilders.OrderBuilder;
import id.co.bjj.library.general.persistence.querybuilders.WherePredicateBuilder;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public abstract class GeneralDaoImpl<M extends GeneralModel, ID extends Serializable> implements GeneralDao<M, ID> {
	protected static Logger log = LogManager.getLogger(GeneralDaoImpl.class);

	public static final Integer PAGING_MAX_RECORD = 20;
	public static final String STATUS_ATTRIBUTE = "status";

	private final WherePredicateBuilder<M> conditionEquals;
	private final WherePredicateBuilder<M> conditionNotEquals;
	private final WherePredicateBuilder<M> conditionLike;
	private final WherePredicateBuilder<M> conditionGreaterThan;
	private final WherePredicateBuilder<M> conditionLessThan;
	private final WherePredicateBuilder<M> conditionGreaterThanEquals;
	private final WherePredicateBuilder<M> conditionLessThanEquals;
	private final WherePredicateBuilder<M> conditionBetween;
	private final WherePredicateBuilder<M> conditionIn;
	private final WherePredicateBuilder<M> conditionIsNull;
	private final WherePredicateBuilder<M> conditionIsNotNull;
	private final OrderBuilder<M> orderBuilder;

	protected GeneralDaoImpl(@Qualifier("conditionEquals") WherePredicateBuilder<M> conditionEquals,
			@Qualifier("conditionNotEquals") WherePredicateBuilder<M> conditionNotEquals,
			@Qualifier("conditionLike") WherePredicateBuilder<M> conditionLike,
			@Qualifier("conditionGreaterThan") WherePredicateBuilder<M> conditionGreaterThan,
			@Qualifier("conditionLessThan") WherePredicateBuilder<M> conditionLessThan,
			@Qualifier("conditionGreaterThanEquals") WherePredicateBuilder<M> conditionGreaterThanEquals,
			@Qualifier("conditionLessThanEquals") WherePredicateBuilder<M> conditionLessThanEquals,
			@Qualifier("conditionBetween") WherePredicateBuilder<M> conditionBetween,
			@Qualifier("conditionIn") WherePredicateBuilder<M> conditionIn,
			@Qualifier("conditionIsNull") WherePredicateBuilder<M> conditionIsNull,
			@Qualifier("conditionIsNotNull") WherePredicateBuilder<M> conditionIsNotNull,
			OrderBuilder<M> orderBuilder) {
		this.conditionEquals = conditionEquals;
		this.conditionNotEquals = conditionNotEquals;
		this.conditionLike = conditionLike;
		this.conditionGreaterThan = conditionGreaterThan;
		this.conditionLessThan = conditionLessThan;
		this.conditionGreaterThanEquals = conditionGreaterThanEquals;
		this.conditionLessThanEquals = conditionLessThanEquals;
		this.conditionBetween = conditionBetween;
		this.conditionIn = conditionIn;
		this.conditionIsNull = conditionIsNull;
		this.conditionIsNotNull = conditionIsNotNull;
		this.orderBuilder = orderBuilder;
	}

	protected EntityManager entityManager;
	private Class<M> entityClass;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@SuppressWarnings("unchecked")
	public Class<M> getEntityClass() {
		if (entityClass == null) {
			entityClass = (Class<M>) ((ParameterizedType) getClass().getGenericSuperclass())
					.getActualTypeArguments()[0];
		}
		return entityClass;
	}

	public void setEntityClass(Class<M> entityClass) {
		this.entityClass = entityClass;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<M> getAll() {
		return this.entityManager.createQuery("SELECT entity FROM " + getEntityClass().getSimpleName() + " entity")
				.getResultList();
	}

	@Override
	public M get(ID id) {
		M entity = this.entityManager.find(getEntityClass(), id);
		if (entity == null) {
			throw new GeneralException(ErrorConstant.DATA_NOT_FOUND);
		}
		return entity;
	}

	@Override
	@Transactional
	public M save(M entity) {
		return this.entityManager.merge(entity);
	}

	@Override
	@Transactional
	public void remove(ID id) {
		M entity = get(id);
		if (entity != null) {
			this.entityManager.remove(entity);
		}
	}

	@Override
	public Paging<M> getAll(Integer pageNumber, Integer pageSize, List<ParameterDto> parameters,
			List<OrderDto> orders) {
		Integer startPosition = null;

		if (pageNumber != null && pageSize != null) {
			startPosition = this.getPage(pageNumber, pageSize);
		} else if (pageNumber != null || pageSize != null) {
			throw new GeneralException(ErrorConstant.INVALID_PARAMETER);
		}

		List<M> data = this.getAllPagingData(startPosition, pageSize, parameters, orders);
		Long recordTotal = this.getSelectCount(parameters);
		Integer pageTotal = this.getTotalPage(recordTotal, pageSize);

		return new Paging<>(pageNumber, pageSize, recordTotal, pageTotal, data);
	}

	@SuppressWarnings("unchecked")
	private List<M> getAllPagingData(Integer pageNumber, Integer pageSize, List<ParameterDto> parameters,
			List<OrderDto> orders) {
		try {
			CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
			CriteriaQuery<M> criteriaQuery = criteriaBuilder.createQuery(this.getEntityClass());
			Root<M> root = criteriaQuery.from(this.getEntityClass());

			List<Predicate> predicates = this.criteriaWhereBuilder(criteriaBuilder, root, parameters);
			List<jakarta.persistence.criteria.Order> criteriaOrders = this.criteriaOrderBuilder(criteriaBuilder, root,
					orders);
			this.criteriaQueryBuilder(criteriaQuery, root, criteriaBuilder, predicates, criteriaOrders);
			Query query = this.entityManager.createQuery(criteriaQuery);

			if (pageNumber != null && pageSize != null) {
				query.setFirstResult(pageNumber)
						.setMaxResults((String.valueOf(pageSize) == null ? PAGING_MAX_RECORD : pageSize));
			}

			return query.getResultList();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralException(ErrorConstant.INVALID_PARAMETER);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<M> getAll(List<ParameterDto> parameters, List<OrderDto> orders) {
		try {
			CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
			CriteriaQuery<M> criteriaQuery = criteriaBuilder.createQuery(this.getEntityClass());
			Root<M> root = criteriaQuery.from(this.getEntityClass());

			List<Predicate> predicates = this.criteriaWhereBuilder(criteriaBuilder, root, parameters);
			List<Order> criteriaOrders = this.criteriaOrderBuilder(criteriaBuilder, root,
					orders);
			this.criteriaQueryBuilder(criteriaQuery, root, criteriaBuilder, predicates, criteriaOrders);
			Query query = this.entityManager.createQuery(criteriaQuery);

			return query.getResultList();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralException(ErrorConstant.INVALID_PARAMETER);
		}
	}

	@Override
	public Long getSelectCount(List<ParameterDto> parameters) {
		try {
			CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
			CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
			Root<M> root = criteriaQuery.from(this.getEntityClass());

			List<Predicate> predicates = this.criteriaWhereBuilder(criteriaBuilder, root, parameters);
			criteriaQuery.select(criteriaBuilder.count(root)).where(predicates.toArray(new Predicate[] {}));
			Query query = this.entityManager.createQuery(criteriaQuery);

			return (Long) query.getSingleResult();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralException(ErrorConstant.INVALID_PARAMETER);
		}
	}

	@Override
	public Integer getSelectSum(String fieldName, List<ParameterDto> parameters) {
		try {
			CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
			CriteriaQuery<Integer> criteriaQuery = criteriaBuilder.createQuery(Integer.class);
			Root<M> root = criteriaQuery.from(this.getEntityClass());

			List<Predicate> predicates = this.criteriaWhereBuilder(criteriaBuilder, root, parameters);
			criteriaQuery.select(criteriaBuilder.sum(root.get(fieldName)))
					.where(predicates.toArray(new Predicate[] {}));
			Query query = this.entityManager.createQuery(criteriaQuery);

			return (Integer) query.getSingleResult();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralException(ErrorConstant.INVALID_PARAMETER);
		}
	}

	@Override
	public Integer getSelectMin(String fieldName, List<ParameterDto> parameters) {
		try {
			CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
			CriteriaQuery<Integer> criteriaQuery = criteriaBuilder.createQuery(Integer.class);
			Root<M> root = criteriaQuery.from(this.getEntityClass());

			List<Predicate> predicates = this.criteriaWhereBuilder(criteriaBuilder, root, parameters);
			criteriaQuery.select(criteriaBuilder.min(root.get(fieldName)))
					.where(predicates.toArray(new Predicate[] {}));
			Query query = this.entityManager.createQuery(criteriaQuery);

			return (Integer) query.getSingleResult();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralException(ErrorConstant.INVALID_PARAMETER);
		}
	}

	@Override
	public Integer getSelectMax(String fieldName, List<ParameterDto> parameters) {
		try {
			CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
			CriteriaQuery<Integer> criteriaQuery = criteriaBuilder.createQuery(Integer.class);
			Root<M> root = criteriaQuery.from(this.getEntityClass());

			List<Predicate> predicates = this.criteriaWhereBuilder(criteriaBuilder, root, parameters);
			criteriaQuery.select(criteriaBuilder.max(root.get(fieldName)))
					.where(predicates.toArray(new Predicate[] {}));
			Query query = this.entityManager.createQuery(criteriaQuery);

			return (Integer) query.getSingleResult();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralException(ErrorConstant.INVALID_PARAMETER);
		}
	}

	@Override
	public Double getSelectAverage(String fieldName, List<ParameterDto> parameters) {
		try {
			CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
			CriteriaQuery<Double> criteriaQuery = criteriaBuilder.createQuery(Double.class);
			Root<M> root = criteriaQuery.from(this.getEntityClass());

			List<Predicate> predicates = this.criteriaWhereBuilder(criteriaBuilder, root, parameters);
			criteriaQuery.select(criteriaBuilder.avg(root.get(fieldName)))
					.where(predicates.toArray(new Predicate[] {}));
			Query query = this.entityManager.createQuery(criteriaQuery);

			return (Double) query.getSingleResult();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralException(ErrorConstant.INVALID_PARAMETER);
		}
	}

	protected List<Predicate> criteriaWhereBuilder(CriteriaBuilder criteriaBuilder, Root<M> root,
			List<ParameterDto> parameters) {
		List<Predicate> predicates = new ArrayList<>();

		if (parameters != null && !parameters.isEmpty()) {
			for (ParameterDto parameter : parameters) {
				if (parameter.getCondition().equals(ConditionEnum.EQUALS)) {
					predicates.add(conditionEquals.getPredicate(criteriaBuilder, root, parameter));
				} else if (parameter.getCondition().equals(ConditionEnum.NOT_EQUALS)) {
					predicates.add(conditionNotEquals.getPredicate(criteriaBuilder, root, parameter));
				} else if (parameter.getCondition().equals(ConditionEnum.LIKE)) {
					predicates.add(conditionLike.getPredicate(criteriaBuilder, root, parameter));
				} else if (parameter.getCondition().equals(ConditionEnum.GREATER_THAN)) {
					predicates.add(conditionGreaterThan.getPredicate(criteriaBuilder, root, parameter));
				} else if (parameter.getCondition().equals(ConditionEnum.LESS_THAN)) {
					predicates.add(conditionLessThan.getPredicate(criteriaBuilder, root, parameter));
				} else if (parameter.getCondition().equals(ConditionEnum.GREATER_THAN_EQUALS)) {
					predicates.add(conditionGreaterThanEquals.getPredicate(criteriaBuilder, root, parameter));
				} else if (parameter.getCondition().equals(ConditionEnum.LESS_THAN_EQUALS)) {
					predicates.add(conditionLessThanEquals.getPredicate(criteriaBuilder, root, parameter));
				} else if (parameter.getCondition().equals(ConditionEnum.BETWEEN)) {
					predicates.add(conditionBetween.getPredicate(criteriaBuilder, root, parameter));
				} else if (parameter.getCondition().equals(ConditionEnum.IN)) {
					predicates.add(conditionIn.getPredicate(criteriaBuilder, root, parameter));
				} else if (parameter.getCondition().equals(ConditionEnum.IS_NULL)) {
					predicates.add(conditionIsNull.getPredicate(criteriaBuilder, root, parameter));
				} else if (parameter.getCondition().equals(ConditionEnum.IS_NOT_NULL)) {
					predicates.add(conditionIsNotNull.getPredicate(criteriaBuilder, root, parameter));
				}
			}
		}

		predicates.add(criteriaBuilder
				.and(criteriaBuilder.or(criteriaBuilder.notEqual(root.get(STATUS_ATTRIBUTE), StatusEnum.HIST),
						criteriaBuilder.isNull(root.get(STATUS_ATTRIBUTE)))));

		return predicates;
	}

	protected List<jakarta.persistence.criteria.Order> criteriaOrderBuilder(CriteriaBuilder criteriaBuilder, Root<M> root,
			List<OrderDto> orders) {
		List<jakarta.persistence.criteria.Order> criteriaOrders = new ArrayList<>();
		if (orders != null && !orders.isEmpty()) {
			for (OrderDto order : orders) {
				criteriaOrders.add(orderBuilder.getOrder(criteriaBuilder, root, order));
			}
		}
		return criteriaOrders;
	}

	protected void criteriaQueryBuilder(CriteriaQuery<M> criteriaQuery, Root<M> root, CriteriaBuilder criteriaBuilder,
			List<Predicate> criteriaWhere, List<jakarta.persistence.criteria.Order> criteriaOrder) {
		if (criteriaOrder != null && !criteriaOrder.isEmpty()) {
			if (criteriaWhere != null && !criteriaWhere.isEmpty()) {
				criteriaQuery.select(root).where(criteriaWhere.toArray(new Predicate[] {})).orderBy(criteriaOrder);
			} else {
				criteriaQuery.select(root).orderBy(criteriaOrder);
			}
		} else {
			if (criteriaWhere != null && !criteriaWhere.isEmpty()) {
				criteriaQuery.select(root).where(criteriaWhere.toArray(new Predicate[] {}))
						.orderBy(criteriaBuilder.desc(root.get("id")));
			} else {
				criteriaQuery.select(root).orderBy(criteriaBuilder.desc(root.get("id")));
			}
		}
	}

	protected Integer getPage(int page, int pageSize) {
		return (page - 1) * pageSize;
	}

	protected Integer getTotalPage(Long totalRecord, Integer pageSize) {
		Double totalPage = (double) (totalRecord.intValue() + pageSize - 1) / pageSize;
		return totalPage.intValue();
	}
}
