package id.co.bjj.library.general.core.exceptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Multiple custom exception handler
 * @author Steve Sentosa, 26-10-2021 
 */
public class MultipleGeneralException extends GeneralException {
	private static final long serialVersionUID = 3399319941105168133L;

	private MultipleError multipleError = new MultipleError();
	private List<MultipleError> multipleErrors = new ArrayList<>();

	public MultipleGeneralException() {
		super();
	}

	public MultipleGeneralException(Error exceptionKey) {
		super(exceptionKey);
		this.multipleError = this.getError(super.getError());
	}

	public MultipleGeneralException(List<Error> errors, int httpStatus) {
		super(errors, httpStatus);
		for (Error error : errors) {
			this.multipleErrors.add(this.getError(error));
		}
	}
	
	public MultipleGeneralException(List<MultipleError> bulkingErrors) {
		this.multipleError = this.getMultipleError();
		this.multipleErrors.addAll(bulkingErrors);
		super.httpStatus = 500;
	}

	public MultipleGeneralException(String message, Throwable t) {
		super(message, t);
	}
	
	private MultipleError getError(Error error) {
		MultipleError result = new MultipleError();
		result.setCode(error.getCode());
		result.setLocalizedMessage(error.getLocalizedMessage());
		result.setMessage(error.getMessage());
		result.setField(error.getField());
		result.setHttpStatus(error.getHttpStatus());

		return result;
	}
	
	public MultipleError getMultipleError() {
		return multipleError;
	}

	public void setMultipleError(MultipleError multipleError) {
		this.multipleError = multipleError;
	}

	public List<MultipleError> getMultipleErrors() {
		return multipleErrors == null ? new ArrayList<>() : multipleErrors;
	}

	public void setMultipleErrors(List<MultipleError> multipleErrors) {
		this.multipleErrors = multipleErrors;
	}

}
