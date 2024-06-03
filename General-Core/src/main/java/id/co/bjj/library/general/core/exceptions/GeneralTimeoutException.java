package id.co.bjj.library.general.core.exceptions;

import id.co.bjj.library.general.core.constants.ErrorConstant;

public class GeneralTimeoutException extends GeneralException {
	private static final long serialVersionUID = -7548097924993267377L;

	public GeneralTimeoutException() {
		super(ErrorConstant.THREAD_TIMEOUT);
	}

	public GeneralTimeoutException(Error error) {
		super(error);
	}
}
