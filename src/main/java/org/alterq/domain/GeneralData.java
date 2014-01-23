package org.alterq.domain;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class GeneralData implements Serializable {
	private int season;
	private int round;
	private boolean active;
	private int dobles;
	private int triples;

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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public int getDobles() {
		return dobles;
	}

	public void setDobles(int dobles) {
		this.dobles = dobles;
	}

	public int getTriples() {
		return triples;
	}

	public void setTriples(int triples) {
		this.triples = triples;
	}

}
