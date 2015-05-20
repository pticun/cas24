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

/**
 * @author osruiz
 *
 */
@RunWith(BlockJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BetElectronicFileTest {
	
	@Test
	public void test00() {
		int[] modulo=modulusBloque(0);
		System.out.println("0="+modulo[0]+"-"+modulo[1]);
		modulo=modulusBloque(64);
		System.out.println("64="+modulo[0]+"-"+modulo[1]);
		modulo=modulusBloque(9);
		System.out.println("9="+modulo[0]+"-"+modulo[1]);
		modulo=modulusBloque(81);
		System.out.println("81="+modulo[0]+"-"+modulo[1]);
		modulo=modulusBloque(5);
		System.out.println("5="+modulo[0]+"-"+modulo[1]);
		modulo=modulusBloque(77);
		System.out.println("77="+modulo[0]+"-"+modulo[1]);
		
		
	}


	@Test
	public void test01BetElectronicFile() {
		
		HeaderBetElectronicFile cb = new HeaderBetElectronicFile();
		cb.setIdDelegacion("64");
		cb.setIdReceptor("65428");

		String rdo[] = new String[0];
		String rdoAux[];

		CalculateRigths aux = new CalculateRigths();
		//Directa (1 apuesta)
		rdoAux = aux.unfolding("1111111111111100", "NNNNNNNNNNNNNN", 0);
		rdo = aux.acumula(rdo, rdoAux);
		//Directa - 1 Triple (3 apuestas)
		rdoAux = aux.unfolding("7111111111111100", "NNNNNNNNNNNNNN", 0);
		rdo = aux.acumula(rdo, rdoAux);
		//Directa - 1 Doble (2 apuestas)
		rdoAux = aux.unfolding("5111111111111100", "NNNNNNNNNNNNNN", 0);
		rdo = aux.acumula(rdo, rdoAux);
		//Reduccion 1 - 4 triples (9 apuestas)
		rdoAux = aux.unfolding("7777111111111100", "TTTTNNNNNNNNNN", 1);
		rdo = aux.acumula(rdo, rdoAux);
		//Reduccion 2 - 7 dobles (16 apuestas)
		rdoAux = aux.unfolding("5555555111111101", "DDDDDDDNNNNNNN", 2);
		rdo = aux.acumula(rdo, rdoAux);
		//Reduccion 3 - 3 Triples y 3 Dobles (24 apuestas)
		rdoAux = aux.unfolding("7775551111111102", "TTTDDDNNNNNNNN", 3);
		rdo = aux.acumula(rdo, rdoAux);
		//Reduccion 4 - 2 Triples y 6 Dobles (64 apuestas)
		rdoAux = aux.unfolding("775555551111110M", "TTDDDDDDNNNNNN", 4);
		rdo = aux.acumula(rdo, rdoAux);
		//Reduccion 5 - 8 Triples (81 apuestas)
		rdoAux = aux.unfolding("7777777711111110", "TTTTTTTTNNNNNN", 5);
		rdo = aux.acumula(rdo, rdoAux);
		//Reduccion 6 - 11 Dobles (132 apuestas)
		rdoAux = aux.unfolding("5555555555511120", "DDDDDDDDDDDNNN", 6);
		rdo = aux.acumula(rdo, rdoAux);

//		rdo = new String[0];
		//Reduccion 5 - 8 Triples (81 apuestas)
		rdoAux = aux.unfolding("7777777711111110", "TTTTTTTTNNNNNN", 5);
		rdo = aux.acumula(rdo, rdoAux);
		BetElectronicFile befile=new BetElectronicFile();
		befile.setCabecera(cb);

		RegistroBetElectronicFile[] registro=new RegistroBetElectronicFile[rdo.length];

//		System.out.println("numBloques="+calculoNumBloques(rdo.length));
		System.out.println("numApuestas="+rdo.length);
		
		MultiValueMap mhm=ordenarApuestas(rdo);
		Set<String> keys = mhm.keySet();
		int numBloquesPleno15=keys.size();
		System.out.println("numBloquesPleno15="+numBloquesPleno15);
		int indexBloquesTotal=1;
		for (Object k : keys) {  
		    System.out.println("("+k+" : "+mhm.get(k)+")");  
		    int numApuestasIgualPleno15=mhm.getCollection(k).size();
		    int[] modulusBloque=modulusBloque(numApuestasIgualPleno15);
			int numBloques=modulusBloque[0];
			int indexIterator=0;
			int numApuestaLastBloque=0;
			int numBloquesContador=1;
		    System.out.println("numBloque="+modulusBloque[0]);
		    System.out.println("modBloque="+modulusBloque[1]);
		    System.out.println("numApuestasIgualPleno15="+numApuestasIgualPleno15);
		    boolean lastBloque=false;
		    StringBuffer pronosticoPartido=new StringBuffer();
		    for (Iterator iterator = mhm.getCollection(k).iterator(); iterator.hasNext();) {
		    	if(numBloquesContador==numBloques){
		    		lastBloque=true;
		    		numApuestaLastBloque++;
		    	}
				String linea = (String) iterator.next();
				indexIterator++;
				pronosticoPartido.append(StringUtils.left(linea, 14));
				//esto es un nuevo bloque
				if(indexIterator%modulusBloque[1]==0 && !lastBloque){
					RegistroBetElectronicFile registroBe=new RegistroBetElectronicFile();
					registroBe.setNumApuestaBloque(""+modulusBloque[1]);
					registroBe.setNumBloque(StringUtils.leftPad(""+indexBloquesTotal, 8, '0') );
					registroBe.setPronostico15(StringUtils.right(""+k, 2));
					registroBe.setPronosticoPartido(StringUtils.rightPad(pronosticoPartido.toString(), 112,' '));
					registro[indexBloquesTotal]=registroBe;
					
					pronosticoPartido=new StringBuffer();
					numBloquesContador++;
					indexBloquesTotal++;
				}
//				System.out.println(linea);
			}
			RegistroBetElectronicFile registroBe=new RegistroBetElectronicFile();
			registroBe.setNumApuestaBloque(""+numApuestaLastBloque);
			registroBe.setNumBloque(StringUtils.leftPad(""+indexBloquesTotal, 8, '0') );
			registroBe.setPronostico15(StringUtils.right(""+k, 2));
			registroBe.setPronosticoPartido(StringUtils.rightPad(pronosticoPartido.toString(), 112,' '));
			registro[indexBloquesTotal]=registroBe;
			indexBloquesTotal++;
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
		
		cb.setFechaJornada("010115");
		cb.setNumTotalApuestas(StringUtils.leftPad(""+rdo.length, 6, '0'));
		cb.setNumTotalBloques(StringUtils.leftPad(""+(indexBloquesTotal-1), 6, '0'));
		
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
	
	public int calculoNumBloques(int numApuestas,int modulo){
		int numBloques=0;
		
		int moduloNumBloques=numApuestas%modulo;
		numBloques=numApuestas/modulo;
		if(moduloNumBloques>0){
			numBloques++;
		}
		
		return numBloques;
	}
	/**
	 * @param numBets
	 * @return [numBlock,modBlock]
	 */
	public int[] modulusBloque(int numBets){
		int[] modulusBloque={0,0};
		int numBlock=0;
		int modBlock=0;
		int i=8;
		
		if(numBets==0){
			return modulusBloque;
		}
		
		for (; i > 0; i--) {
			int resto=numBets%i;
			modBlock=i;
			if(resto!=1){
				break;
			}
		}
		
		int resto=numBets%i;
		numBlock=numBets/i;
		if (resto!=0){
			numBlock++;
		}

		modulusBloque[0]=numBlock;
		modulusBloque[1]=modBlock;
		
		return modulusBloque;
	}

	
	public void test10BetPleno15ElectronicFile() {
		
		HeaderBetElectronicFile cb = new HeaderBetElectronicFile();
		cb.setIdDelegacion("64");
		cb.setIdReceptor("65428");

		String rdo[]; 
		CalculateRigths aux = new CalculateRigths();
		rdo = aux.unfolding("77771111111111ff", "TTTTNNNNNNNNNNNN", 1);

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
