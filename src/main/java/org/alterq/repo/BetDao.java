package org.alterq.repo;

import java.util.List;

import org.alterq.domain.Bet;
import org.alterq.domain.RoundBets;


public interface BetDao {
	public RoundBets findAllBets(int season, int round);
	
	public boolean addBet(int season, int round, Bet bet);
}
