package org.alterq.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class RoundResult {

	@Id
	private String id;

	private int season;
	private int round;
	private String resultBet;


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

	public String getResultBet() {
		return resultBet;
	}

	public void setResultBet(String resultBet) {
		this.resultBet = resultBet;
	}
}