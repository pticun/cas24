package org.alterq.mvc;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.alterq.domain.Bet;
import org.alterq.domain.Game;
import org.alterq.domain.GeneralData;
import org.alterq.domain.Prize;
import org.alterq.domain.PrizesRound;
import org.alterq.domain.Ranking;
import org.alterq.domain.Round;
import org.alterq.domain.RoundBets;
import org.alterq.domain.UserAlterQ;
import org.alterq.dto.AlterQConstants;
import org.alterq.dto.ResponseDto;
import org.alterq.exception.SecurityException;
import org.alterq.repo.GeneralDataDao;
import org.alterq.repo.RoundDao;
import org.alterq.repo.RoundRankingDao;
import org.alterq.repo.SessionAlterQDao;
import org.alterq.repo.RoundBetDao;
import org.alterq.repo.UserAlterQDao;
import org.alterq.security.UserAlterQSecurity;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
    		int numPosibilidades = (superior + 1) - inferior;
		    double aleat = Math.random() * numPosibilidades;
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
		case 0: return 1;
		case 1: return 9;
		case 2: return 24;
		default: return 1;
		}
	}
	private static void calcFinalDoublesAndTriples(int type)
	{
		switch(type){
		case 1: //reduction 4 triples
			triples+=4;
			break;
		case 2: //reduction 3 triples and 3 dobles
			triples+=3;
			doubles+=3;
			break;
			
		default:
		}
	}
	/**
	 * Function calcFinalQuiniela that return the final quiniela
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
	 * ResultBet Signs:
	 * 	1   = 100 = 4
	 * 	 X  = 010 = 2
	 *    2 = 001 = 1
	 **/  	
	private String translateResult1x2 (String apu)
	{
		String rdo = "";
	
		for (int i = 0; i<apu.length(); i++){
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
		
		int rdo, doses, equis, unos;
		int [] salida = new int[4];
		salida[0]=salida[1]=salida[2]= salida[3]= -1;
		
		rdo = 0;
		doses = 0;
		equis = 0;
		unos = 0;
		
		int singBet;
		int singRes;
		
		try{
			//Translate 1X2 to 421
			
		for (int i = 0; i<apu.length(); i++){
			singBet = Integer.parseInt(apu.substring(i, i+1));
			singRes = Integer.parseInt(resultBet.substring(i, i+1));		
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
					equis++;
				}
				break;
			case 1: //sign 2
				if ((singBet == 1) || (singBet == 3) || (singBet == 5) || (singBet == 7)){
					rdo++;
					doses++;
				}
				break;
			default: //something wrong
				break;
			}
		}
		} catch (Exception e){
			rdo = 0;
			doses = 0;
			equis = 0;
			unos = 0;
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
		
		return salida;
	}
	
	private void updateUserWeight(UserAlterQ user, int rightSigns){
		double oldWeight = user.getWeight();
		
		user.setWeight(oldWeight+rightSigns);
		
		userAlterQDao.save(user);
		
		return;
	}
	
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
	
	private void updateGlobalRanking(int company, int season, UserAlterQ user, int points, int ones, int equs, int twos){
		Ranking rnk = new Ranking();
		rnk.setCompany(company);
		rnk.setOnes(ones);
		rnk.setEqus(equs);
		rnk.setTwos(twos);
		rnk.setPoints(points);
		rnk.setUser(user);

		roundRankingDao.addRankingGlobal(company, season, rnk);
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
			
			int[] vMaxAciertos = {0,0,0,0};
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
					bUpdate = false;
				}
				
				
				//Update iter user
				if (bUpdate)
				{
					//STEP 3: update users weight
					updateUserWeight(userAlterQ, vMaxAciertos[0]);
					//STEP 4: update round ranking
					updateRoundRanking(company, season, round, lastUserAlterQ, vMaxAciertos[0], vMaxAciertos[3], vMaxAciertos[2], vMaxAciertos[1]);
					//SETP 5: update global ranking
					updateGlobalRanking(company, season, lastUserAlterQ, vMaxAciertos[0], vMaxAciertos[3], vMaxAciertos[2], vMaxAciertos[1]);
					
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
				//SETP 5: update global ranking
				updateGlobalRanking(company, season, lastUserAlterQ, vMaxAciertos[0], vMaxAciertos[3], vMaxAciertos[2], vMaxAciertos[1]);
			}
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			response.addErrorDto("AdminController:addMatches", "SecurityException");
			e.printStackTrace();
		}
		
		return response;
	}
	
	
//	@RequestMapping(method = RequestMethod.POST, produces = "application/json", value = "/company/{company}/season/{season}/round/{round}/prize15/{count15}/{amount15}/prize14/{count14}/{amount14}/prize13/{count13}/{amount13}/prize12/{count12}/{amount12}/prize11/{count11}/{amount11}/prize10/{count10}/{amount10}")
//	public @ResponseBody 
//	ResponseDto  prizesRound(@PathVariable int company, @PathVariable int season, @PathVariable int round, @PathVariable int count15, @PathVariable float amount15, @PathVariable int count14, @PathVariable float amount14, @PathVariable int count13, @PathVariable float amount13, @PathVariable int count12, @PathVariable float amount12, @PathVariable int count11, @PathVariable float amount11, @PathVariable int count10, @PathVariable float amount10) {
	@RequestMapping(method = RequestMethod.POST, produces = "application/json", value = "/company/{company}/season/{season}/round/{round}/prizesBet")
	public @ResponseBody ResponseDto prizesRound(@CookieValue(value = "session", defaultValue = "") String cookieSession,@RequestBody PrizesRound prizesRound) {
		ResponseDto response = new ResponseDto();
		RoundBets roundBets;
		int numBets;
		double rewardGlobal;
		double betReward = 0;
		UserAlterQ userAlterQ;
		
		try {
			userSecurity.isAdminUserInSession( cookieSession);
			roundBets = roundBetDao.findAllBets(prizesRound.getSeason(), prizesRound.getRound());
			
			/*roundBets.setHit10(count10);
			roundBets.setReward10(amount10);
			
			roundBets.setHit11(count11);
			roundBets.setReward11(amount11);

			roundBets.setHit12(count12);
			roundBets.setReward12(amount12);

			roundBets.setHit13(count13);
			roundBets.setReward13(amount13);

			roundBets.setHit14(count14);
			roundBets.setReward14(amount14);

			roundBets.setHit15(count15);
			roundBets.setReward15(amount15);
			*/
			roundBets.setPrizes(prizesRound.getPrizes());
			
			
			numBets = roundBets.getBets().size();
			//rewardGlobal = roundBets.getJackpot() + count15*amount15 + count14*amount14 + count13*amount13 + count12*amount12 + count11*amount11 + count10*amount10;
			rewardGlobal = roundBets.getJackpot();
			List<Prize> lPrizes = roundBets.getPrizes();
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
		}

		
		
		return response;
	}
}
