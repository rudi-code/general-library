package id.co.bjj.library.general.persistence.daos;

import java.io.Serializable;
import java.util.List;

import id.co.bjj.library.general.persistence.dtos.OrderDto;
import id.co.bjj.library.general.persistence.dtos.Paging;
import id.co.bjj.library.general.persistence.dtos.ParameterDto;

public interface GeneralFlexibleDao<M, ID extends Serializable> {
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
	 * Select all data by paging
	 * 
	 * @author Steve Sentosa, 20 May 2024
	 * @param pageNumber -> page number
	 * @param pageSize   -> limit per page
	 * @param parameters List<ParameterDto> -> for where
	 * @param orders     List<OrderDto> -> for order
	 * @return
	 */
	public Paging<M> getAll(Integer pageNumber, Integer pageSize, List<ParameterDto> parameters, List<OrderDto> orders);

	/**
	 * Select all data by parameters and orders
	 * 
	 * @author Steve Sentosa, 20 May 2024
	 * @param parameters List<ParameterDto> -> for where
	 * @param orders     List<OrderDto> -> for order
	 * @return
	 */
	public List<M> getAll(List<ParameterDto> parameters, List<OrderDto> orders);

	/**
	 * Aggregate function select count
	 * 
	 * @author Steve Sentosa, 20 May 2024
	 * @param parameters List<ParameterDto> -> for where
	 * @return
	 */
	public Long getSelectCount(List<ParameterDto> parameters);

	/**
	 * Aggregate function select sum
	 * 
	 * @author Steve Sentosa, 20 May 2024
	 * @param fieldName
	 * @param parameters List<ParameterDto> -> for where
	 * @return
	 */
	public Integer getSelectSum(String fieldName, List<ParameterDto> parameters);

	/**
	 * Aggregate function select min value
	 * 
	 * @author Steve Sentosa, 20 May 2024
	 * @param fieldName
	 * @param parameters List<ParameterDto> -> for where
	 * @return
	 */
	public Integer getSelectMin(String fieldName, List<ParameterDto> parameters);

	/**
	 * Aggregate function select max value
	 * 
	 * @author Steve Sentosa, 20 May 2024
	 * @param fieldName
	 * @param parameters List<ParameterDto> -> for where
	 * @return
	 */
	public Integer getSelectMax(String fieldName, List<ParameterDto> parameters);

	/**
	 * Aggregate function select average
	 * 
	 * @author Steve Sentosa, 20 May 2024
	 * @param fieldName
	 * @param parameters List<ParameterDto> -> for where
	 * @return
	 */
	public Double getSelectAverage(String fieldName, List<ParameterDto> parameters);
}
