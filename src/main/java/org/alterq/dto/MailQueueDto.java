package org.alterq.dto;

import java.io.Serializable;

import org.alterq.domain.Company;
import org.alterq.domain.RoundBets;
import org.alterq.domain.UserAlterQ;
import org.alterq.util.enumeration.QueueMailEnum;

public class MailQueueDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4219768056824985763L;
	
	public UserAlterQ user;
	private Company company;
	public String contactName;
	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getContactMenssage() {
		return contactMenssage;
	}

	public void setContactMenssage(String contactMenssage) {
		this.contactMenssage = contactMenssage;
	}

	public String contactEmail;
	public String contactMenssage;

	
	public String getCco() {
		return cco;
	}

	public void setCco(String cco) {
		this.cco = cco;
	}

	public String cco;
	
	public QueueMailEnum type;
	
	public RoundBets roundBet;

	public RoundBets getRoundBet() {
		return roundBet;
	}

	public void setRoundBet(RoundBets roundBet) {
		this.roundBet = roundBet;
	}

	public UserAlterQ getUser() {
		return user;
	}

	public void setUser(UserAlterQ user) {
		this.user = user;
	}

	public QueueMailEnum getType() {
		return type;
	}

	public void setType(QueueMailEnum type) {
		this.type = type;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
	

}
