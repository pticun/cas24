package org.alterq.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class UserAlterQ implements Serializable {
	@Id
	private String id;
	private String nick;
	private String name;
	private String surnames;
	private int typeID;
	private String idCard;
	private String pwd;
	private String phoneNumber;
	private String birthday;
	private String city;
	private String balance;
	private String accept;
	private boolean active;
	private Date dateCreated;
	private Date dateUpdated;
	private int automatics;
	private double weight;
	private List<RolCompany> rols;
	private List<Bet> specialBets;

	public List<Bet> getSpecialBets() {
		return specialBets;
	}

	public void setSpecialBets(List<Bet> specialBets) {
		this.specialBets = specialBets;
	}

	public List<RolCompany> getRols() {
		return rols;
	}

	public void setRols(List<RolCompany> rols) {
		this.rols = rols;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;

	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumer) {
		this.phoneNumber = phoneNumer;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public int getAutomatics() {
		return automatics;
	}

	public void setAutomatics(int automatics) {
		this.automatics = automatics;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	/*
	 * public boolean isAdmin() { return admin; }
	 * 
	 * public void setAdmin(boolean admin) { this.admin = admin; }
	 */

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getAccept() {
		return accept;
	}

	public void setAccept(String accept) {
		this.accept = accept;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getSurnames() {
		return surnames;
	}

	public void setSurnames(String surnames) {
		this.surnames = surnames;
	}

	public int getTypeID() {
		return typeID;
	}

	public void setTypeID(int typeID) {
		this.typeID = typeID;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
