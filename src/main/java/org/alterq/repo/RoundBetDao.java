package org.alterq.repo;

import org.alterq.domain.Bet;
import org.alterq.domain.RoundBets;
import org.alterq.util.enumeration.BetTypeEnum;

public interface RoundBetDao {
	public RoundBets findAllBets(int season, int round);

	public RoundBets findAllBets(int season, int round, int company);

	public int countAllBets(int season, int round);

	public int countAllBets(int season, int round, int company);

	public RoundBets findAllUserBets(int season, int round, String user, int company);

	public RoundBets findAutomaticBet(int season, int round, int company);

	public RoundBets findNormalBet(int season, int round, int company);

	public RoundBets findFinalBet(int season, int round, int company);

	public RoundBets findResultBet(int season, int round, int company);

	public RoundBets findTypeBet(int season, int round, int company, BetTypeEnum betType);

	public boolean addBet(int company, int season, int round, Bet bet);

	public void add(RoundBets bean);

	public boolean deleteAllBets(int season, int round);

	public boolean deleteAllUserBets(int season, int round, String user);

	public boolean deleteUserBet(int season, int round, Bet bet);

	public void update(RoundBets rounBets);
}
