package org.alterq.repo;

import java.util.List;

import org.alterq.domain.Round;

public interface RoundDao {
	public Round findByTemporadaJornada(int temporada, int jornada);

	public Round findLastJornada();

	public List<Round> findAllOrderedByTemporada();

	public void addRound(Round jornada);
}
