package org.alterq.util;

import java.lang.Math;

import org.alterq.domain.AdminData;
import org.alterq.domain.UserAlterQ;
import org.alterq.dto.AlterQConstants;
import org.alterq.repo.AdminDataDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BetTools{
	@Autowired
	private AdminDataDao adminDataDao;

	public int getQuinielaNumBets(int type) {
		switch (type) {
		case 0:
			return 1; // Sencilla
		case 1:
			return 9; // Reduccion Primera (4T)
		case 2:
			return 16; // Reduccion Segunda (7D)
		case 3:
			return 24; // Reduccion Tercera (3D + 3T)
		case 4:
			return 64; // Reduccion Cuarta (6D + 2T)
		case 5:
			return 81; // Reduccion Quinta (8T)
		default:
			return 1;
		}
	}	
	
	public float calcQuinielaPrice(int doubles, int triples, int type) {
		AdminData ad = adminDataDao.findById(AlterQConstants.DEFECT_COMPANY);
		float priceBet = ad.getPrizeBet();

		return new Double(getQuinielaNumBets(type) * priceBet * Math.pow(2, doubles) * Math.pow(3, triples)).floatValue();
	}


	public float getPriceBet(){
		return adminDataDao.findById(AlterQConstants.DEFECT_ADMINDATA).getPrizeBet();
	}
	
	public int getNumberBets(int typeReduction, int dobles, int triples, int pleno1, int pleno2){
		Double numBets = Math.pow(2, dobles) * Math.pow(3, triples)*pleno1*pleno2;
		switch (typeReduction){
			case 0: numBets *= 1;	break;//Quiniela Directa sin Reduccion
			case 1: numBets *= 9;	break;//Reduccion 1 (4T: 9 apuestas)
			case 2: numBets *= 16;	break;//Reduccion 2 (7D: 16 apuestas)
			case 3: numBets *= 24;	break;//Reduccion 3 (3T + 3D: 24 apuestas)
			case 4: numBets *= 64;	break;//Reduccion 4 (2T + 6D: 64 apuestas)
			case 5: numBets *= 81;	break;//Reduccion 5 (8T: 81 apuestas)
			case 6: numBets *= 132;	break;//Reduccion 6 (11D: 132 apuestas)
			default: numBets = new Double(0d);	break;
		}
			
		return numBets.intValue();
	}

	public int getReductionType(int doblesRed, int triplesRed){
		int rdo = -1;
		
		if ((doblesRed == 0) && (triplesRed == 0))//Quiniela Directa sin Reduccion
			rdo = 0;
		else if ((doblesRed == 0) && (triplesRed == 4))//Reduccion 1 (4T: 9 apuestas)
			rdo = 1;
		else if ((doblesRed == 7) && (triplesRed == 0))//Reduccion 2 (7D: 16 apuestas)
			rdo = 2;
		else if ((doblesRed == 3) && (triplesRed == 3))//Reduccion 3 (3T + 3D: 24 apuestas)
			rdo = 3;
		else if ((doblesRed == 2) && (triplesRed == 6))//Reduccion 4 (2T + 6D: 64 apuestas)
			rdo = 4;
		else if ((doblesRed == 0) && (triplesRed == 8))//Reduccion 5 (8T: 81 apuestas)
			rdo = 5;
		else if ((doblesRed == 11) && (triplesRed == 0))//Reduccion 6 (11D: 132 apuestas)
			rdo = 6;
				
		return rdo;
	}	
	
	/**
	 * Function translateResult1x2 that translates 1X2 signs to 421
	 * 
	 * Format BET: S01S02S03S04S05S06S07S08S09S10S11S12S13S14P01P02
	 * 
	 * ResultBet Signs (Sxx): 1 = 100 = 4 X = 010 = 2 2 = 001 = 1
	 * 
	 * pleno 15 (Pxx) 0 = 1 1 = 2 2 = 4 M = 8
	 **/
	public String translateResult1x2(String apu) {
		String rdo = "";

		for (int i = 0; i < apu.length(); i++) {
			if (i < 14) {
				if (apu.substring(i, i + 1).startsWith("1")) {
					rdo += "4";
				} else if (apu.substring(i, i + 1).startsWith("X")) {
					rdo += "2";
				} else if (apu.substring(i, i + 1).startsWith("2")) {
					rdo += "1";
				} else {
					rdo = null;
					break;
				}
			} else {
				if (apu.substring(i, i + 1).startsWith("0")) {
					rdo += "1";
				} else if (apu.substring(i, i + 1).startsWith("1")) {
					rdo += "2";
				} else if (apu.substring(i, i + 1).startsWith("2")) {
					rdo += "4";
				} else if (apu.substring(i, i + 1).startsWith("M")) {
					rdo += "8";
				} else {
					rdo = null;
					break;
				}
			}
		}

		return rdo;
	}
	public String translateResultTo1x2(String apu) {
		String rdo = "";

		for (int i = 0; i < apu.length(); i++) {
			if (i < 14) {
				if (apu.substring(i, i + 1).startsWith("4")) {
					rdo += "1";
				} else if (apu.substring(i, i + 1).startsWith("2")) {
					rdo += "X";
				} else if (apu.substring(i, i + 1).startsWith("1")) {
					rdo += "2";
				} else {
					rdo = null;
					break;
				}
			} else {
				if (apu.substring(i, i + 1).startsWith("1")) {
					rdo += "0";
				} else if (apu.substring(i, i + 1).startsWith("2")) {
					rdo += "1";
				} else if (apu.substring(i, i + 1).startsWith("4")) {
					rdo += "2";
				} else if (apu.substring(i, i + 1).startsWith("8")) {
					rdo += "M";
				} else {
					rdo = null;
					break;
				}
			}
		}

		return rdo;
	}
	
	public int isBetAllowed(int dobles, int doblesRed, int triples, int triplesRed, int pleno1, int pleno2){
		boolean redOk = false;
		boolean betOk = false;
		
		double nBets= getNumberBets(0, dobles, triples, pleno1, pleno2);
		if ((triplesRed>0) || (doblesRed>0)) //QUINIELA REDUCIDA
		{
			if((triplesRed == 4) && (doblesRed == 0)){ //Reduccion 1 (4T: 9 apuestas)
				redOk= true;
				switch (triples){
				case 7:
					if (dobles == 0 && pleno1==1 && pleno2==1)
						betOk = true;
					break;
				case 6:
					if (dobles <= 2 && nBets<=26244)
						betOk = true;
					break;
				case 5:
					if (dobles <= 3 && nBets<=26244)
						betOk = true;
					break;
				case 4:
					if (dobles <= 5 && nBets<=23328)
						betOk = true;
					break;
				case 3:
					if (dobles <= 7 && nBets<=31104)
						betOk = true;
					break;
				case 2:
					if (dobles <= 8 && nBets<=31104)
						betOk = true;
					break;
				case 1:
					if (dobles <= 9 && nBets<=31104)
						betOk = true;
					break;
				case 0:
					if (dobles <= 10 && nBets<=27648)
						betOk = true;
					break;
				}
			}else if((triplesRed == 0) && (doblesRed == 7)){ //Reduccion 2 (7D: 16 apuestas)
				redOk = true; 
				switch (triples){
				case 6:
					if (dobles <= 1 && nBets<=23328)
						betOk = true;
					break;
				case 5:
					if (dobles <= 2 && nBets<=31104)
						betOk = true;
					break;
				case 4:
					if (dobles <= 3 && nBets<=31104)
						betOk = true;
					break;
				case 3:
					if (dobles <= 4 && nBets<=27648)
						betOk = true;
					break;
				case 2:
					if (dobles <= 5 && nBets<=27648)
						betOk = true;
					break;
				case 1:
					if (dobles <= 6 && nBets<=24576)
						betOk = true;
					break;
				case 0:
					if (dobles <= 7 && nBets<=24576)
						betOk = true;
					break;
				}
			}else if((triplesRed == 3) && (doblesRed == 3)){ //Reduccion 3 (3T + 3D: 24 apuestas)
				redOk = true;
				switch (triples){
				case 6:
					if (dobles==0 && pleno1==1 && pleno2==1)
						betOk = true;
					break;
				case 5:
					if (dobles<=2 && nBets<=23328)
						betOk = true;
					break;
				case 4:
					if (dobles<=4 && nBets<=31104)
						betOk = true;
					break;
				case 3:
					if (dobles<=5 && nBets<=31104)
						betOk = true;
					break;
				case 2:
					if (dobles<=6 && nBets<=27648)
						betOk = true;
					break;
				case 1:
					if (dobles<=7 && nBets<=27648)
						betOk = true;
					break;
				case 0:
					if (dobles<=8 && nBets<=24576)
						betOk = true;
					break;
				}
			}else if((triplesRed == 2) && (doblesRed == 6)){ //Reduccion 4 (2T + 6D: 64 apuestas)
				redOk = true;
				switch (triples){
				case 5:
					if (dobles<=1 && nBets<=31104)
						betOk = true;
					break;
				case 4:
					if (dobles<=2 && nBets<=31104)
						betOk = true;
					break;
				case 3:
					if (dobles<=3 && nBets<=31104)
						betOk = true;
					break;
				case 2:
					if (dobles<=4 && nBets<=27648)
						betOk = true;
					break;
				case 1:
					if (dobles<=5 && nBets<=27648)
						betOk = true;
					break;
				case 0:
					if (dobles<=6 && nBets<=24567)
						betOk = true;
					break;
				
				}
			}else if((triplesRed == 8) && (doblesRed == 0)){ //Reduccion 5 (8T: 81 apuestas)
				redOk = true;
				switch (triples){
				case 5:
					if (dobles==0 && pleno1==1 && pleno1==1)
						betOk = true;
					break;
				case 4:
					if (dobles<=2 && nBets<=26244)
						betOk = true;
					break;
				case 3:
					if (dobles<=3 && nBets<=26244)
						betOk = true;
					break;
				case 2:
					if (dobles<=4 && nBets<=23328)
						betOk = true;
					break;
				case 1:
					if (dobles<=5 && nBets<=23328)
						betOk = true;
					break;
				case 0:
					if (dobles<=6 && nBets<=31104)
						betOk = true;
					break;
				}
			}else if((triplesRed == 0) && (doblesRed == 11)){ //Reduccion 6 (11D: 132 apuestas)
				redOk = true;
				switch(triples){
				case 3:
					if (dobles==0 && nBets<=28512)
						betOk = true;
					break;
				case 2:
					if (dobles<=1 && nBets<=28512)
						betOk = true;
					break;
				case 1:
					if (dobles<=2)
						betOk = true;
					break;
				case 0:
					if (dobles<=3)
						betOk = true;
					break;
				}
			}
			
			if (!redOk)
				return -1;
			
			if (!betOk)
				return -2;
			
		}else{ //QUINIELA DIRECTA
			switch (triples){
			case 9:
				if (dobles == 0 && pleno1==1 && pleno2==1)
					betOk = true;
				break;
			case 8:
				if (dobles<=2 && nBets<=26244)
					betOk = true;
				break;
			case 7:
				if (dobles <= 3 && nBets<=17496)
					betOk = true;
				break;
			case 6:
				if (dobles <= 5 && nBets<=23328)
					betOk = true;
				break;
			case 5:
				if (dobles <= 7 && nBets<=31104)
					betOk = true;
				break;
			case 4:
				if (dobles <= 8 && nBets<=31104)
					betOk = true;
				break;
			case 3:
				if (dobles <= 10 && nBets<=27648)
					betOk = true;
				break;
			case 2:
				if (dobles <= 11 && nBets<=27648)
					betOk = true;
				break;
			case 1:
				if (dobles <= 13 && nBets<=27648)
					betOk = true;
				break;
			case 0:
				if (dobles <= 14 && nBets<=24576)
					betOk = true;
				break;
			}
			
			if (!betOk)
				return -2;
			
		}
		
		return 0;
	}
	
	public String randomBet() {
		String solucion = "";
		for (int i = 1; i <= 15; i++) {
			int inferior = 1;
			int superior = 3;
			int infPleno15 = 1;
			int supPleno15 = 4;
			int numPosibilidades = 0;
			double aleat = 0;

			if (i != 15) {
				numPosibilidades = (superior + 1) - inferior;
				aleat = Math.random() * numPosibilidades;
				aleat = Math.floor(aleat);
				aleat = (inferior + aleat);
				if (aleat > 2) {
					solucion = solucion + "1";
				} else if (aleat > 1) {
					solucion = solucion + "2";
				} else {
					solucion = solucion + "4";
				}
			} else {
				numPosibilidades = (supPleno15 + 1) - infPleno15;
				// Calculo primer partido del Pleno al 15
				aleat = Math.random() * numPosibilidades;
				aleat = Math.floor(aleat);
				aleat = (inferior + aleat);
				if (aleat > 3) {
					solucion = solucion + "1";
				} else if (aleat > 2) {
					solucion = solucion + "2";
				} else if (aleat > 1) {
					solucion = solucion + "4";
				} else {
					solucion = solucion + "8";
				}
				// Calculo segundo partido del Pleno al 15
				aleat = Math.random() * numPosibilidades;
				aleat = Math.floor(aleat);
				aleat = (inferior + aleat);
				if (aleat > 3) {
					solucion = solucion + "1";
				} else if (aleat > 2) {
					solucion = solucion + "2";
				} else if (aleat > 1) {
					solucion = solucion + "4";
				} else {
					solucion = solucion + "8";
				}
			}
		}
		return solucion;

	}

	/**
	 * Function calcUserRightSigns that calcs bet user's right sings
	 * 
	 * ResultBet Signs: 1 = 100 = 4 X = 010 = 2 2 = 001 = 1
	 * 
	 * Bet Signs: 1 = 100 = 4 X = 010 = 2 2 = 001 = 1 1X = 110 = 6 1 2 = 101 = 5
	 * X2 = 011 = 3 1X2 = 111 = 7
	 * 
	 * Pleno15 Signs: 0 = 0001 = 1 1 = 0010 = 2 01 = 0011 = 3 2 = 0100 = 4 0 2 =
	 * 0101 = 5 12 = 0110 = 6 012 = 0111 = 7 M = 1000 = 8 0 M = 1001 = 9 1 M =
	 * 1010 = a 0 2M = 1011 = b 2M = 1100 = c 01 M = 1101 = d 12M = 1110 = e
	 * 012M = 1111 = f
	 * 
	 * Gets users Bets, and calculate right signs for each bet
	 * 
	 * @return int[] Devuelve un vector de int donde: int[0] es el numero de
	 *         aciertos int[1] es el número de doses acertados int[2] es el
	 *         número de equis acertadas int[3] es el número de unos acertados
	 *
	 * @param String
	 *            resultBet : result round bet
	 * @param String
	 *            apu : user bet
	 * @param String
	 *            user : user id
	 * 
	 *            Descripcion: Calculate right signs for each user bet
	 * */

	public int[] calcUserRightSigns(String resultBet, String apu) {

		int rdo, doses, equis, unos, p15;
		int[] salida = new int[5];
		salida[0] = salida[1] = salida[2] = salida[3] = salida[4] = -1;

		rdo = 0;
		doses = 0;
		equis = 0;
		unos = 0;
		p15 = 0;

		int singBet;
		int singRes;

		try {
			// Translate 1X2 to 421
			// Pleno15 012M to 1248

			for (int i = 0; i < apu.length(); i++) {
				singBet = Integer.parseInt(apu.substring(i, i + 1));
				singRes = Integer.parseInt(resultBet.substring(i, i + 1));
				if (i < 14) {
					switch (singRes) {
					case 4:// sign 1
						if ((singBet == 4) || (singBet == 6) || (singBet == 5) || (singBet == 7)) {
							rdo++;
							unos++;
						}
						break;
					case 2:// sign X
						if ((singBet == 2) || (singBet == 3) || (singBet == 6) || (singBet == 7)) {
							rdo++;
							equis++;
						}
						break;
					case 1: // sign 2
						if ((singBet == 1) || (singBet == 3) || (singBet == 5) || (singBet == 7)) {
							rdo++;
							doses++;
						}
						break;
					default: // something wrong
						break;
					}
				} else {
					//TODO check the new pleno al 15
					switch (singRes) {
					case 1:// sign 0
						if ((singBet == 1) || (singBet == 3) || (singBet == 5) || (singBet == 7) || (singBet == 9) || (singBet == 11) || (singBet == 13) || (singBet == 15)) {
							p15++;
						}
						break;
					case 2:// sign 1
						if ((singBet == 2) || (singBet == 3) || (singBet == 6) || (singBet == 7) || (singBet == 10) || (singBet == 13) || (singBet == 14) || (singBet == 15)) {
							p15++;
						}
						break;
					case 4: // sign 2
						if ((singBet == 4) || (singBet == 5) || (singBet == 6) || (singBet == 7) || (singBet == 11) || (singBet == 12) || (singBet == 14) || (singBet == 15)) {
							p15++;
						}
						break;
					case 8: // sign M
						if ((singBet == 8) || (singBet == 9) || (singBet == 10) || (singBet == 11) || (singBet == 12) || (singBet == 13) || (singBet == 14) || (singBet == 15)) {
							p15++;
						}
						break;
					default: // something wrong
						break;
					}

				}
			}
		} catch (Exception e) {
			rdo = 0;
			doses = 0;
			equis = 0;
			unos = 0;
			p15 = 0;
		}
		//Verificamos si el pleno al 15 está acertado
		if (p15 == 2)
			p15=1;
		else
			p15=0;
		// Asignamos los resultados al vector final
		// Numero de apuestas acertadas
		salida[0] = rdo + p15;
		// Número de doses acertados
		salida[1] = doses;
		// Número de equis acertadas
		salida[2] = equis;
		// Número de unos acertados
		salida[3] = unos;
		salida[4] = p15;

		return salida;
	}

}