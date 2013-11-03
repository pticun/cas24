package org.alterq.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
 
@Document
public class SessionAlterQ {
 
    @Id
    private String id;
    private String userAlterQ;
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
}