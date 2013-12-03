package org.alterq.dto;

import org.alterq.domain.Jornada;
import org.alterq.domain.UserAlterQ;

public class ResponseDto {
	private UserAlterQ userAlterQ;
	private ErrorDto errorDto;
	private Jornada jornada;

	public UserAlterQ getUserAlterQ() {
		return userAlterQ;
	}

	public void setUserAlterQ(UserAlterQ userAlterQ) {
		this.userAlterQ = userAlterQ;
	}

	public ErrorDto getErrorDto() {
		return errorDto;
	}

	public void setErrorDto(ErrorDto errorDto) {
		this.errorDto = errorDto;
	}

	public Jornada getJornada() {
		return jornada;
	}

	public void setJornada(Jornada jornada) {
		this.jornada = jornada;
	}

}
