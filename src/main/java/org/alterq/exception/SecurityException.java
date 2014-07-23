package org.alterq.exception;

import org.alterq.dto.ErrorDto;

public class SecurityException extends AlterQException {

	private static final long serialVersionUID = 1L;
	private ErrorDto errorDto;
	public SecurityException(ErrorDto errorDto){
		super("SecurityException");
		this.errorDto = errorDto;
	}

	public SecurityException(String message){
		super(message);
	}

	public ErrorDto getError() {
		return errorDto;
	}

	public void setError(ErrorDto errorDto) {
		this.errorDto = errorDto;
	}
	
}
