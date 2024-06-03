package id.co.bjj.library.general.core.exceptions;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Error implements Serializable, Cloneable {
	private static final long serialVersionUID = 7790958190208911773L;

	private String code;
	private String message;
	private String localizedMessage;
	private Integer httpStatus;
	private Set<String> field;

	public Error() {
		super();
	}

	public Error(String code, String message, String localizedMessage) {
		super();
		this.code = code;
		this.message = message;
		this.localizedMessage = localizedMessage;
	}

	public Error(String code, String message, String messageLocalize, Integer httpStatus) {
		super();
		this.code = code;
		this.message = message;
		this.localizedMessage = messageLocalize;
		this.httpStatus = httpStatus;
	}

	public String getLocalizedMessage() {
		return localizedMessage;
	}

	public void setLocalizedMessage(String localizeMessage) {
		this.localizedMessage = localizeMessage;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(Integer httpStatus) {
		this.httpStatus = httpStatus;
	}

	public Set<String> getField() {
		return field == null ? new HashSet<String>() : this.field;
	}

	public void setField(Set<String> field) {
		this.field = field == null ? new HashSet<String>() : field;
		;
	}

	public void addField(String field) {
		if (this.field == null) {
			this.field = new HashSet<>();
		}
		this.field.add(field);
	}

	@Override
	protected Error clone() throws CloneNotSupportedException {
		Error error = (Error) super.clone();
		error.getField().clear();
		return error;
	}
}
