package org.alterq.exception;

import org.alterq.dto.ErrorDto;

public class SecurityException  extends Exception {

	private static final long serialVersionUID = 1L;
	private ErrorDto errorDto;

	public SecurityException(String message){
		super(message);
	}
	public SecurityException(ErrorDto errorDto){
		this.errorDto = errorDto;
	}

	public ErrorDto getError() {
		return errorDto;
	}

	public void setError(ErrorDto errorDto) {
		this.errorDto = errorDto;
	}
	
}
