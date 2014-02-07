package org.alterq.repo;

import java.util.List;

import org.alterq.domain.Round;

public interface RoundDao {
	public Round findBySeasonRound(int season, int round);

	public Round findLastRound();

	public List<Round> findAllOrderedBySeason();

	public void addRound(Round jornada);
}
