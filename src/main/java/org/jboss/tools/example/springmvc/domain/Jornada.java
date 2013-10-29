package org.jboss.tools.example.springmvc.domain;

import java.util.List;
//import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
 
@Document
public class Jornada {
	
	private int temporada;
	private int jornada;
    private List<Partido> partidos;
    
    public int getTemporada() {
		return temporada;
	}
	public void setTemporada(int temporada) {
		this.temporada = temporada;
	}
    public int getJornada() {
		return jornada;
	}
	public void setJornada(int jornada) {
		this.jornada = jornada;
	}
    public List<Partido> getPartidos() {
		return partidos;
	}
	public void setPartidos(List<Partido> partidos) {
		this.partidos = partidos;
	}
	public String toString()
	{
		
		String rdo = "{temporada:" + temporada + ",jornada:" + jornada + ",partidos:[";
				for(int i=0; i< partidos.size(); i++) {
					if(i!=0)
						rdo+=",";
					rdo+="{pos:" + ((Partido)partidos.get(i)).getPos();
					rdo+=", equipo1:" + ((Partido)partidos.get(i)).getEquipo1();
		            rdo+=", equipo2:"+((Partido)partidos.get(i)).getEquipo2() + "}";
		        }
				rdo+="]}";
		return rdo;
	}
}