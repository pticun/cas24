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
    public void setId(int pos) {
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
	
	public String obtenerCadenaPartido()
    {
    	int lEq1 = equipo1.length();
    	int lEq2 = equipo2.length();
    	String rdo = "";
    	
    	if (lEq1>10)
    	{
    		if ((lEq1+lEq2)<=20)
    			rdo += equipo1;
    		else
    		{
	    		lEq1 = 10;
	    		rdo += equipo1.substring(0, 10);
    		}
    	}
    	else{
    		rdo += equipo1;
    	}
    	
    	rdo += "-";
    	
    	if (lEq2>10)
    	{
    		if ((lEq1+lEq2)<=20)
    			rdo += equipo2;
    		else
    		{
        		lEq2 = 10;
        		rdo += equipo2.substring(0, 10);
    		}
    	}
    	else
    		rdo += equipo2;

    	for (int i=lEq1+lEq2; i<22; i++)
    		rdo +=".";
    	
    	if (pos>9)
    		rdo+=Integer.toString(pos);
    	else
    		rdo+=" " + Integer.toString(pos);
    	
		return rdo;
    }	
}