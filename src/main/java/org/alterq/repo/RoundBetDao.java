package org.alterq.repo;

import org.alterq.domain.Bet;
import org.alterq.domain.RoundBets;


public interface RoundBetDao {
	public RoundBets findAllBets(int company, int season, int round);
	public int countAllBets(int company, int season, int round);
	public RoundBets findAllUserBets(int company, int season, int round, String user);
	
	public boolean addBet(int company, int season, int round, Bet bet);
	public void add(RoundBets bean);
	
	public boolean deleteAllBets(int company, int season, int round);
	public boolean deleteAllUserBets(int company, int season, int round, String user);
	public boolean deleteUserBet(int company, int season, int round, Bet bet);
}
