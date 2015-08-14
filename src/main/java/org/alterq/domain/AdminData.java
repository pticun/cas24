package org.alterq.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class AdminData implements Serializable {
	@Id
	private String id;
	
	//TODO change name is not company included in AdminData is Company included in AdminData
	private int company;
	private int season;
	private int round;
	private float prizeBet;
	private String idDelegacion = "00";
	private String idReceptor = "00000";
	private boolean active;
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
	public int getSeason() {
		return season;
	}
	public void setSeason(int season) {
		this.season = season;
	}
	public int getRound() {
		return round;
	}
	public void setRound(int round) {
		this.round = round;
	}
	public int getCompany() {
		return company;
	}
	public void setCompany(int company) {
		this.company = company;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public float getPrizeBet() {
		return prizeBet;
	}
	public void setPrizeBet(float prizeBet) {
		this.prizeBet = prizeBet;
	}
	
}
