package com.springboot.h2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AlbumNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public AlbumNotFoundException(String exception) {
		    super(exception);
		  }
}
