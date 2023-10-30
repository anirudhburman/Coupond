package com.coupond.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class ResourceAlreadyExistsException extends RuntimeException {

	public ResourceAlreadyExistsException(String message) {
		super(message);
	}
}
