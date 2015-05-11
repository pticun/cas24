package org.alterq.domain;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Round {

	@Id
	private String id;

	private int company;
	private int season;
	private int round;
	private List<Game> games;
	private Date dateRound;

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

	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}

	public int getCompany() {
		return company;
	}

	public void setCompany(int company) {
		this.company = company;
	}

	public Date getDateRound() {
		return dateRound;
	}

	public void setDateRound(Date dateRound) {
		this.dateRound = dateRound;
	}
}