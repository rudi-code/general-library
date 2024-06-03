package id.co.bjj.library.general.service.converters;

import java.util.List;

/**
 * Interface converter from other object to dto
 * 
 * @author Steve Sentosa, 20 May 2024
 * @param <OBJECT>
 * @param <DTO>
 */
public interface ToDtoConverter<OBJECT, DTO> {
	DTO toDto(OBJECT object);

	List<DTO> toDtos(List<OBJECT> objects);
}
