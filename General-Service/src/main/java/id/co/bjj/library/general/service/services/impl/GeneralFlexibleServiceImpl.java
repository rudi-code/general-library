package id.co.bjj.library.general.service.services.impl;

import java.io.Serializable;
import java.util.List;

import id.co.bjj.library.general.core.constants.ErrorConstant;
import id.co.bjj.library.general.core.exceptions.GeneralException;
import id.co.bjj.library.general.persistence.daos.GeneralFlexibleDao;
import id.co.bjj.library.general.persistence.dtos.OrderDto;
import id.co.bjj.library.general.persistence.dtos.Paging;
import id.co.bjj.library.general.persistence.dtos.ParameterDto;
import id.co.bjj.library.general.service.services.GeneralFlexibleService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GeneralFlexibleServiceImpl<M, ID extends Serializable> implements GeneralFlexibleService<M, ID> {

	private final GeneralFlexibleDao<M, ID> generalFlexibleDao;

	protected GeneralFlexibleServiceImpl(GeneralFlexibleDao<M, ID> generalFlexibleDao) {
		this.generalFlexibleDao = generalFlexibleDao;
	}

	@Override
	public M save(M model) {
		log.info("General Flexible Service, Save");

		if (model == null) {
			throw new GeneralException(ErrorConstant.NULL_ENTITY);
		}

		return generalFlexibleDao.save(model);
	}

	@Override
	public List<M> getAll() {
		log.info("General Flexible Service , Get All");

		return generalFlexibleDao.getAll();
	}

	@Override
	public M get(ID id) {
		log.info("General Flexible Service, Get");

		return generalFlexibleDao.get(id);
	}

	@Override
	public void remove(ID id) {
		log.info("General Flexible Service, Remove");

		generalFlexibleDao.remove(id);
	}

	@Override
	public Paging<M> getAll(Integer pageNumber, Integer pageSize, List<ParameterDto> parameters,
			List<OrderDto> orders) {
		log.info("General Flexible Service, Get All Paging");

		return generalFlexibleDao.getAll(pageNumber, pageSize, parameters, orders);
	}

	@Override
	public List<M> getAll(List<ParameterDto> parameters, List<OrderDto> orders) {
		log.info("General Flexible Service, Get All");

		return generalFlexibleDao.getAll(parameters, orders);
	}

	@Override
	public Long getSelectCount(List<ParameterDto> parameters) {
		log.info("General Flexible Service, Get Select Count");

		return generalFlexibleDao.getSelectCount(parameters);
	}

	@Override
	public Integer getSelectSum(String fieldName, List<ParameterDto> parameters) {
		log.info("General Flexible Service, Get Select Sum");

		return generalFlexibleDao.getSelectSum(fieldName, parameters);
	}

	@Override
	public Integer getSelectMax(String fieldName, List<ParameterDto> parameters) {
		log.info("General Flexible Service, Get Select Max");

		return generalFlexibleDao.getSelectMax(fieldName, parameters);
	}

	@Override
	public Integer getSelectMin(String fieldName, List<ParameterDto> parameters) {
		log.info("General Flexible Service Get Select Min");

		return generalFlexibleDao.getSelectMin(fieldName, parameters);
	}

	@Override
	public Double getSelectAverage(String fieldName, List<ParameterDto> parameters) {
		log.info("General Flexible Service, Get Select Average");

		return generalFlexibleDao.getSelectAverage(fieldName, parameters);
	}
}
