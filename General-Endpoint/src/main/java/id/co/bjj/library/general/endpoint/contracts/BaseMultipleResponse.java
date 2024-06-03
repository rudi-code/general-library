package id.co.bjj.library.general.endpoint.contracts;

import java.util.List;

import id.co.bjj.library.general.core.exceptions.MultipleError;

/**
 * Class for general multiple response
 * @author Steve Sentosa, 28 October 2021
 */
public class BaseMultipleResponse extends BaseResponse {
	private static final long serialVersionUID = 6602791938996167013L;

	private Integer totalError;
	private Integer totalSuccess;

	public BaseMultipleResponse() {
		super();
	}

	public BaseMultipleResponse(List<MultipleError> multipleErrors, Integer totalRow) {
		super();
		this.totalError = multipleErrors.size();
		this.totalSuccess = totalRow - this.totalError;
		super.setResponse(multipleErrors);
	}

	public Integer getTotalError() {
		return totalError;
	}

	public void setTotalError(Integer totalError) {
		this.totalError = totalError;
	}

	public Integer getTotalSuccess() {
		return totalSuccess;
	}

	public void setTotalSuccess(Integer totalSuccess) {
		this.totalSuccess = totalSuccess;
	}
}
