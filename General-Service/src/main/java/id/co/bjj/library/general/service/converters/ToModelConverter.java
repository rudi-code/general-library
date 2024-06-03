package id.co.bjj.library.general.service.converters;

import java.util.List;

/**
 * Interface from other object to Model
 * 
 * @author Steve Sentosa, 20 May 2024
 * @param <OBJECT>
 * @param <MODEL>
 */
public interface ToModelConverter<OBJECT, MODEL> {
	MODEL toModel(OBJECT object);

	List<MODEL> toModels(List<OBJECT> objects);
}
