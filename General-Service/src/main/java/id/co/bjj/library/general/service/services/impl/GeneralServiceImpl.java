package id.co.bjj.library.general.service.services.impl;

import java.io.Serializable;
import java.util.List;

import id.co.bjj.library.general.core.constants.ErrorConstant;
import id.co.bjj.library.general.core.exceptions.GeneralException;
import id.co.bjj.library.general.persistence.daos.GeneralDao;
import id.co.bjj.library.general.persistence.dtos.OrderDto;
import id.co.bjj.library.general.persistence.dtos.Paging;
import id.co.bjj.library.general.persistence.dtos.ParameterDto;
import id.co.bjj.library.general.persistence.enums.StatusEnum;
import id.co.bjj.library.general.persistence.models.GeneralModel;
import id.co.bjj.library.general.service.services.GeneralService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class GeneralServiceImpl<M extends GeneralModel, ID extends Serializable>
		implements GeneralService<M, ID> {

	private final GeneralDao<M, ID> generalDao;

	protected GeneralServiceImpl(GeneralDao<M, ID> generalDao) {
		this.generalDao = generalDao;
	}

	@Override
	public M save(M model) {
		log.info("General Service, Save");
		
		if (model == null) {
			throw new GeneralException(ErrorConstant.NULL_ENTITY);
		}
		
		if (model.getStatus() == null) {
			model.setStatus(StatusEnum.LIVE);
		}
		
		return generalDao.save(model);
	}

	@Override
	public List<M> getAll() {
		log.info("General Service, Get All");
		
		return generalDao.getAll();
	}

	@Override
	public M get(ID id) {
		log.info("General Service, Get");
		
		return generalDao.get(id);
	}

	@Override
	public void remove(ID id) {
		log.info("General Service, Remove");
		
		generalDao.remove(id);
	}

	@Override
	public Paging<M> getAll(Integer pageNumber, Integer pageSize, List<ParameterDto> parameters,
			List<OrderDto> orders) {
		log.info("General Service, Get All Paging");
		
		return generalDao.getAll(pageNumber, pageSize, parameters, orders);
	}

	@Override
	public List<M> getAll(List<ParameterDto> parameters, List<OrderDto> orders) {
		log.info("General Service, Get All");
		
		return generalDao.getAll(parameters, orders);
	}

	@Override
	public Long getSelectCount(List<ParameterDto> parameters) {
		log.info("General Service, Get Select Count");
		
		return generalDao.getSelectCount(parameters);
	}

	@Override
	public Integer getSelectSum(String fieldName, List<ParameterDto> parameters) {
		log.info("General Service, Get Select Sum");
		
		return generalDao.getSelectSum(fieldName, parameters);
	}

	@Override
	public Integer getSelectMax(String fieldName, List<ParameterDto> parameters) {
		log.info("General Service, Get Select Max");
		
		return generalDao.getSelectMax(fieldName, parameters);
	}

	@Override
	public Integer getSelectMin(String fieldName, List<ParameterDto> parameters) {
		log.info("General Service, Get Select Min");
		
		return generalDao.getSelectMin(fieldName, parameters);
	}

	@Override
	public Double getSelectAverage(String fieldName, List<ParameterDto> parameters) {
		log.info("General Service, Get Select Average");
		
		return generalDao.getSelectAverage(fieldName, parameters);
	}
}
