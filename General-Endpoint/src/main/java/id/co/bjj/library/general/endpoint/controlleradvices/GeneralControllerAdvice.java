package id.co.bjj.library.general.endpoint.controlleradvices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;

import id.co.bjj.library.general.core.constants.ErrorConstant;
import id.co.bjj.library.general.core.exceptions.GeneralException;
import id.co.bjj.library.general.core.exceptions.MultipleGeneralException;
import id.co.bjj.library.general.core.exceptions.SnapBiException;
import id.co.bjj.library.general.endpoint.contracts.BaseResponse;
import id.co.bjj.library.general.endpoint.contracts.SnapBiErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * Class for handling all exception for REST API
 * 
 * @author Steve Sentosa, 20 May 2024
 */
@RestControllerAdvice
@Slf4j
public class GeneralControllerAdvice extends BaseControllerAdvice {

	/**
	 * Custom global handling exception
	 * 
	 * @author Steve Sentosa, 20 May 2024
	 * @param GeneralException exception
	 * @return ResponseEntity
	 */
	@ExceptionHandler(GeneralException.class)
	public static ResponseEntity<BaseResponse> handleGeneralException(final GeneralException exception) {
		log.error(exception.getMessage(), exception);
		return createErrorResponse(exception);
	}

	/**
	 * Custom global handling exception
	 * 
	 * @author Steve Sentosa, 20 May 2024
	 * @param SnapBIException exception
	 * @return ResponseEntity
	 */
	@ExceptionHandler(SnapBiException.class)
	public static ResponseEntity<SnapBiErrorResponse> handleSnapBIException(final SnapBiException exception) {
		log.error(exception.getMessage(), exception);
		return createErrorResponseSnapBI(exception);
	}

	/**
	 * Custom multiple global handling exception for request
	 * 
	 * @author Steve Sentosa, 20 May 2024
	 * @param MultipleGeneralException exception
	 * @return ResponseEntity
	 */
	@ExceptionHandler(MultipleGeneralException.class)
	public static ResponseEntity<BaseResponse> handleBulkGeneralException(final MultipleGeneralException exception) {
		log.error(exception.getMessage(), exception);
		return createErrorResponse(exception);
	}

	/**
	 * Error handling when data parameter url is not found
	 * 
	 * @author Steve Sentosa, 20 May 2024
	 * @param MissingPathVariableException exception
	 * @return ResponseEntity
	 */
	@ExceptionHandler(MissingPathVariableException.class)
	public ResponseEntity<BaseResponse> handleMissingPathVariableException(MissingPathVariableException exception) {
		log.error(exception.getMessage(), exception);
		return createErrorResponse(new GeneralException(ErrorConstant.API_NOT_FOUND));
	}

	/**
	 * Error handling when url api not found
	 * 
	 * @author Steve Sentosa, 20 May 2024
	 * @param NoHandlerFoundException exception
	 * @return ResponseEntity
	 */
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<BaseResponse> handleNoHandlerFoundException(NoHandlerFoundException exception) {
		log.error(exception.getMessage(), exception);
		return createErrorResponse(new GeneralException(ErrorConstant.API_NOT_FOUND));
	}

	/**
	 * Error handling when url api got unexpected internal server error
	 * 
	 * @author Steve Sentosa, 20 May 2024
	 * @param NoHandlerFoundException exception
	 * @return ResponseEntity
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<BaseResponse> handleInternalServerErrorException(Exception exception) {
		log.error(exception.getMessage(), exception);
		return createErrorResponse(new GeneralException(ErrorConstant.INTERNAL_SERVER_ERROR));
	}

	/**
	 * Error handling when url api got unexpected bad request
	 * 
	 * @author Steve Sentosa, 20 May 2024
	 * @param NoHandlerFoundException exception
	 * @return ResponseEntity
	 */
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<BaseResponse> handleBadRequestException(RuntimeException exception) {
		log.error(exception.getMessage(), exception);
		return createErrorResponse(new GeneralException(ErrorConstant.BAD_REQUEST));
	}

	/**
	 * Error handling when http request method is wrong or not supported
	 * 
	 * @author Steve Sentosa, 20 May 2024
	 * @param HttpRequestMethodNotSupportedException exception
	 * @return ResponseEntity
	 */
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<BaseResponse> handleHttpRequestMethodNotSupportedException(
			HttpRequestMethodNotSupportedException exception) {
		log.error(exception.getMessage(), exception);
		return createErrorResponse(new GeneralException(ErrorConstant.API_NOT_FOUND));
	}

	/**
	 * Error handling when failed to parse a json object or json array
	 * 
	 * @author Steve Sentosa, 20 May 2024
	 * @param JsonParseException exception
	 * @return ResponseEntity
	 */
	@ExceptionHandler(JsonParseException.class)
	public ResponseEntity<BaseResponse> handleJsonParseException(JsonParseException exception) {
		log.error(exception.getMessage(), exception);
		return createErrorResponse(new GeneralException(ErrorConstant.PARSING_ERROR));
	}

	/**
	 * Error handling
	 * 
	 * @author Steve Sentosa, 20 May 2024
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(UnrecognizedPropertyException.class)
	public ResponseEntity<BaseResponse> handleUnRecognizedPropertyException(UnrecognizedPropertyException exception) {
		GeneralException error = new GeneralException(ErrorConstant.INVALID_PARAMETER);
		log.error(error.getMessage(), error);

		error.getError().getField().clear();
		error.getError().setField(getFieldName(exception));

		return createErrorResponse(error);
	}

	/**
	 * Error handling for parsing format
	 * 
	 * @author Steve Sentosa, 20 May 2024
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(InvalidFormatException.class)
	public ResponseEntity<BaseResponse> handleInvalidFormatException(InvalidFormatException exception) {
		GeneralException error = new GeneralException(ErrorConstant.INVALID_DATA_ENTRY);
		log.error(error.getMessage(), error);

		error.getError().getField().clear();
		error.getError().setField(getFieldName(exception));

		return createErrorResponse(error);
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<BaseResponse> handleHttpNotFound() {
		return createErrorResponse(new GeneralException(ErrorConstant.API_NOT_FOUND));
	}

	/**
	 * Error handling for rest template
	 * 
	 * @author Steve Sentosa, 20 May 2024
	 * @param exception
	 * @param request
	 * @param response
	 * @param webRequest
	 * @return
	 */
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<BaseResponse> handleJsonParseException(HttpMessageNotReadableException exception,
			HttpServletRequest request, HttpServletResponse response, WebRequest webRequest) {
		Throwable cause = exception.getCause();

		if (cause instanceof MismatchedInputException mismatchInputException) {
			GeneralException error = new GeneralException(ErrorConstant.INVALID_DATA_ENTRY);
			log.error(error.getMessage(), error);
			error.getError().getField().clear();
			error.getError().setField(getFieldName(mismatchInputException));
			return createErrorResponse(error);
		}

		GeneralException error = new GeneralException(ErrorConstant.PARSING_ERROR);
		log.error(error.getMessage(), error);
		return createErrorResponse(error);
	}
}
