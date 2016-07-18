package javacommon.web;

import org.springframework.http.HttpStatus;

import javacommon.web.ErrorCode;

public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = -8634700792767837033L;

	public ErrorCode errorCode;
	public HttpStatus status;
	
	public ServiceException() {
		super();
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}
	public ServiceException(String message, ErrorCode errorCode) {
		super(message);
		this.errorCode = errorCode;
	}
	public ServiceException(HttpStatus status, String message) {
		super(message);
		this.status = status;
	}
}
