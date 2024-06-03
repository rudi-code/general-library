package id.co.bjj.library.general.endpoint.contracts;

import java.io.Serializable;

import lombok.Data;

@Data
public class SnapBiErrorResponse implements Serializable {
	private static final long serialVersionUID = 6960719093898566271L;
	
	private String responseCode;
	private String responseMessage;
}
