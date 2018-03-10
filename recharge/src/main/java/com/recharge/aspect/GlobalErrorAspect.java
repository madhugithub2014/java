package com.recharge.aspect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalErrorAspect {

	/**
	 * When suitable HTTP Method is not supported
	 */
	@ExceptionHandler
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
		log.error("Exception thrown HttpRequestMethodNotSupportedException : ", ex);
	    StringBuilder builder = new StringBuilder();
	    builder.append(ex.getMethod());
	    builder.append(" method is not supported for this request. Supported methods are ");
	    ex.getSupportedHttpMethods().forEach(t -> builder.append(t + " "));
	 
	    ApiError apiError = new ApiError(HttpStatus.METHOD_NOT_ALLOWED, 
	      ex.getLocalizedMessage(), builder.toString());
	    return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	/**
	 * When required header is missing from the request
	 */
	@ExceptionHandler
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(ServletRequestBindingException ex) {
		log.error("Exception thrown ServletRequestBindingException : ", ex);
	    StringBuilder builder = new StringBuilder();
	    builder.append(ex.getMessage());
	    ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, 
	      ex.getLocalizedMessage(), builder.toString());
	    return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	/**
     * MissingServletRequestParameterException: This exception is thrown when request parameter is missing from request
	 */
	@ExceptionHandler
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex) {
		log.error("Exception thrown MissingServletRequestParameter : ", ex);
		String error = ex.getParameterName() + " parameter is missing";
	    ApiError apiError = 
	      new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
	    
	    return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}
	
	/**
	 * MethodArgumentTypeMismatchException: This exception is thrown when method argument is not the expected type:
	 */
	@ExceptionHandler
	public ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
		log.error("Exception thrown MethodArgumentTypeMismatchException : ", ex);
	    String error = ex.getName() + " should be of type " + ex.getRequiredType().getName();
	    ApiError apiError = 
	      new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
	    return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}
	
	/**
	 * MissingServletRequestPartException This exception is thrown when when the part of a multipart request not found
	 */
	@ExceptionHandler
	public ResponseEntity<Object> handleMissingServletRequestPartException(MissingServletRequestPartException ex) {
		log.error("Exception thrown MissingServletRequestPartException : ", ex);
	    String error = ex.getRequestPartName() +" is missing";
	    ApiError apiError = 
	      new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
	    return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	/**
	 * MethodArgumentNotValidException:This exception is thrown when argument annotated with @Valid failed validation:
	 */
	@ExceptionHandler
	public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		log.error("Exception thrown MethodArgumentNotValidException : ", ex);
	    String error = ex.getMessage();
	    ApiError apiError = 
	      new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
	    return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}
	
	
	/**
	 * HttpMediaTypeNotSupportedException is thrown when input media type is not accepted by server
	 */
	@ExceptionHandler
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex) {
		log.error("Exception thrown HttpMediaTypeNotSupportedException : ", ex);
	    StringBuilder builder = new StringBuilder();
	    builder.append(ex.getContentType());
	    builder.append(" media type is not supported. Supported media types are ");
	    ex.getSupportedMediaTypes().forEach(t -> builder.append(t + ", "));
	 
	    ApiError apiError = new ApiError(HttpStatus.UNSUPPORTED_MEDIA_TYPE, 
	      ex.getLocalizedMessage(), builder.substring(0, builder.length() - 2));
	    return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	/**
	 * HttpMediaTypeNotAcceptableException is thrown when client can not accept the media type service is returning
	 */
	@ExceptionHandler
	protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex) {
		log.error("Exception thrown HttpMediaTypeNotAcceptableException : ", ex);
	    StringBuilder builder = new StringBuilder();
	    builder.append(ex.getLocalizedMessage());
	    builder.append(" media type is not supported. Supported media types are ");
	    ex.getSupportedMediaTypes().forEach(t -> builder.append(t + ", "));
	 
	    ApiError apiError = new ApiError(HttpStatus.UNSUPPORTED_MEDIA_TYPE, 
	      ex.getLocalizedMessage(), builder.substring(0, builder.length() - 2));
	    return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	/**
	 * Exception is thrown when Path variable is missing
	 */
	@ExceptionHandler
	protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex) {
		log.error("Exception thrown MissingPathVariableException : ", ex);
	    StringBuilder builder = new StringBuilder();
	    builder.append(ex.getVariableName());
	    builder.append(" variable is missing in path");
	    ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, 
	      ex.getLocalizedMessage(), builder.substring(0, builder.length() - 2));
	    return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}
	
	/**
	 * This is fall back handler. We will catch the exception when no suitable handler is defined for our request
	 */
	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> handleAll(Exception ex) {
		log.error("Error occured ", ex);
		String errorMessage = ex.getMessage() == null ? ex.getClass().getName() : ex.getMessage();
	    ApiError apiError = new ApiError(
	      HttpStatus.BAD_REQUEST, "Error occurred", errorMessage);
	    return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}

}
