package org.alterq.exception;

import org.alterq.dto.ErrorDto;

public class ValidatorException extends AlterQException {

	private static final long serialVersionUID = 1L;

	private ErrorDto errorDto;

	public ValidatorException(ErrorDto errorDto) {
		super("ValidatorException");
		this.errorDto = errorDto;
	}

	public ValidatorException(String message) {
		super(message);
	}

	public ErrorDto getError() {
		return errorDto;
	}

	public void setError(ErrorDto errorDto) {
		this.errorDto = errorDto;
	}

}
