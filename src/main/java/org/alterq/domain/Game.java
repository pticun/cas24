package org.alterq.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Game {

	private int pos;
	private String player1;
	private String player2;

	public int getPos() {
		return pos;
	}

	public void setId(int pos) {
		this.pos = pos;
	}

	public String getPlayer1() {
		return player1;
	}

	public void setPlayer1(String player1) {
		this.player1 = player1;
	}

	public String getPlayer2() {
		return player2;
	}

	public void setPlayer2(String player2) {
		this.player2 = player2;
	}

	public String obtenerCadenaPartido() {
		int lEq1 = player1.length();
		int lEq2 = player2.length();
		String rdo = "";

		if (lEq1 > 10) {
			if ((lEq1 + lEq2) <= 20)
				rdo += player1;
			else {
				lEq1 = 10;
				rdo += player1.substring(0, 10);
			}
		} else {
			rdo += player1;
		}

		rdo += "-";

		if (lEq2 > 10) {
			if ((lEq1 + lEq2) <= 20)
				rdo += player2;
			else {
				lEq2 = 10;
				rdo += player2.substring(0, 10);
			}
		} else
			rdo += player2;

		for (int i = lEq1 + lEq2; i < 22; i++)
			rdo += ".";

		if (pos > 9)
			rdo += Integer.toString(pos);
		else
			rdo += " " + Integer.toString(pos);

		return rdo;
	}
}