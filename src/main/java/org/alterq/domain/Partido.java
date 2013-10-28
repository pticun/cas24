package org.alterq.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
 
@Document
public class Partido {
 
    private int pos;
    private String equipo1;
    private String equipo2;
     
    public int getPos() {
        return pos;
    }
    public void setId(int id) {
        this.pos = pos;
    }
    public String getEquipo1() {
		return equipo1;
	}
	public void setEquipo1(String equipo1) {
		this.equipo1 = equipo1;
	}
    public String getEquipo2() {
		return equipo2;
	}
	public void setEquipo2(String equipo2) {
		this.equipo2 = equipo2;
	}
}