package org.alterq.domain;

import org.springframework.data.mongodb.core.mapping.Document;
 
@Document
public class Bet {
 
    private String user;
    private String bet;
	private float price;
     
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
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}	
}