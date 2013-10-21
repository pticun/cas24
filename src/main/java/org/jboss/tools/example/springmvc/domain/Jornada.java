package org.jboss.tools.example.springmvc.domain;

import java.util.List;
//import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
 
@Document
public class Jornada {
 
    private List<Partido> partidos;
    
    public List<Partido> getJornada() {
		return partidos;
	}
	public void setEquipo1(List<Partido> partidos) {
		this.partidos = partidos;
	}
}