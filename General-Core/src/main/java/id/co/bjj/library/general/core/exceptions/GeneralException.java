package id.co.bjj.library.general.core.exceptions;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.extern.slf4j.Slf4j;

/**
 * Custom exception handler
 * 
 * @author Steve Sentosa, 20 May 2024
 */
@Slf4j
public class GeneralException extends RuntimeException {
	private static final long serialVersionUID = -325835457292194541L;
	private List<Error> errors = new ArrayList<>();
	private Error error;
	protected int httpStatus;

	public GeneralException() {
		super();
	}

	public GeneralException(List<Error> errors, int httpStatus) {
		super();
		this.errors = errors == null ? new ArrayList<>() : errors;
		this.httpStatus = httpStatus;
	}

	public GeneralException(Error exceptionKey) {
		super(exceptionKey.getMessage());
		this.error = this.fetchError(exceptionKey);
		if (exceptionKey.getHttpStatus() == 0 || exceptionKey.getHttpStatus() == null) {
			exceptionKey.setHttpStatus(400);
		}
		this.httpStatus = exceptionKey.getHttpStatus();
	}

	public GeneralException(String message, Throwable t) {
		if (t instanceof GeneralException generalException) {
			GeneralException exception = generalException;
			this.errors = exception.getErrors();
			this.error = exception.getError();
			this.httpStatus = exception.getHttpStatus();
		} else {
			this.httpStatus = 500;
		}
	}

	@JsonIgnore
	public List<Error> getAllErrors() {
		List<Error> allError = new ArrayList<>();
		if (getError() != null) {
			allError.add(getError());
		}

		for (Error error : errors) {
			allError.add(error);
		}

		return allError;
	}

	public List<Error> getErrors() {
		return errors;
	}

	public void setErrors(List<Error> errors) {
		this.errors = errors;
	}

	public Error getError() {
		return error;
	}

	public Integer getHttpStatus() {
		return httpStatus;
	}

	@Override
	public String getMessage() {
		if (error != null) {
			return error.getMessage();
		} else {
			return super.getMessage();
		}
	}

	@Override
	public String getLocalizedMessage() {
		if (error != null) {
			return error.getLocalizedMessage();
		} else {
			return super.getLocalizedMessage();
		}
	}

	private Error fetchError(Error errorConstant) {
		try {
			return errorConstant.clone();
		} catch (CloneNotSupportedException e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
}
