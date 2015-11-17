package org.alterq.dto;

import java.io.Serializable;

import org.alterq.domain.UserAlterQ;
import org.alterq.util.enumeration.QueueMailEnum;

public class MailQueueDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4219768056824985763L;
	
	public UserAlterQ user;
	
	public QueueMailEnum type;

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
	
	

}
