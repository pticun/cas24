package org.arch.core.file;

import java.util.Iterator;
import java.util.Set;

import org.alterq.util.CalculateRigths;
import org.apache.commons.collections.map.MultiValueMap;
import org.apache.commons.lang3.StringUtils;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.MethodSorters;

@RunWith(BlockJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BetElectronicFileTest {

	@Test
	public void test01BetElectronicFile() {
		
		HeaderBetElectronicFile cb = new HeaderBetElectronicFile();
		cb.setIdDelegacion("64");
		cb.setIdReceptor("65428");

		String rdo[]; 
		CalculateRigths aux = new CalculateRigths();
		rdo = aux.Despliegue("7777111111111100", "TNNNNNNNNNNNNN", 1);
		rdo = aux.Despliegue("77771111111111ff", "TTTTNNNNNNNNNNNN", 1);

		cb.setFechaJornada("010115");
		cb.setNumTotalApuestas(StringUtils.leftPad(""+rdo.length, 6, '0'));
		cb.setNumTotalBloques(StringUtils.leftPad(""+rdo.length, 6, '0'));
		BetElectronicFile befile=new BetElectronicFile();
		befile.setCabecera(cb);

		RegistroBetElectronicFile[] registro=new RegistroBetElectronicFile[rdo.length];

		System.out.println("numBloques="+calculoNumBloques(rdo.length));
		System.out.println("numApuestas="+rdo.length);
		
		MultiValueMap mhm=ordenarApuestas(rdo);
		Set<String> keys = mhm.keySet();
		int numBloquesPleno15=keys.size();
		System.out.println("numBloquesPleno15="+numBloquesPleno15);
		int indexBloques=1;
		int numBloques=1;
		for (Object k : keys) {  
		    System.out.println("("+k+" : "+mhm.get(k)+")");  
		    int numApuestasIgualPleno15=mhm.getCollection(k).size();
		    System.out.println("numApuestasIgualPleno15="+numApuestasIgualPleno15);
		    System.out.println("numBloques="+calculoNumBloques(numApuestasIgualPleno15));
		    int indexIterator=0;
		    int numApuestaLastBloque=1;
		    numBloques=numApuestasIgualPleno15/8+1;
		    boolean lastBloque=false;
		    StringBuffer pronosticoPartido=new StringBuffer();
		    for (Iterator iterator = mhm.getCollection(k).iterator(); iterator.hasNext();) {
				String linea = (String) iterator.next();
				indexIterator++;
				pronosticoPartido.append(StringUtils.left(linea, 14));
				//esto es un nuevo bloque
				if (indexIterator%8==0 && !lastBloque){
					RegistroBetElectronicFile registroBe=new RegistroBetElectronicFile();
					registroBe.setNumApuestaBloque("8");
					registroBe.setNumBloque(StringUtils.leftPad(""+indexBloques, 8, '0') );
					registroBe.setPronostico15(StringUtils.right(""+k, 2));
					registroBe.setPronosticoPartido(StringUtils.rightPad(pronosticoPartido.toString(), 112,' '));
					registro[indexBloques]=registroBe;
					
					pronosticoPartido=new StringBuffer();
					indexBloques++;
				}
				if (lastBloque){
					numApuestaLastBloque++;
				}
				if(indexBloques==numBloques){
					lastBloque=true;
				}
				System.out.println(linea);
			}
			RegistroBetElectronicFile registroBe=new RegistroBetElectronicFile();
			registroBe.setNumApuestaBloque(""+numApuestaLastBloque);
			registroBe.setNumBloque(StringUtils.leftPad(""+indexBloques, 8, '0') );
			registroBe.setPronostico15(StringUtils.right(""+k, 2));
			registroBe.setPronosticoPartido(StringUtils.rightPad(pronosticoPartido.toString(), 112,' '));
			registro[indexBloques]=registroBe;
			indexBloques++;
		}  	
		befile.setRegistro(registro);

		/*
		for (int i = 0; i < rdo.length; i++) {
			String linea = rdo[i];
			RegistroBetElectronicFile registroBe=new RegistroBetElectronicFile();
			registroBe.setNumApuestaBloque("1");
			registroBe.setNumBloque(StringUtils.leftPad(""+i, 6, '0') );
			registroBe.setPronostico15(StringUtils.right(linea, 2));
			registroBe.setPronosticoPartido(StringUtils.right(linea, 14));
			registro[i]=registroBe;
//			System.out.println(linea);
		}
		*/
		
		
		
		System.out.println(befile.getCabeceraString());
		System.out.println(befile.getRegistroString());

	}
	public MultiValueMap ordenarApuestas(String rdo[]){
		MultiValueMap mhm = new MultiValueMap();
		
		for (int i = 0; i < rdo.length; i++) {
			String linea = rdo[i];
			mhm.put(StringUtils.right(linea, 2), StringUtils.left(linea, 14));
		}
		System.out.println("results: "+mhm);
		return mhm;
	}
	
	public int calculoNumBloques(int numApuestas){
		int numBloques=0;
		
		int moduloNumBloques=numApuestas%8;
		numBloques=numApuestas/8;
		if(moduloNumBloques==1){
			numBloques++;
		}
		
		return numBloques;
	}

	
	public void test10BetPleno15ElectronicFile() {
		
		HeaderBetElectronicFile cb = new HeaderBetElectronicFile();
		cb.setIdDelegacion("64");
		cb.setIdReceptor("65428");

		String rdo[]; 
		CalculateRigths aux = new CalculateRigths();
		rdo = aux.Despliegue("77771111111111ff", "TTTTNNNNNNNNNNNN", 1);

		cb.setFechaJornada("010115");
		cb.setNumTotalApuestas(StringUtils.leftPad(""+rdo.length, 6, '0'));
		cb.setNumTotalBloques(StringUtils.leftPad(""+rdo.length, 6, '0'));
		BetElectronicFile befile=new BetElectronicFile();
		befile.setCabecera(cb);

		RegistroBetElectronicFile[] registro=new RegistroBetElectronicFile[rdo.length];

		for (int i = 0; i < rdo.length; i++) {
			String linea = rdo[i];
			RegistroBetElectronicFile registroBe=new RegistroBetElectronicFile();
			registroBe.setNumApuestaBloque("1");
			registroBe.setNumBloque(StringUtils.leftPad(""+i, 6, '0') );
			registroBe.setPronostico15(StringUtils.right(linea, 2));
			registroBe.setPronosticoPartido(StringUtils.right(linea, 14));
			registro[i]=registroBe;
			System.out.println(linea);
		}
		
		befile.setRegistro(registro);
		
		
		System.out.println(befile.getCabeceraString());
		System.out.println(befile.getRegistroString());

	}

}
