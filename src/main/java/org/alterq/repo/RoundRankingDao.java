package org.alterq.repo;

import org.alterq.domain.Ranking;
import org.alterq.domain.RoundRanking;


public interface RoundRankingDao {
	public RoundRanking findRanking(int company, int season, int round);
	
	public RoundRanking findUserRanking(int company, int season, int round, String userID);
	
	public boolean addRanking(int company, int season, int round, Ranking ranking);
	
	public boolean updateRanking(int company, int season, int round, Ranking ranking);
	
	public void add(RoundRanking bean);
	
	public boolean deleteRanking(int company, int season, int round);
	
	public void update(RoundRanking bean);
}
