package org.alterq.domain;

import java.io.Serializable;
import java.math.BigInteger;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Company implements Serializable {
	@Id
	private String id;
	
	private int company;
	
	//Type
	//1=collaborative
	//2=non-collaborative
	private int type;
	private boolean visibility;
	private String nick;
	private String description;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public boolean isVisibility() {
		return visibility;
	}
	public void setVisibility(boolean visibility) {
		this.visibility = visibility;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
