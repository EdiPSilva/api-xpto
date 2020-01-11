package com.xpto.api.exceptions;

import org.springframework.http.HttpStatus;

public class DefaultException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private HttpStatus status;

	public DefaultException(String message, HttpStatus status) {
		super(message);
		this.status = status;
	}

	public DefaultException(String message, Throwable caused, HttpStatus status) {
		super(message, caused);
		this.status = status;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}
}