package org.alterq.domain;


import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Account {

	@Id
	private String id;
	
	private int company;
	private String user;
	private int season;
	private int round;
	private int type;
	private Date date;
	private String description;
	private String amount;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getCompany() {
		return company;
	}
	public void setCompany(int company) {
		this.company = company;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
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
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}


}