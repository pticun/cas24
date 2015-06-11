package org.alterq.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class AdminData implements Serializable {
	@Id
	private String id;
	
	private String idDelegacion = "00";
	private String idReceptor = "00000";
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIdDelegacion() {
		return idDelegacion;
	}
	public void setIdDelegacion(String idDelegacion) {
		this.idDelegacion = idDelegacion;
	}
	public String getIdReceptor() {
		return idReceptor;
	}
	public void setIdReceptor(String idReceptor) {
		this.idReceptor = idReceptor;
	}
	
}
