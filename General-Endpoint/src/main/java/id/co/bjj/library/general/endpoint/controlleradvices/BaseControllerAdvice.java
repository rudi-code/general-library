package id.co.bjj.library.general.endpoint.controlleradvices;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;

import id.co.bjj.library.general.core.constants.ErrorConstant;
import id.co.bjj.library.general.core.exceptions.Error;
import id.co.bjj.library.general.core.exceptions.GeneralException;
import id.co.bjj.library.general.core.exceptions.MultipleGeneralException;
import id.co.bjj.library.general.core.exceptions.SnapBiException;
import id.co.bjj.library.general.core.utils.XTimestampStringUtil;
import id.co.bjj.library.general.endpoint.contracts.BaseResponse;
import id.co.bjj.library.general.endpoint.contracts.SnapBiErrorResponse;

public class BaseControllerAdvice {

	/**
	 * Get Field name from exception
	 * 
	 * @author Steve Sentosa, 20 May 2024
	 * @param exception
	 * @return
	 */
	public Set<String> getFieldName(InvalidFormatException exception) {
		Set<String> result = new HashSet<>();
		result.add(exception.getPath().get(exception.getPath().size() - 1).getFieldName());
		return result;
	}

	/**
	 * Get Field name from exception
	 * 
	 * @author Steve Sentosa, 20 May 2024
	 * @param exception
	 * @return
	 */
	public Set<String> getFieldName(UnrecognizedPropertyException e) {
		Set<String> result = new HashSet<>();
		result.add(e.getPath().get(e.getPath().size() - 1).getFieldName());
		return result;
	}

	/**
	 * Get Field name from exception
	 * 
	 * @author Steve Sentosa, 20 May 2024
	 * @param exception
	 * @return
	 */
	public Set<String> getFieldName(MismatchedInputException e) {
		List<Reference> refs = e.getPath();
		Set<String> fields = new HashSet<>();
		for (Reference r : refs) {
			if (r.getFieldName() != null) {
				fields.add(r.getFieldName());
			}
		}
		return fields;
	}

	/**
	 * Function retun response error from general exception
	 * 
	 * @author Steve Sentosa, 20 May 2024
	 * @param exception
	 * @return
	 */
	public static ResponseEntity<BaseResponse> createErrorResponse(GeneralException exception) {
		BaseResponse baseResponse = new BaseResponse();

		if (exception.getError() != null) {
			baseResponse.setMessage(exception.getMessage());
			baseResponse.setLocalizedMessage(exception.getLocalizedMessage());
		} else {
			Error defaultError = ErrorConstant.FAILED;
			baseResponse.setMessage(defaultError.getMessage());
			baseResponse.setLocalizedMessage(defaultError.getLocalizedMessage());
		}

		HttpStatus httpStatus = HttpStatus.valueOf(exception.getHttpStatus());
		baseResponse.setErrors(exception.getAllErrors());
		baseResponse.setResponseCode("00");

		return new ResponseEntity<>(baseResponse, httpStatus);
	}

	/**
	 * Function return response error for snap bi dummy
	 * 
	 * @author Steve Sentosa, 20 May 2024
	 * @param exception
	 * @return
	 */
	public static ResponseEntity<SnapBiErrorResponse> createErrorResponseSnapBI(SnapBiException exception) {
		SnapBiErrorResponse snapBiErrorResponse = new SnapBiErrorResponse();

		if (exception.getError() != null) {
			snapBiErrorResponse.setResponseMessage(exception.getMessage());
		}

		HttpStatus httpStatus = HttpStatus.valueOf(exception.getHttpStatus());
		snapBiErrorResponse.setResponseCode(exception.getError().getCode());

		Date date = new Date();
		String xTimestamp = XTimestampStringUtil.convertDate(date);
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("X-TIMESTAMP", xTimestamp);
		headers.add("Content-type", "application/json");		
				
		return new ResponseEntity<>(snapBiErrorResponse, headers, httpStatus);
	}

	/**
	 * Function return response multiple error from multiple general exception
	 * 
	 * @author Steve Sentosa, 20 May 2024
	 * @param exception
	 * @return
	 */
	public static ResponseEntity<BaseResponse> createErrorResponse(MultipleGeneralException exception) {
		BaseResponse baseResponseError = new BaseResponse();

		if (exception.getMultipleErrors() != null && !exception.getMultipleErrors().isEmpty()) {
			baseResponseError.setMessage(exception.getMessage());
			baseResponseError.setLocalizedMessage(exception.getLocalizedMessage());
		} else {
			Error defaultError = ErrorConstant.FAILED;
			baseResponseError.setMessage(defaultError.getMessage());
			baseResponseError.setLocalizedMessage(defaultError.getLocalizedMessage());
		}

		HttpStatus httpStatus = HttpStatus.valueOf(exception.getHttpStatus());
		baseResponseError.setResponse(exception.getMultipleErrors());
		baseResponseError.setResponseCode("00");

		return new ResponseEntity<>(baseResponseError, httpStatus);
	}
}
