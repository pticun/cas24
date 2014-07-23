package org.alterq.exception;

import java.util.ArrayList;
import java.util.List;

import org.alterq.dto.ErrorDto;

public class AlterQException extends Exception {
	private static final long serialVersionUID = 1L;
	protected List<ErrorDto> errorDto = new ArrayList<ErrorDto>();

	public AlterQException(String message) {
		super(message);
	}

	public List<ErrorDto> getErrorDto() {
		return errorDto;
	}

	public void addErrorDto(ErrorDto errorDto) {
		this.errorDto.add(errorDto);
	}

}
