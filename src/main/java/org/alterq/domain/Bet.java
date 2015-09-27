package org.alterq.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Bet {

	@Id
	private String id;
	private int company;
	/**
	 * type return element {org.alterq.util.enumeration.BetTypeEnum}
	 */
	private int type;

	private Date dateCreated;
	private Date dateUpdated;
	private String user;
	private String bet;
	private float price;
	private int numBets;
	private List<Prize> prizes;
	private String reduction;
	private int typeReduction;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getNumBets() {
		return numBets;
	}

	public void setNumBets(int numBets) {
		this.numBets = numBets;
	}
	public String getReduction() {
		return reduction;
	}

	public void setReduction(String reduction) {
		this.reduction = reduction;
	}

	public int getTypeReduction() {
		return typeReduction;
	}

	public void setTypeReduction(int typeReduction) {
		this.typeReduction = typeReduction;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getBet() {
		return bet;
	}

	public void setBet(String bet) {
		this.bet = bet;
	}

	public int getNumberHits(String rdo) {
		return 0;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

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
	public List<Prize> getPrizes() {
		return prizes;
	}
	public void setPrizes(List<Prize> prizes) {
		this.prizes = prizes;
	}
	public boolean addPrize(Prize prize) {
		if(this.prizes==null){
			this.prizes=new ArrayList<Prize>();
		}
		return this.prizes.add(prize);
	}
}