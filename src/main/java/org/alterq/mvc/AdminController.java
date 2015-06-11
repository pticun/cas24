package org.alterq.mvc;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.alterq.domain.Bet;
import org.alterq.domain.Game;
import org.alterq.domain.GeneralData;
import org.alterq.domain.Prize;
import org.alterq.domain.Ranking;
import org.alterq.domain.Round;
import org.alterq.domain.RoundBets;
import org.alterq.domain.UserAlterQ;
import org.alterq.dto.AlterQConstants;
import org.alterq.dto.ErrorDto;
import org.alterq.dto.ResponseDto;
import org.alterq.exception.SecurityException;
import org.alterq.repo.GeneralDataDao;
import org.alterq.repo.RoundBetDao;
import org.alterq.repo.RoundDao;
import org.alterq.repo.RoundRankingDao;
import org.alterq.repo.SessionAlterQDao;
import org.alterq.repo.UserAlterQDao;
import org.alterq.security.UserAlterQSecurity;
import org.alterq.util.CalculateRigths;
import org.apache.commons.collections.map.MultiValueMap;
import org.apache.commons.lang3.StringUtils;
import org.arch.core.file.BetElectronicFile;
import org.arch.core.file.HeaderBetElectronicFile;
import org.arch.core.file.RegistroBetElectronicFile;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = { "/admin" })
public class AdminController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SessionAlterQDao sessionDao;
	@Autowired
	private GeneralDataDao dao;
	@Autowired
	private RoundBetDao roundBetDao;
	@Autowired
	private UserAlterQDao userAlterQDao;
	@Autowired
	private RoundRankingDao roundRankingDao;
	@Autowired
	private RoundDao roundDao;
	@Autowired
	private UserAlterQSecurity userSecurity;
	
	
	private static int doubles = 0;
	private static int triples = 0;

	private static final float DEF_QUINIELA_BET_PRICE = (float)0.5;

	@RequestMapping(method = RequestMethod.GET)
	public String initPage() {
		log.debug("init admin.jsp");
		return "admin";
	}
	
	/**
	 * 
	 * */
	private String getAdmin()
	{
		UserAlterQ admin= userAlterQDao.findAdminByCompany(AlterQConstants.COMPANY);
		return admin.getId();
	}
	/**
	 * @return String randomBet
	 * Descripcion: Get a new ramdom bet
	 * */
	private static String randomBet()
	{
		String solucion = "";
		for(int i = 1; i <= 15; i++){
			int inferior = 1;
			int superior = 3;
			int infPleno15 = 1;
			int supPleno15 = 4;
    		int numPosibilidades = 0;
		    double aleat = 0;
    		
		    if (i !=15){
			    numPosibilidades = (superior + 1) - inferior;
			    aleat = Math.random() * numPosibilidades;
			    aleat = Math.floor(aleat);
				aleat = (inferior + aleat);
				if (aleat>2){
					solucion = solucion+"1";
				}
				else if (aleat>1){
					solucion = solucion+"2";
				}
				else{
					solucion = solucion+"4";
				}
		    }
		    else{
			    numPosibilidades = (supPleno15 + 1) - infPleno15;
			    //Calculo primer partido del Pleno al 15
			    aleat = Math.random() * numPosibilidades;
			    aleat = Math.floor(aleat);
				aleat = (inferior + aleat);
				if (aleat>3){
					solucion = solucion+"1";
				}
				else if (aleat>2){
					solucion = solucion+"2";
				}
				else if (aleat>1){
					solucion = solucion+"4";
				}
				else{
					solucion = solucion+"8";
				}
				//Calculo segundo partido del Pleno al 15
			    aleat = Math.random() * numPosibilidades;
			    aleat = Math.floor(aleat);
				aleat = (inferior + aleat);
				if (aleat>3){
					solucion = solucion+"1";
				}
				else if (aleat>2){
					solucion = solucion+"2";
				}
				else if (aleat>1){
					solucion = solucion+"4";
				}
				else{
					solucion = solucion+"8";
				}
		    }
		}
		return solucion;
		
	}
	
	//pendiente de revision para incorportar el TYPE de la quiniela
	private static void calcBasicDoublesAndTriples(int numBets, int type){
		if ((type == 0) || (numBets < 9)){
			triples = (int) ((numBets<3)?0:(Math.log(numBets)/Math.log(3)));
			doubles = (int) (((numBets - Math.pow(3, triples))<2)?0:(Math.log((int)(numBets / Math.pow(3, triples)))/Math.log(2)));
		}else{//It's a quiniela with reduction
			triples = (int) ((numBets < (3 * getQuinielaNumBets(type)))?0:(Math.log(numBets)/Math.log(3 * getQuinielaNumBets(type))));
			doubles = (int) (((numBets - Math.pow((3 * getQuinielaNumBets(type)), triples))<(2 * getQuinielaNumBets(type)))?0:(Math.log((int)(numBets / Math.pow((3 * getQuinielaNumBets(type)), triples)))/Math.log((2*getQuinielaNumBets(type)))));
		}
	}
	
	private static float calcQuinielaPrice(int doubles, int triples, int type){
		return new Double(getQuinielaNumBets(type) * DEF_QUINIELA_BET_PRICE * Math.pow(2, doubles) * Math.pow(3, triples)).floatValue();
	}
	
	private static int getQuinielaNumBets(int type){
		switch (type){
		case 0: return 1;	//Sencilla
		case 1: return 9;	//Reduccion Primera (4T)
		case 2: return 16;	//Reduccion Segunda (7D)
		case 3: return 24;	//Reduccion Tercera (3D + 3T)
		case 4: return 64;	//Reduccion Cuarta	(6D + 2T)
		case 5: return 81;	//Reduccion Quinta	(8T)
		default: return 1;
		}
	}
	private static void calcFinalDoublesAndTriples(int type)
	{
		switch(type){
		case 1: //Reduccion Primera (4T)
			triples+=4;
			break;
		case 2: //Reduccion Segunda (7D)
			doubles+=7;
			break;
		case 3: //Reduccion Tercera (3D + 3T)
			triples+=3;
			doubles+=3;
			break;
		case 4: //Reduccion Cuarta	(6D + 2T)
			triples+=2;
			doubles+=6;
			break;
		case 5: //Reduccion Quinta	(8T)
			triples+=8;
			break;
			
		default:
		}
	}
	/**
	 * Function calcFinalQuiniela that return the final quiniela
	 * 
	 * *************************************************************************
	 * PENDIENTE REVISAR ESTA FUNCION: SE UTILIZARA CUANDO SE ACTIVEN LAS PEÑAS
	 * Y HABRA QUE GESTIONAR EL NUEVO PLENO AL QUINCE (012M)
	 * *************************************************************************
	 * 
	 * Signs:
	 * 	1   = 100 = 4
	 * 	 X  = 010 = 2
	 *    2 = 001 = 1
	 *  1X  = 110 = 6
	 *  1 2 = 101 = 5
	 *   X2 = 011 = 3
	 *  1X2 = 111 = 7
	 *  
	 *   Gets users Bets, and calculate a value for each sign ( sum(user sign * user weight))
	 *   Selecrt max values until triples and double are completed
	 *   
	 * @return String final quiniela
	 * @param int doubles : number of doubles in the final quiniela
	 * @param int triples : number of triples in the final quiniela
	 * 
	 * Descripcion: Get a new ramdom bet
	 * */
	private String calcFinalQuiniela(int season, int round, int doubles, int triples, List<Bet> lBets){
		double [][]count1X2 = new double[15][3];
		String aux ="";
		
		for (Bet bet : lBets){
			String apu = bet.getBet();
			double usuWeight = calcUserWeight(bet.getUser());
			
			for(int j=0; j<15; j++){
				if (apu.charAt(j)=='4'){
					count1X2[j][0]=count1X2[j][0] + 1 * usuWeight;
				}else if (apu.charAt(j)=='2'){
					count1X2[j][1]=count1X2[j][1] + 1 * usuWeight;
				}else if (apu.charAt(j)=='1'){
					count1X2[j][2]=count1X2[j][2] + 1 * usuWeight;
				}else if (apu.charAt(j)=='6'){
					count1X2[j][0]=count1X2[j][0] + 1 * usuWeight;
					count1X2[j][1]=count1X2[j][1] + 1 * usuWeight;
				}else if (apu.charAt(j)=='5'){
					count1X2[j][0]=count1X2[j][0] + 1 * usuWeight;
					count1X2[j][2]=count1X2[j][2] + 1 * usuWeight;
				}else if (apu.charAt(j)=='3'){
					count1X2[j][1]=count1X2[j][1] + 1 * usuWeight;
					count1X2[j][2]=count1X2[j][2] + 1 * usuWeight;
				}else if (apu.charAt(j)=='7'){
					count1X2[j][0]=count1X2[j][0] + 1 * usuWeight;
					count1X2[j][1]=count1X2[j][1] + 1 * usuWeight;
					count1X2[j][2]=count1X2[j][2] + 1 * usuWeight;
				}
			}
		}
		
		//Select Max Values & set Quiniela Final
		//...
		
		//Select Simple Final Quiniela
		for(int j=0; j<15; j++){
			if ((count1X2[j][0]>=count1X2[j][1]) && (count1X2[j][0]>=count1X2[j][2]))
			{
				count1X2[j][0]= -1;
				continue;
			}else if ((count1X2[j][1]>=count1X2[j][0]) && (count1X2[j][1]>=count1X2[j][2]))
			{
				count1X2[j][1]= -1;
				continue;
				
			}else if ((count1X2[j][2]>=count1X2[j][0]) && (count1X2[j][2]>=count1X2[j][1]))
			{
				count1X2[j][2]= -1;
				continue;
			}else{
				count1X2[j][0]= -1;
			}
				
		}
		//Select Doubles
		int aX  =0;
		int aY  =0;
		double max = count1X2[0][0];
		for (int i=0; i<doubles; i++){
			max=0;
			for(int j=0; j<14; j++){//Don't put doubles in plenoAlQuince
				for (int k=0; k<3; k++)
				{
					if(count1X2[j][k]>max)
					{
						max = count1X2[j][k];
						aX = j;
						aY = k;
					}
				}
			}
			count1X2[aX][aY] = -1;
		}
		
		//Select Triples
		aX  =0;
		max = count1X2[0][0];
		for (int i=0; i<triples; i++){
			max=0;
			for(int j=0; j<14; j++){ //Don't put triples in plenoAlQuince
				for (int k=0; k<3; k++)
				{
					if(count1X2[j][k]>max)
					{
						max = count1X2[j][k];
						aX = j;
					}
				}
			}
			//There isn't any candidate. It's choosen the first one.
			if (aX == 0){
				for(int j=0; j<14; j++){ //Don't put triples in plenoAlQuince
					//check if it's a double
					if((count1X2[j][0]!=-1 && count1X2[j][1]!=-1 && count1X2[j][2]!=-1) ||
					   (count1X2[j][0]==-1 && count1X2[j][1]!=-1 && count1X2[j][2]!=-1) ||
					   (count1X2[j][0]!=-1 && count1X2[j][1]==-1 && count1X2[j][2]!=-1) ||
					   (count1X2[j][0]!=-1 && count1X2[j][1]!=-1 && count1X2[j][2]==-1))
						
					{
						count1X2[j][0] = -1;
						count1X2[j][1] = -1;
						count1X2[j][2] = -1;
						break;
					}
				}
			}else{
				count1X2[aX][0] = -1;
				count1X2[aX][1] = -1;
				count1X2[aX][2] = -1;
			}
		}
		
		//Set Final Quiniela
		for(int j=0; j<15; j++){
			if((count1X2[j][0]==-1)&&(count1X2[j][1]==-1)&&(count1X2[j][2]==-1))
				aux+="7";
			else if((count1X2[j][0]==-1)&&(count1X2[j][1]==-1))
				aux+="6";
			else if((count1X2[j][0]==-1)&&(count1X2[j][2]==-1))
				aux+="5";
			else if((count1X2[j][1]==-1)&&(count1X2[j][2]==-1))
				aux+="3";
			else if(count1X2[j][0]==-1)
				aux+="4";
			else if(count1X2[j][1]==-1)
				aux+="2";
			else if(count1X2[j][2]==-1)
				aux+="1";
		}
		
		return aux;
	}
	
	private double calcUserWeight(String user){
		
		UserAlterQ userAlterQ;
		
		userAlterQ = userAlterQDao.findById(user);
		
		if (userAlterQ!= null)
			return userAlterQ.getWeight();
		
		return 0.0;
	}
	
	/**
	 * Function translateResult1x2 that translates 1X2 signs to 421
	 * 
	 * Format BET: S01S02S03S04S05S06S07S08S09S10S11S12S13S14P01P02
	 * 
	 * ResultBet Signs (Sxx):
	 * 	1   = 100 = 4
	 * 	 X  = 010 = 2
	 *    2 = 001 = 1
	 *    
	 *  pleno 15 (Pxx)
	 *  0   = 1
	 *  1   = 2
	 *  2   = 4
	 *  M   = 8
	 **/  	
	private String translateResult1x2 (String apu)
	{
		String rdo = "";
	
		for (int i = 0; i<apu.length(); i++){
			if (i<14){
				if (apu.substring(i, i+1).startsWith("1")){
					rdo +="4";
				}else if (apu.substring(i, i+1).startsWith("X")){
					rdo +="2";
				}else if (apu.substring(i, i+1).startsWith("2")){
					rdo +="1";
				}
				else{
					rdo = null;
					break;
				}
			}else{
				if (apu.substring(i, i+1).startsWith("0")){
					rdo +="1";
				}else if (apu.substring(i, i+1).startsWith("1")){
					rdo +="2";
				}else if (apu.substring(i, i+1).startsWith("2")){
					rdo +="4";
				}else if (apu.substring(i, i+1).startsWith("M")){
					rdo +="8";
				}
				else{
					rdo = null;
					break;
				}
			}
		}
		
		return rdo;
	}

	/**
	 * Function calcUserRightSigns that calcs bet user's right sings
	 * 
	 * ResultBet Signs:
	 * 	1   = 100 = 4
	 * 	 X  = 010 = 2
	 *    2 = 001 = 1
	 *  
	 * Bet Signs:
	 * 	1   = 100 = 4
	 * 	 X  = 010 = 2
	 *    2 = 001 = 1
	 *  1X  = 110 = 6
	 *  1 2 = 101 = 5
	 *   X2 = 011 = 3
	 *  1X2 = 111 = 7

	 * Pleno15 Signs:
	 * 	0    = 0001 = 1
	 * 	 1   = 0010 = 2
	 * 	01   = 0011 = 3
	 * 	  2  = 0100 = 4
	 * 	0 2  = 0101 = 5
	 * 	 12  = 0110 = 6
	 * 	012  = 0111 = 7
	 * 	   M = 1000 = 8
	 * 	0  M = 1001 = 9
	 * 	 1 M = 1010 = a
	 * 	0 2M = 1011 = b
	 * 	  2M = 1100 = c
	 * 	01 M = 1101 = d
	 * 	 12M = 1110 = e
	 * 	012M = 1111 = f

	 *   Gets users Bets, and calculate right signs for each bet
	 * @return int[]   
	 * Devuelve un vector de int donde:
	 * int[0] es el numero de aciertos
	 * int[1] es el número de doses acertados
	 * int[2] es el número de equis acertadas
	 * int[3] es el número de unos acertados
	 *
	 * @param String resultBet : result round bet
	 * @param String apu : user bet
	 * @param String user : user id
	 * 
	 * Descripcion: Calculate right signs for each user bet
	 * */
	
	private int[] calcUserRightSigns(String resultBet, String apu, UserAlterQ user, int company){
		
		int rdo, doses, equis, unos, p15;
		int [] salida = new int[5];
		salida[0]=salida[1]=salida[2]= salida[3]= salida[4] =-1;
		
		rdo = 0;
		doses = 0;
		equis = 0;
		unos = 0;
		p15 = 0;
		
		int singBet;
		int singRes;
		
		try{
			//Translate 1X2 to 421
			//Pleno15 012M to 1248
			
			for (int i = 0; i<apu.length(); i++){
				singBet = Integer.parseInt(apu.substring(i, i+1));
				singRes = Integer.parseInt(resultBet.substring(i, i+1));
				if (i<14)
				{
					switch (singRes)
					{
					case 4:// sign 1
						if ((singBet == 4) || (singBet == 6) || (singBet == 5) || (singBet == 7)){
							rdo++;
							unos++;
						}
						break;
					case 2://sign X
						if ((singBet == 2) || (singBet == 3) || (singBet == 6) || (singBet == 7)){
							rdo++;
							unos++;
						}
						break;
					case 1: //sign 2
						if ((singBet == 1) || (singBet == 3) || (singBet == 5) || (singBet == 7)){
							rdo++;
							unos++;
						}
						break;
					default: //something wrong
						break;
					}
				}
				else{
					switch (singRes)
					{
					case 1:// sign 0
						if ((singBet == 1) || (singBet == 3) || (singBet == 5) || (singBet == 7) || (singBet == 9) || (singBet == 11) || (singBet == 13) || (singBet == 15)){
							p15++;
						}
						break;
					case 2://sign 1
						if ((singBet == 2) || (singBet == 3) || (singBet == 6) || (singBet == 7) || (singBet == 10) || (singBet == 13) || (singBet == 14) || (singBet == 15)){
							p15++;
						}
						break;
					case 4: //sign 2
						if ((singBet == 4) || (singBet == 5) || (singBet == 6) || (singBet == 7) || (singBet == 11) || (singBet == 12) || (singBet == 14) || (singBet == 15)){
							p15++;
						}
						break;
					case 8: //sign M
						if ((singBet == 8) || (singBet == 9) || (singBet == 10) || (singBet == 11)|| (singBet == 12)|| (singBet == 13)|| (singBet == 14)|| (singBet == 15)){
							p15++;
						}
						break;
					default: //something wrong
						break;
					}
					
				}
			}
		} catch (Exception e){
			rdo = 0;
			doses = 0;
			equis = 0;
			unos = 0;
			p15 = 0;
		}
		//Asignamos los resultados al vector final
		//Numero de apuestas acertadas
		salida[0]=rdo;
		//Número de doses acertados
		salida[1]= doses;
		//Número de equis acertadas
		salida[2]= equis;
		//Número de unos acertados
		salida[3]= unos;
		salida[4]= p15;
		
		return salida;
	}
	
	/**
	 * Funcion para calcular el peso de cada usuario
	 * 
	 * Se utilizará cuando se activen las peñas. De momento no se utilizará
	 * */
	private void updateUserWeight(UserAlterQ user, int rightSigns){
		double oldWeight = user.getWeight();
		
		user.setWeight(oldWeight+rightSigns);
		
		userAlterQDao.save(user);
		
		return;
	}
	
	
	private static int[] calcularAciertos(String bet, String resultBet){
		
		return null;
	}

		//Funciona
	private void updateRoundRanking(int company, int season, int round, UserAlterQ user, int points, int ones, int equs, int twos){
		Ranking rnk = new Ranking();
		rnk.setCompany(company);
		rnk.setOnes(ones);
		rnk.setEqus(equs);
		rnk.setTwos(twos);
		rnk.setPoints(points);
		rnk.setUser(user);
		
		roundRankingDao.addRanking(company, season, round, rnk);
	}
	
	@RequestMapping(method = RequestMethod.POST, produces = "application/json", value = "/company/{company}/season/{season}/round/{round}/open")
	public @ResponseBody 
	ResponseDto openRound(@CookieValue(value = "session", defaultValue = "") String cookieSession,@PathVariable int company, @PathVariable int season, @PathVariable int round) {
		GeneralData generalData = null;
		ResponseDto response = new ResponseDto();
		
		log.debug("openRound: start");
		try {
			userSecurity.isAdminUserInSession( cookieSession);
			generalData = dao.findByCompany(company);
			
			//OPENING PROCESS STEPS
			//---------------------

			//STEP 1: update generalData
			
			//if exist, update active=true
			if (generalData != null){
				log.debug("openRound: active=true");
				if ((company == generalData.getCompany()) && (season == generalData.getSeason()) && (round == generalData.getRound()) && (generalData.isActive()))
				{
					log.debug("openRound: Round is already actived");
				}
				else{
					generalData.setCompany(company);
					generalData.setSeason(season);
					generalData.setRound(round);
					generalData.setActive(true);
					dao.update(generalData);
				}
			}
			else{//if not exist, create a new generalData (active=true)
				log.debug("openRound: new generalData active=true");
				generalData = new GeneralData();
				generalData.setActive(true);
				generalData.setCompany(company);
				generalData.setRound(round);
				generalData.setSeason(season);
				
				dao.add(generalData);
			}
			
			//STEP 2: Create roundData (RoundBets collection)
			 RoundBets roundBets = roundBetDao.findAllBets(season, round);
			//Check if exist this roundBet
			if (roundBets == null){
				RoundBets bean= new RoundBets();
				bean.setCompany(company);
				bean.setRound(round);
				bean.setSeason(season);
				roundBetDao.add(bean);
			}
			
		} catch (SecurityException e) {
			response.addErrorDto("AdminController:openRound", "SecurityException");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		log.debug("openRound: end");
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST, produces = "application/json", value = "/company/{company}/season/{season}/round/{round}/close")
	public @ResponseBody 
	ResponseDto closeRound(@CookieValue(value = "session", defaultValue = "") String cookieSession,@PathVariable int company, @PathVariable int season, @PathVariable int round) {
		GeneralData generalData = null;
		ResponseDto response = new ResponseDto();
		log.debug("closeRound: start");
		
		try {
			userSecurity.isAdminUserInSession( cookieSession);
			generalData = dao.findByCompany(company);
			
			//CLOSING PROCESS STEPS
			//
			
			//STEP 1: if exist, update active=false
			//
			if (generalData != null){
				log.debug("closeRound: active=true");
				generalData.setActive(false);
				dao.update(generalData);
			}
			else{ //there is not round to close
				response.addErrorDto("AdminController:closeRound", "There is not round to close");
				return response;
			}
			
			//STEP 2: Automatics Bets
			//
			//STEP 2.1 - Get Users with automatic bets
			List<UserAlterQ> lUsers = userAlterQDao.findUserWithAutomatics(company);
			//STEP 2.2 - For each user do as bets as automatic bets (It has to check user amount before make automatics bets)
			for (UserAlterQ user : lUsers){
				//loop for number of automatic bets
				int numAutom = user.getAutomatics();
				for (int i=0; i<numAutom; i++)
				{
					//STEP 2.2.1 - Check User Balance
					float balance = new Float(user.getBalance()).floatValue();
					if (balance < DEF_QUINIELA_BET_PRICE){
						log.debug("closeRound: user("+user.getName()+") No enough money for automatic bet");
						//STEP 2.2.1.error - Send an email to the user ("NOT ENOUGH MONEY")
						continue;
					}
					//STEP 2.2.2 - Calc RandomBet
					String randomBet = randomBet();
					//STEP 2.2.3 - Make Automatic User Bet
					Bet bet = new Bet();
					bet.setPrice(DEF_QUINIELA_BET_PRICE);
					bet.setBet(randomBet);
					bet.setUser(user.getId());
					bet.setCompany(company);
					bet.setDateCreated(new Date());
					bet.setDateUpdated(new Date());
					bet.setId(new ObjectId().toStringMongod());	
					
					roundBetDao.addBet(season, round, bet);
					
					//STEP 2.2.4 - Update User Balance
					try{
						user.setBalance(Float.toString((float)(balance - DEF_QUINIELA_BET_PRICE)));
						userAlterQDao.save(user);
						/*
						if(userAlterQDao.getLastError() != null){    
							log.debug("closeRound: user("+user.getName()+") Error updating balance.");  
							//STEP 2.2.4.error - Send an email to the admin ("ERROR updating user balance")
							continue;
						}
						*/
					} catch (Exception e){
						log.debug("closeRound: user("+user.getName()+") Error updating balance.");
						//STEP 2.2.4.error - Send an email to the admin ("ERROR updating user balance")
						response.addErrorDto("AdminController:closeRound", " user("+user.getName()+") Error updating balance.");
						continue;
					}
				}
			} 

			
			//STEP 3: Fixed Bets (¿?)
		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			response.addErrorDto("AdminController:closeRound", "SecurityException");
			e1.printStackTrace();
		}
		


		log.debug("closeRound: end");
		return response;
	}

	@RequestMapping(method = RequestMethod.POST, produces = "application/json", value = "/company/{company}/season/{season}/round/{round}/finalBet/{type}")
	public @ResponseBody 
	ResponseDto finalBetRound(@CookieValue(value = "session", defaultValue = "") String cookieSession,@PathVariable int company, @PathVariable int season, @PathVariable int round, @PathVariable int type) {
		GeneralData generalData = null;
		ResponseDto response = new ResponseDto();
		log.debug("closeRound: start");
		
		try {
			userSecurity.isAdminUserInSession( cookieSession);
			generalData = dao.findByCompany(company);
			
			//FINAL BET PROCESS STEPS
			//
			

			//STEP 4: Quiniela
				//STEP 4.1 - Calc Number Bets
				int numBets = roundBetDao.countAllBets(season, round);
				
				//STEP 4.2 - Calc Number of Doubles and Triples
				calcBasicDoublesAndTriples(numBets, type);
				
				//STEP 4.3 - Calc Quiniela Price and Round Jackpot
				float price = calcQuinielaPrice(doubles, triples, type);
				float jackpot = ((price==0)?(float)0:(float)(numBets * DEF_QUINIELA_BET_PRICE - price));
				
				//STEP 4.4 - Update RoundData
				RoundBets rBets = roundBetDao.findAllBets(season, round);
				rBets.setJackpot(jackpot);
				rBets.setPrice(price);
				roundBetDao.update(rBets);

				calcFinalDoublesAndTriples(type);
				
				//STEP 4.5 - Calc Final Quiniela
				String finalQ = calcFinalQuiniela(season, round, doubles, triples, rBets.getBets());
				
				//STEP 4.6 - Add Final Bet (admin)

				//**********************************************************************
				//Check if exist admin bet for this round (Better optimization analizing rBets.getBets() for Admin)
				RoundBets rBetsAdmin =roundBetDao.findAllUserBets(season, round, getAdmin());
				if ((rBetsAdmin != null) && (rBetsAdmin.getBets().size() > 0))
					roundBetDao.deleteAllUserBets(season, round, getAdmin());
				//**********************************************************************
				
				Bet bet = new Bet();
				bet.setPrice((float)0.0);
				bet.setBet(finalQ);
				bet.setUser(getAdmin());
				bet.setCompany(company);
				bet.setDateCreated(new Date());
				bet.setDateUpdated(new Date());
				bet.setId(new ObjectId().toStringMongod());
				roundBetDao.addBet(season, round, bet);

		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			response.addErrorDto("AdminController:finalBetRound", "SecurityException");
			e.printStackTrace();
		}
		
		
		log.debug("closeRound: end");
		return response;
	}
	

	@RequestMapping(method = RequestMethod.POST, produces = "application/json", value = "/company/{company}/season/{season}/round/{round}/resultBet/{resultBet}")
	public @ResponseBody 
	ResponseDto  resutlBetRound(@CookieValue(value = "session", defaultValue = "") String cookieSession,@PathVariable int company, @PathVariable int season, @PathVariable int round, @PathVariable String resultBet) {
		ResponseDto response = new ResponseDto();
		UserAlterQ userAlterQ;

		try
		{
			userSecurity.isAdminUserInSession( cookieSession);
			//RESULT ROUND STEPS
			//---------------------
	
			//STEP 1: get users with bet
			String lastUser = null;
			UserAlterQ lastUserAlterQ = null;
			
			int[] vMaxAciertos = {0,0,0,0,0};
			boolean bUpdate = false;
		
			RoundBets bean = roundBetDao.findAllBets(season, round);
			
			//OJO!! hay que ordenar las apuestas por usuario para que funcione.
			List<Bet> lBets = bean.getBets();
			
			Collections.sort(lBets, new Comparator<Bet>() {
				@Override
				public int compare(Bet p1, Bet p2) {
					// Aqui esta el truco, ahora comparamos p2 con p1 y no al reves como antes
					return p2.getUser().compareTo(p1.getUser());
				}
			});
			
			//Translate result from "1X2" to "421"
			resultBet = translateResult1x2(resultBet);

			for (Bet bet : lBets){
				String apu = bet.getBet();
				String user = bet.getUser();
				userAlterQ = userAlterQDao.findById(user);
				
				if (userAlterQ== null){
					log.debug("closeRound: user("+user+") Error resultBet user not find");  
					//STEP 1.1.error - Send an email to the admin ("ERROR resultBet user not find")
					continue;
				}
				
				//La apuesta globla no se debe gestionar para el ranking
				if(user.equals(getAdmin()))
				{
					bUpdate = true;
					continue;
				}
	
				
				//STEP 2: calc user right signs
				int[] vAciertos = calcUserRightSigns(resultBet, apu, userAlterQ, company);
				if (vAciertos[0] == -1){
					log.debug("closeRound: user("+user+") Error updating right sings");  
					//STEP 2.1.error - Send an email to the admin ("ERROR updating user rigth signs")
					continue;
				}
				if (lastUser!=null)
				{
					if (user.equals(lastUser)){
						if (vMaxAciertos[0]<vAciertos[0])
						{
							vMaxAciertos[0] = vAciertos[0];
							vMaxAciertos[1] = vAciertos[1];
							vMaxAciertos[2] = vAciertos[2];
							vMaxAciertos[3] = vAciertos[3];
							vMaxAciertos[4] = vAciertos[4];
						}
						bUpdate = false;
					}				
					else{
						bUpdate = true;
					}
				}else{
					lastUser = user;
					lastUserAlterQ = userAlterQ;
					vMaxAciertos[0] = vAciertos[0];
					vMaxAciertos[1] = vAciertos[1];
					vMaxAciertos[2] = vAciertos[2];
					vMaxAciertos[3] = vAciertos[3];
					vMaxAciertos[4] = vAciertos[4];
					bUpdate = false;
				}
				
				
				//Update iter user
				if (bUpdate)
				{
					//STEP 3: update users weight
					updateUserWeight(userAlterQ, vMaxAciertos[0]);
					//STEP 4: update round ranking
					updateRoundRanking(company, season, round, lastUserAlterQ, vMaxAciertos[0], vMaxAciertos[3], vMaxAciertos[2], vMaxAciertos[1]);
					//SETP 5: update global ranking (round=0)
					updateRoundRanking(company, season, 0,lastUserAlterQ, vMaxAciertos[0], vMaxAciertos[3], vMaxAciertos[2], vMaxAciertos[1]);
					
					lastUser = user;
					lastUserAlterQ = userAlterQ;
					
					vMaxAciertos[0] = vAciertos[0];
					vMaxAciertos[1] = vAciertos[1];
					vMaxAciertos[2] = vAciertos[2];
					vMaxAciertos[3] = vAciertos[3];
					
					bUpdate = false;
				}
			}
			//Update last user
			if (bUpdate)
			{
				//STEP 3: update users weight
				updateUserWeight(lastUserAlterQ, vMaxAciertos[0]);
				//STEP 4: update round ranking
				updateRoundRanking(company, season, round, lastUserAlterQ, vMaxAciertos[0], vMaxAciertos[3], vMaxAciertos[2], vMaxAciertos[1]);
				//SETP 5: update global ranking (round=0)
				updateRoundRanking(company, season, 0,lastUserAlterQ, vMaxAciertos[0], vMaxAciertos[3], vMaxAciertos[2], vMaxAciertos[1]);
			}
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			response.addErrorDto("AdminController:addMatches", "SecurityException");
			e.printStackTrace();
		}
		
		return response;
	}
	
/*	
//	@RequestMapping(method = RequestMethod.POST, produces = "application/json", value = "/company/{company}/season/{season}/round/{round}/prize15/{count15}/{amount15}/prize14/{count14}/{amount14}/prize13/{count13}/{amount13}/prize12/{count12}/{amount12}/prize11/{count11}/{amount11}/prize10/{count10}/{amount10}")
//	public @ResponseBody 
//	ResponseDto  prizesRound(@PathVariable int company, @PathVariable int season, @PathVariable int round, @PathVariable int count15, @PathVariable float amount15, @PathVariable int count14, @PathVariable float amount14, @PathVariable int count13, @PathVariable float amount13, @PathVariable int count12, @PathVariable float amount12, @PathVariable int count11, @PathVariable float amount11, @PathVariable int count10, @PathVariable float amount10) {
	@RequestMapping(method = RequestMethod.POST, produces = "application/json", value = "/company/{company}/season/{season}/round/{round}/prizesBetPenya")
	//public @ResponseBody ResponseDto prizesRound(@CookieValue(value = "session", defaultValue = "") String cookieSession, HttpServletRequest request, @RequestBody PrizesRound prizesRound) {
	public @ResponseBody 
	ResponseDto  prizesRoundPenya(@CookieValue(value = "session", defaultValue = "") String cookieSession, HttpServletRequest request, @PathVariable int company, @PathVariable int season, @PathVariable int round) {
		ResponseDto response = new ResponseDto();
		RoundBets roundBets;
		int numBets;
		double rewardGlobal;
		double betReward = 0;
		UserAlterQ userAlterQ;
		
		try {
			userSecurity.isAdminUserInSession( cookieSession);
			roundBets = roundBetDao.findAllBets(season, round);
			
			List<Prize> lPrizes = new ArrayList<Prize>();
			Map<String, String[]> parameters = request.getParameterMap();

			for (int i=0;i<=5;i++)
			{
				Prize priceTmp = new Prize();
				priceTmp.setId(i+10);
				priceTmp.setCount(Integer.parseInt(parameters.get("count"+(i+10))[0]));
				priceTmp.setAmount(Float.parseFloat(parameters.get("prize"+(i+10))[0]));
				lPrizes.add(priceTmp);
			}


			roundBets.setPrizes(lPrizes);
			
			
			numBets = roundBets.getBets().size() - 1; //Admin bet is not a real bet
			//rewardGlobal = roundBets.getJackpot() + count15*amount15 + count14*amount14 + count13*amount13 + count12*amount12 + count11*amount11 + count10*amount10;
			rewardGlobal = roundBets.getJackpot();
			//List<Prize> lPrizes = roundBets.getPrizes();
			for (Prize prize : lPrizes){
				rewardGlobal+= prize.getAmount() * prize.getCount();
			}
			
			
			betReward = rewardGlobal / numBets;
			
			
			List<Bet> lBets = roundBets.getBets();
			for (Bet bet : lBets){
				String user = bet.getUser();
				
				userAlterQ = userAlterQDao.findById(user);
				
				if (userAlterQ== null){
					log.debug("pricesRound: user("+user+") Error resultBet user not find");  
					//STEP 1.1.error - Send an email to the admin ("ERROR pricesRound user not find")
					continue;
				}
				
				//Admin unser don't win money, because his bet is not a real bet
				if (userAlterQ.isAdmin())
				{
					continue;
				}
				
				userAlterQ.setBalance(Double.toString( Double.parseDouble(userAlterQ.getBalance())  + betReward));
				userAlterQDao.save(userAlterQ);
			}		
			
			roundBetDao.update(roundBets);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			response.addErrorDto("AdminController:prizesRound", "SecurityException");
			e.printStackTrace();
		}
		
		
		return response;
	}
	
*/	
	
	
	/*PENDIENTE
	 * 
	 * Hay que mirar todas las apuestas, comprobar el numero de aciertos que tiene y repartir los premios en funcion
	 * a la cantidad de premios obtenidos
	 * 
	 *  
	 * */
	@RequestMapping(method = RequestMethod.POST, produces = "application/json", value = "/season/{season}/round/{round}/prizesBet")
	//public @ResponseBody ResponseDto prizesRound(@CookieValue(value = "session", defaultValue = "") String cookieSession, HttpServletRequest request, @RequestBody PrizesRound prizesRound) {
	public @ResponseBody 
	ResponseDto  prizesRound(@CookieValue(value = "session", defaultValue = "") String cookieSession, HttpServletRequest request, @PathVariable int company, @PathVariable int season, @PathVariable int round) {
		ResponseDto response = new ResponseDto();
		RoundBets roundBets;
		int numBets;
		double rewardGlobal;
		double betReward = 0;
		UserAlterQ userAlterQ;
		int countPrizes[] = {0,0,0,0,0};
		
		try {
			userSecurity.isAdminUserInSession( cookieSession);
			roundBets = roundBetDao.findAllBets(season, round);
			
			List<Prize> lPrizes = new ArrayList<Prize>();
			Map<String, String[]> parameters = request.getParameterMap();


			
			List<Bet> lBets = roundBets.getBets();
			for (Bet bet : lBets){
				String user = bet.getUser();
				
				userAlterQ = userAlterQDao.findById(user);
				
				if (userAlterQ== null){
					log.debug("pricesRound: user("+user+") Error resultBet user not find");  
					//STEP 1.1.error - Send an email to the admin ("ERROR pricesRound user not find")
					continue;
				}

				CalculateRigths util = new CalculateRigths();
				countPrizes = util.calculate(parameters.get("results").toString(), bet.getBet(), bet.getReduction(), bet.getTypeReduction() );
				for (int i=0;i<=5;i++)
				{
					Prize priceTmp = new Prize();
					priceTmp.setId(i+10);
					priceTmp.setCount(countPrizes[i]);
					priceTmp.setAmount(Float.parseFloat(parameters.get("prize"+(i+10))[0]));
					lPrizes.add(priceTmp);
				}

				bet.setPrizes(lPrizes); 
				
				betReward=0;
				
				for (Prize prize : lPrizes){
					betReward+= prize.getAmount() * prize.getCount();
				}
				
				userAlterQ.setBalance(Double.toString( Double.parseDouble(userAlterQ.getBalance())  + betReward));
				userAlterQDao.save(userAlterQ);
			}		
			
			roundBetDao.update(roundBets);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			response.addErrorDto("AdminController:prizesRound", "SecurityException");
			e.printStackTrace();
		}
		
		
		return response;
	}

//	@RequestMapping(method = RequestMethod.POST, produces = "application/json", value = "/company/{company}/season/{season}/round/{round}/{local01}/{visitor01}/{local02}/{visitor02}/{local03}/{visitor03}/{local04}/{visitor04}/{local05}/{visitor05}/{local06}/{visitor06}/{local07}/{visitor07}/{local08}/{visitor08}/{local09}/{visitor09}/{local10}/{visitor10}/{local11}/{visitor11}/{local12}/{visitor12}/{local13}/{visitor13}/{local14}/{visitor14}/{local15}/{visitor15}")
//	public @ResponseBody 
//	ResponseDto  addRoundGames(@PathVariable int company, @PathVariable int season, @PathVariable int round, @PathVariable String local01, @PathVariable String visitor01, @PathVariable String local02, @PathVariable String visitor02, @PathVariable String local03, @PathVariable String visitor03, @PathVariable String local04, @PathVariable String visitor04, @PathVariable String local05, @PathVariable String visitor05, @PathVariable String local06, @PathVariable String visitor06, @PathVariable String local07, @PathVariable String visitor07, @PathVariable String local08, @PathVariable String visitor08, @PathVariable String local09, @PathVariable String visitor09, @PathVariable String local10, @PathVariable String visitor10, @PathVariable String local11, @PathVariable String visitor11, @PathVariable String local12, @PathVariable String visitor12, @PathVariable String local13, @PathVariable String visitor13, @PathVariable String local14, @PathVariable String visitor14, @PathVariable String local15, @PathVariable String visitor15) {
	@RequestMapping(method = RequestMethod.POST, produces = "application/json", value = "/company/{company}/season/{season}/round/{round}/matches")
	public @ResponseBody ResponseDto addMatches(@CookieValue(value = "session", defaultValue = "") String cookieSession,HttpServletRequest request, @PathVariable int company, @PathVariable int season, @PathVariable int round) {
		ResponseDto response = new ResponseDto();
		Round myRound = new Round();
		Round tmpRound;
		
		try {
			userSecurity.isAdminUserInSession( cookieSession);
			Map<String, String[]> parameters = request.getParameterMap();
			List<Game> lGames = new ArrayList<Game>();
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date dt = df.parse(parameters.get("dateMatches")[0]);
			
			for (int i=1;i<=15;i++)
			{
				Game gameTmp = new Game();
				gameTmp.setId(i);
				gameTmp.setPlayer1(parameters.get("matchlocal" + ((i<10)?"0"+i:i))[0]);
				gameTmp.setPlayer2(parameters.get("matchvisit" + ((i<10)?"0"+i:i))[0]);
				lGames.add(gameTmp);
			}

			myRound.setCompany(company);
			myRound.setSeason(season);
			myRound.setRound(round);
			myRound.setDateRound(dt);
			myRound.setGames(lGames);
			
			tmpRound = roundDao.findBySeasonRound(season, round);
			
			if (tmpRound != null)
			{
				roundDao.deleteRound(company, season, round);
			}
			
			roundDao.addRound(myRound);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			response.addErrorDto("AdminController:addMatches", "SecurityException");
			e.printStackTrace();
		} catch (Exception e){
			response.addErrorDto("AdminController:addMatches", "Exception");
			e.printStackTrace();
		}

		
		
		return response;
	}
	@RequestMapping(method = RequestMethod.POST, produces = "application/json", value = "/company/{company}/user/{user}/balance/{balance}/updateBalanceUser")
	public @ResponseBody 
	ResponseDto  updateBalanceUser(@CookieValue(value = "session", defaultValue = "") String cookieSession,@PathVariable int company, @PathVariable String user, @PathVariable String balance) {
		ResponseDto response = new ResponseDto();
		UserAlterQ userAlterQ;

		try
		{
			userSecurity.isAdminUserInSession( cookieSession);
			
			userAlterQ = userAlterQDao.findById(user);
			
			if (userAlterQ == null){
				log.debug("updateBalance: user("+user+") Error resultBet user not find");
				
				ErrorDto error = new ErrorDto();
				error.setIdError(AlterQConstants.USER_NOT_EXIST);
				error.setStringError("User does not exist");
				response.addErrorDto(error);			
			}
			
			userAlterQ.setBalance(Double.toString( Double.parseDouble(balance)));
			userAlterQDao.save(userAlterQ);
			
		}catch (SecurityException e) {
			response.addErrorDto("AdminController:updateBalance", "SecurityException");
			e.printStackTrace();
		}catch (Exception e) {
			response.addErrorDto("AdminController:updateBalance", "Generic Update Error");
			e.printStackTrace();
		}
		
		
		return response;
	}
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE, value = "/company/{company}/season/{season}/round/{round}/getFile")
	public @ResponseBody void getElectricFile(@CookieValue(value = "session", defaultValue = "") String cookieSession,@PathVariable int company, @PathVariable int season, @PathVariable int round,HttpServletResponse resp) {
		GeneralData generalData = null;
		ResponseDto response = new ResponseDto();
		String responseString=new String();
		
		
		log.debug("getElectricFile: start");
		
		try {
			userSecurity.isAdminUserInSession( cookieSession);
			generalData = dao.findByCompany(company);
		
			
			Round tmpRound = roundDao.findBySeasonRound(season, round);
			//Get All Round Bets
			 RoundBets roundBets = roundBetDao.findAllBets(season, round);
			 if (roundBets == null){
				 response.addErrorDto("AdminController:getElectricFile", "No Bets");
			 }
			 else{
					CalculateRigths aux = new CalculateRigths();
					String rdo[] = new String[0];
					String despApuesta[];
					List<Bet> lBets = roundBets.getBets();
					for (Bet bet : lBets){
						//Calculamos el desglose de cada apuesta
						if ((bet.getBet()==null) || (bet.getBet().length()<16)){
							//Tenemos que enviar un aviso al usuario
							log.debug("APUESTA ERRONEA (BET="+bet.getBet()+"USER="+bet.getUser());
							continue;
						}
						if ((bet.getReduction()==null)||(bet.getReduction().length()<14)){
							//Tenemos que enviar un aviso al usuario
							log.debug("APUESTA ERRONEA (BET="+bet.getBet()+" REDUCTION="+bet.getReduction()+" USER="+bet.getUser());
							continue;
						}
						if ((bet.getTypeReduction()<0)){
							//Tenemos que enviar un aviso al usuario
							log.debug("APUESTA ERRONEA (BET="+bet.getBet()+" REDUCTION="+bet.getReduction()+" TIPO="+ bet.getTypeReduction() +" USER="+bet.getUser());
							continue;
						}
						try{
							
							despApuesta = aux.unfolding(bet.getBet(), bet.getReduction(), bet.getTypeReduction());
							if (despApuesta == null){
								//Tenemos que enviar un aviso al usuario
								log.debug("ERROR EN DESGLOSE USER="+bet.getUser());
								continue;
							}
						
						}catch (Exception e){
							//Tenemos que enviar un aviso al usuario
							log.debug("ERROR EN DESGLOSE USER="+bet.getUser());
							continue;
						}
						
						rdo = aux.acumula(rdo, despApuesta);
						
					}
					//En este punto tenemos ya el array con todas las apuestas desplegadas
					
					//Hay que ordenar las apuestas por el pleno al 15
					
					//Hay que calcular cuantas apuestas y cuantos bloques hay para cada pleno al 15 distinto
					int bloques = rdo.length;//Pendiente revisar
					
					//Creamos la cabecera del fichero
					HeaderBetElectronicFile cb = new HeaderBetElectronicFile();
					cb.setIdDelegacion("12");
					cb.setIdReceptor("24380");

					BetElectronicFile befile=new BetElectronicFile();
					befile.setCabecera(cb);

					RegistroBetElectronicFile[] registro=new RegistroBetElectronicFile[rdo.length];

					log.debug("numApuestas="+rdo.length);
					
					MultiValueMap mhm=ordenarApuestas(rdo);
					Set<String> keys = mhm.keySet();
					int numBloquesPleno15=keys.size();
					log.debug("numBloquesPleno15="+numBloquesPleno15);
					int indexBloquesTotal=1;
					for (Object k : keys) {  
					    log.debug("("+k+" : "+mhm.get(k)+")");  
					    int numApuestasIgualPleno15=mhm.getCollection(k).size();
					    int[] modulusBloque=modulusBloque(numApuestasIgualPleno15);
						int numBloques=modulusBloque[0];
						int indexIterator=0;
						int numApuestaLastBloque=0;
						int numBloquesContador=1;
					    log.debug("numBloque="+modulusBloque[0]);
					    log.debug("modBloque="+modulusBloque[1]);
					    log.debug("numApuestasIgualPleno15="+numApuestasIgualPleno15);
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
//							log.debug(linea);
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
					
					//check data round in create Round
					//cb.setFechaJornada("010115");
					Date dt = tmpRound.getDateRound();
					DateFormat df = new SimpleDateFormat("ddMMyy");
					String dtf = df.format(dt); 
					
					cb.setFechaJornada(dtf);
					cb.setNumJornada(StringUtils.leftPad(""+tmpRound.getRound(), 2, '0'));
					cb.setNumTotalApuestas(StringUtils.leftPad(""+rdo.length, 6, '0'));
					cb.setNumTotalBloques(StringUtils.leftPad(""+(indexBloquesTotal-1), 6, '0'));
					
					log.debug(befile.getCabeceraString());
					log.debug(befile.getRegistroString());
					
					responseString=befile.getCabeceraString()+""+befile.getRegistroString();
					
					resp.setContentType("application/force-download"); 
					resp.setHeader("Content-Disposition", "attachment; filename=\"AD243\"");
					resp.getOutputStream().write((befile.getCabeceraString()+""+befile.getRegistroString()).getBytes());
					resp.flushBuffer();
			 }

			
		} catch (Exception e) {
			response.addErrorDto("AdminController:getElectricFile", "SecurityException");
			// TODO Auto-generated catch block
			e.printStackTrace();
//			return "";
		}

		log.debug("getElectricFile: end");
//		return responseString;
		
//		return response;
	}
	
	private MultiValueMap ordenarApuestas(String rdo[]){
		MultiValueMap mhm = new MultiValueMap();
		
		for (int i = 0; i < rdo.length; i++) {
			String linea = rdo[i];
			mhm.put(StringUtils.right(linea, 2), StringUtils.left(linea, 14));
		}
		log.debug("results: "+mhm);
		return mhm;
	}
	
	private int calculoNumBloques(int numApuestas,int modulo){
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
	private int[] modulusBloque(int numBets){
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
	
}
