package org.alterq.repo;

import org.alterq.domain.RoundResult;


public interface RoundResultsDao {
	public RoundResult findResult(int season, int round);
	
	public boolean updateResult(int season, int round, String result);
	
	public void add(RoundResult bean);
	
	public boolean deleteResult(int season, int round);
	
	public void update(RoundResult bean);
}
