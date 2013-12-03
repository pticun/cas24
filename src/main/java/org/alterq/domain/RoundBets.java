package org.alterq.domain;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
 
@Document
public class RoundBets {
	
	private int season;
	private int round;
    private List<Bet> bets;
    
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
    public List<Bet> getBets() {
		return bets;
	}
	public void setBets(List<Bet> bets) {
		this.bets = bets;
	}
	public boolean addBet(Bet bet) {
		return this.bets.add(bet);
	}
	
}