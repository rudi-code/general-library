package id.co.bjj.library.general.service.services;

import java.io.Serializable;
import java.util.List;

import id.co.bjj.library.general.persistence.dtos.OrderDto;
import id.co.bjj.library.general.persistence.dtos.Paging;
import id.co.bjj.library.general.persistence.dtos.ParameterDto;
import id.co.bjj.library.general.persistence.models.GeneralModel;

public interface GeneralService<M extends GeneralModel, ID extends Serializable> {

	/**
	 * Retrieve all or get all data without filter
	 * 
	 * @author Steve Sentosa, 20 May 2024
	 * @return
	 */
	public List<M> getAll();

	/**
	 * Retrieve or get one data from select
	 * 
	 * @author Steve Sentosa, 20 May 2024
	 * @param id
	 * @return
	 */
	public M get(ID id);

	/**
	 * Insert process if new data, and if user bought id it will be update process
	 * 
	 * @author Steve Sentosa, 20 May 2024
	 * @param model
	 * @return
	 */
	public M save(M model);

	/**
	 * Remove or delete the data
	 * 
	 * @param id
	 */
	public void remove(ID id);

	/**
	 * Get aggregate function average data with feature where
	 * 
	 * @author Steve Sentosa, 20 May 2024
	 * @param parameters
	 * @return
	 */
	public Double getSelectAverage(String fieldName, List<ParameterDto> parameters);

	/**
	 * Get aggregate function min data with feature where
	 * 
	 * @author Steve Sentosa, 20 May 2024
	 * @param parameters
	 * @return
	 */
	public Integer getSelectMin(String fieldName, List<ParameterDto> parameters);

	/**
	 * Get aggregate function max data with feature where
	 * 
	 * @author Steve Sentosa, 20 May 2024
	 * @param parameters
	 * @return
	 */
	public Integer getSelectMax(String fieldName, List<ParameterDto> parameters);

	/**
	 * Get aggregate function sum data with feature where
	 * 
	 * @author Steve Sentosa, 20 May 2024
	 * @param parameters
	 * @return
	 */
	public Integer getSelectSum(String fieldName, List<ParameterDto> parameters);

	/**
	 * Get aggregate function count data with feature where
	 * 
	 * @author Steve Sentosa, 20 May 2024
	 * @param parameters
	 * @return
	 */
	Long getSelectCount(List<ParameterDto> parameters);

	/**
	 * Select data with feature where and order
	 * 
	 * @author Steve Sentosa, 20 May 2024
	 * @param parameters
	 * @param orders
	 * @return
	 */
	public List<M> getAll(List<ParameterDto> parameters, List<OrderDto> orders);

	/**
	 * Select all data with feature paging where and order
	 * 
	 * @author Steve Sentosa, 20 May 2024
	 * @param pageNumber
	 * @param pageSize
	 * @param parameters
	 * @param orders
	 * @return
	 */
	public Paging<M> getAll(Integer pageNumber, Integer pageSize, List<ParameterDto> parameters, List<OrderDto> orders);
}
