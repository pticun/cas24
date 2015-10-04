package org.alterq.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class RoundBets {

	@Id
	private String id;

	private Date dateRound;

	private int company;
	private int season;
	private int round;

	private float reward;
	private float jackpot;
	private float price;

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
		if (this.bets == null) {
			this.bets = new ArrayList<Bet>();
		}
		return this.bets.add(bet);
	}

	public int numBets() {
		return bets.size();
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

	public float getReward() {
		return reward;
	}

	public void setReward(float reward) {
		this.reward = reward;
	}

	public float getJackpot() {
		return jackpot;
	}

	public void setJackpot(float jackpot) {
		this.jackpot = jackpot;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Date getDateRound() {
		return dateRound;
	}

	public void setDateRound(Date dateRound) {
		this.dateRound = dateRound;
	}

}