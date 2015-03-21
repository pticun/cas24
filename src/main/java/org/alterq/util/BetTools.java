package org.alterq.util;

import java.lang.Math;

public class BetTools{
	public double getNumberBets(int typeReduction, int dobles, int triples, int pleno1, int pleno2){
		double numBets = Math.pow(2, dobles) * Math.pow(3, triples)*pleno1*pleno2;
		switch (typeReduction){
			case 0: numBets *= 1;	break;//Quiniela Directa sin Reduccion
			case 1: numBets *= 9;	break;//Reduccion 1 (4T: 9 apuestas)
			case 2: numBets *= 16;	break;//Reduccion 2 (7D: 16 apuestas)
			case 3: numBets *= 24;	break;//Reduccion 3 (3T + 3D: 24 apuestas)
			case 4: numBets *= 64;	break;//Reduccion 4 (2T + 6D: 64 apuestas)
			case 5: numBets *= 81;	break;//Reduccion 5 (8T: 81 apuestas)
			case 6: numBets *= 132;	break;//Reduccion 6 (11D: 132 apuestas)
			default: numBets = 0;	break;
		}
			
		return numBets;
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

}