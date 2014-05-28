package org.alterq.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
 
@Document
public class RoundBets {
	
	@Id
	private String id;

	private int company;
	private int season;
	private int round;
	
	private float reward; 
	private float jackpot;
	private float price;
	
	/*private int hit15;
	private float reward15;
	private int hit14;
	private float reward14;
	private int hit13;
	private float reward13;
	private int hit12;
	private float reward12;
	private int hit11;
	private float reward11;
	private int hit10;
	private float reward10;*/
	private List<Prize> prizes;
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
		if(this.bets==null){
			this.bets=new ArrayList<Bet>();
		}
		return this.bets.add(bet);
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
	/*
	public int getHit15() {
		return hit15;
	}
	public void setHit15(int hit15) {
		this.hit15 = hit15;
	}
	public float getReward15() {
		return reward15;
	}
	public void setReward15(float reward15) {
		this.reward15 = reward15;
	}
	public int getHit14() {
		return hit14;
	}
	public void setHit14(int hit14) {
		this.hit14 = hit14;
	}
	public float getReward14() {
		return reward14;
	}
	public void setReward14(float reward14) {
		this.reward14 = reward14;
	}
	public int getHit13() {
		return hit13;
	}
	public void setHit13(int hit13) {
		this.hit13 = hit13;
	}
	public float getReward13() {
		return reward13;
	}
	public void setReward13(float reward13) {
		this.reward13 = reward13;
	}
	public int getHit12() {
		return hit12;
	}
	public void setHit12(int hit12) {
		this.hit12 = hit12;
	}
	public float getReward12() {
		return reward12;
	}
	public void setReward12(float reward12) {
		this.reward12 = reward12;
	}
	public int getHit11() {
		return hit11;
	}
	public void setHit11(int hit11) {
		this.hit11 = hit11;
	}
	public float getReward11() {
		return reward11;
	}
	public void setReward11(float reward11) {
		this.reward11 = reward11;
	}
	public int getHit10() {
		return hit10;
	}
	public void setHit10(int hit10) {
		this.hit10 = hit10;
	}
	public float getReward10() {
		return reward10;
	}
	public void setReward10(float reward10) {
		this.reward10 = reward10;
	}*/
	
}