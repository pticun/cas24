package org.alterq.repo;

import java.util.List;

import org.alterq.domain.Jornada;

public interface JornadaDao {
	public Jornada findByTemporadaJornada(int temporada, int jornada);

	public Jornada findLastJornada();

	public List<Jornada> findAllOrderedByTemporada();

	public void addJornada(Jornada jornada);
}
