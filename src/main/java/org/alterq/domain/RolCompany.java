package org.alterq.domain;

import java.io.Serializable;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class RolCompany implements Serializable{

	private int company;
	private int rol;
	public int getCompany() {
		return company;
	}
	public void setCompany(int company) {
		this.company = company;
	}
	public int getRol() {
		return rol;
	}
	public void setRol(int rol) {
		this.rol = rol;
	}
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
        .append(company)
        .append(rol)
        .toHashCode();	
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RolCompany other = (RolCompany) obj;
		if (company != other.company)
			return false;
		if (rol != other.rol)
			return false;
		return true;
	}
	
}
