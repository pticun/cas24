package org.alterq.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Ranking {

	@Id
	private String id;
	
	private String user;
	private String nick;
	private int points;
	private int ones;
	private int equs;
	private int twos;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public int getOnes() {
		return ones;
	}
	public void setOnes(int ones) {
		this.ones = ones;
	}
	public int getEqus() {
		return equs;
	}
	public void setEqus(int equs) {
		this.equs = equs;
	}
	public int getTwos() {
		return twos;
	}
	public void setTwos(int twos) {
		this.twos = twos;
	}
}