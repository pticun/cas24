package org.jboss.tools.example.springmvc.repo;

import java.util.List;

import org.jboss.tools.example.springmvc.domain.Jornada;

public interface JornadaDao
{
    public Jornada findByTemporadaJornada(int temporada, int jornada);

    public List<Jornada> findAllOrderedByTemporada();

    public void addJornada(Jornada jornada);
}
