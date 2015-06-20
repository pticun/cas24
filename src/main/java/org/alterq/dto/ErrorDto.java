package org.alterq.dto;

import org.alterq.util.enumeration.MessageResourcesNameEnum;

public class ErrorDto {
	private String idError;
	private String stringError;
    
	public ErrorDto(String idError, String stringError) {
        this.idError = idError;
        this.stringError = stringError;
    }
	public ErrorDto() {
	}

	public String getIdError() {
		return idError;
	}
	public void setIdError(MessageResourcesNameEnum mrne) {
		this.idError = mrne.getValue();
	}
	public void setIdError(String idError) {
		this.idError = idError;
	}
	public String getStringError() {
		return stringError;
	}
	public void setStringError(String stringError) {
		this.stringError = stringError;
	}
	

}
