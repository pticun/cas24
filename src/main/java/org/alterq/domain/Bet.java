package org.alterq.domain;

import org.springframework.data.mongodb.core.mapping.Document;
 
@Document
public class Bet {
 
    private String user;
    private String bet;
     
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
	
	public int getNumberHits(String rdo)
    {
		return 0;
    }	
}