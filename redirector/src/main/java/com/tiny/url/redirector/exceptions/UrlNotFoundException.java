package com.tiny.url.redirector.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UrlNotFoundException extends RuntimeException {
	
	public UrlNotFoundException(String message) {
		super(message);
	}
}
