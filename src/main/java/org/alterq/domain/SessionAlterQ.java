package org.alterq.domain;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
 
@Document
public class SessionAlterQ {
 
    @Id
    private String id;
    private String userAlterQ;
	private Date dateUpdated;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserAlterQ() {
		return userAlterQ;
	}
	public void setUserAlterQ(String userAlterQ) {
		this.userAlterQ = userAlterQ;
	}
	public Date getDateUpdated() {
		return dateUpdated;
	}
	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}
}