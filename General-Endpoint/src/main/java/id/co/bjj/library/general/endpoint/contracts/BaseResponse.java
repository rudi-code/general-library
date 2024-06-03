package id.co.bjj.library.general.endpoint.contracts;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import id.co.bjj.library.general.core.exceptions.Error;
import id.co.bjj.library.general.core.constants.ErrorConstant;

/**
 * Class for general response
 * 
 * @author Steve Sentosa, 20 May 2024
 */
@JsonInclude(Include.NON_NULL)
public class BaseResponse implements Serializable {
	private static final long serialVersionUID = -1563846408191037745L;
	private static final String ERROR_CODE = "00";
	private static final String SUCCESS_CODE = "01";

	private String responseCode = SUCCESS_CODE;
	private String message = ErrorConstant.SUCCESS.getMessage();
	private String localizedMessage = ErrorConstant.SUCCESS.getLocalizedMessage();
	private Object response;
	private List<Error> errors;

	public List<Error> getErrors() {
		return errors;
	}

	public void setErrors(List<Error> listErrorCodes) {
		setResponseCode(ERROR_CODE);
		this.errors = listErrorCodes;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getLocalizedMessage() {
		return localizedMessage;
	}

	public void setLocalizedMessage(String messageLocalize) {
		this.localizedMessage = messageLocalize;
	}

	public Object getResponse() {
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}
}
