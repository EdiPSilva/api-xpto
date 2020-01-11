package com.xpto.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class CitiesExceptions extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public CitiesExceptions(String message) {
		super(message);
	}
}