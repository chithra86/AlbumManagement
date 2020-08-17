package com.springboot.h2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BandNotFoundException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public BandNotFoundException(String exception) {
		    super(exception);
		  }

}
