package id.co.bjj.library.general.service.converters;

import java.util.List;

/**
 * Interface from other object to Contract
 * 
 * @author Steve Sentosa, 20 May 2024
 * @param <OBJECT>
 * @param <CONTRACT>
 */
public interface ToContractConverter<OBJECT, CONTRACT> {
	CONTRACT toContract(OBJECT object);

	List<CONTRACT> toContracts(List<OBJECT> objects);
}
