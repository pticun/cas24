package org.alterq.mvc;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.alterq.domain.Bet;
import org.alterq.domain.Game;
import org.alterq.domain.GeneralData;
import org.alterq.domain.Ranking;
import org.alterq.domain.Round;
import org.alterq.domain.RoundBets;
import org.alterq.domain.UserAlterQ;
import org.alterq.dto.ResponseDto;
import org.alterq.exception.SecurityException;
import org.alterq.repo.GeneralDataDao;
import org.alterq.repo.RoundDao;
import org.alterq.repo.RoundRankingDao;
import org.alterq.repo.SessionAlterQDao;
import org.alterq.repo.RoundBetDao;
import org.alterq.repo.UserAlterQDao;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mongodb.DBObject;

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
	
	
	private static int doubles = 0;
	private static int triples = 0;

	private static final float DEF_QUINIELA_BET_PRICE = (float)0.5;

	@RequestMapping(method = RequestMethod.GET)
	public String initPage() {
		log.debug("init admin.jsp");
		return "admin";
	}
	
	@RequestMapping(method = RequestMethod.POST, produces = "application/json", value = "/company/{company}/season/{season}/round/{round}/open")
	public @ResponseBody 
	GeneralData openRound(@PathVariable int company, @PathVariable int season, @PathVariable int round) {
		GeneralData generalData = null;
		
		log.debug("openRound: start");
		generalData = dao.findByCompany(company);
		
		//OPENING PROCESS STEPS
		//---------------------

		//STEP 1: update generalData
		
		//if exist, update active=true
		if (generalData != null){
			log.debug("openRound: active=true");
			generalData.setActive(true);
			dao.update(generalData);
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
		
		log.debug("openRound: end");
		return generalData;
	}
	
	@RequestMapping(method = RequestMethod.POST, produces = "application/json", value = "/company/{company}/season/{season}/round/{round}/close")
	public @ResponseBody 
	GeneralData closeRound(@PathVariable int company, @PathVariable int season, @PathVariable int round) {
		GeneralData generalData = null;
		log.debug("closeRound: start");
		
		
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
			return null;
		}
		
		//STEP 2: Automatics Bets
		//
		//STEP 2.1 - Get Users with automatic bets
		List<UserAlterQ> lUsers = userAlterQDao.findUserWithAutomatics(company);
		//STEP 2.2 - For each user do as bets as automatic bets (It has to check user amount before make automatics bets)
		for (UserAlterQ user : lUsers){
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
			
			if (!roundBetDao.addBet(season, round, bet))
			{
				log.debug("closeRound: user("+user.getName()+") No enough money for automatic bet");
				//STEP 2.2.3.error - Send an email to the admin ("ERROR making automatic bet")
				continue;
			}
			//STEP 2.2.4 - Update User Balance
			try{
				user.setBalance(Float.toString((float)(balance - DEF_QUINIELA_BET_PRICE)));
				userAlterQDao.save(user);
				if(userAlterQDao.getLastError() != null){    
					log.debug("closeRound: user("+user.getName()+") Error updating balance.");  
					//STEP 2.2.4.error - Send an email to the admin ("ERROR updating user balance")
					continue;
				}
			} catch (Exception e){
				log.debug("closeRound: user("+user.getName()+") Error updating balance.");
				//STEP 2.2.4.error - Send an email to the admin ("ERROR updating user balance")
				continue;
			}
		} 

		
		//STEP 3: Fixed Bets (¿?)

		//STEP 4: Quiniela
			//STEP 4.1 - Calc Number Bets
			int numBets = roundBetDao.countAllBets(season, round);
			
			//STEP 4.2 - Calc Number of Doubles and Triples
			calcDoublesAndTriples(numBets);
			
			//STEP 4.3 - Calc Quiniela Price and Round Jackpot
			float price = calcQuinielaPrice(doubles, triples);
			float jackpot = ((price==0)?(float)0:(float)(numBets * DEF_QUINIELA_BET_PRICE - price));
			
			//STEP 4.4 - Update RoundData
			RoundBets rBets = roundBetDao.findAllBets(season, round);
			rBets.setJackpot(jackpot);
			rBets.setPrice(price);
			roundBetDao.update(rBets);

			//STEP 4.5 - Calc Final Quiniela
			String finalQ = calcFinalQuiniela(season, round, doubles, triples, rBets.getBets());
			
			//Add Final Bet (admin)
			//STEP 2.2.3 - Make Automatic User Bet
			Bet bet = new Bet();
			bet.setPrice((float)0.0);
			bet.setBet(finalQ);
			bet.setUser(getAdmin());
			bet.setCompany(company);
			bet.setDateCreated(new Date());
			bet.setDateUpdated(new Date());
			bet.setId(new ObjectId().toStringMongod());
			roundBetDao.addBet(season, round, bet);

		
		log.debug("closeRound: end");
		return generalData;
	}
	
	/**
	 * 
	 * */
	private static String getAdmin()
	{
		return "admin";
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
				solucion = solucion+"2";
			}
			else if (aleat>1){
				solucion = solucion+"X";
			}
			else{
				solucion = solucion+"1";
			}
		}
		return solucion;
		
	}
	
	private static void calcDoublesAndTriples(int numBets){
		triples = (int) ((numBets<3)?0:(Math.log(numBets)/Math.log(3)));
		doubles = (int) (((numBets - Math.pow(3, triples))<2)?0:(Math.log((int)(numBets / Math.pow(3, triples)))/Math.log(2)));
		
	}
	
	private static float calcQuinielaPrice(int doubles, int triples){
		return new Double(DEF_QUINIELA_BET_PRICE * Math.pow(2, doubles) * Math.pow(3, triples)).floatValue();
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
			for(int j=0; j<15; j++){
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
			for(int j=0; j<15; j++){
				for (int k=0; k<3; k++)
				{
					if(count1X2[j][k]>max)
					{
						max = count1X2[j][k];
						aX = j;
					}
				}
			}
			count1X2[aX][0] = -1;
			count1X2[aX][1] = -1;
			count1X2[aX][2] = -1;
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

	@RequestMapping(method = RequestMethod.POST, produces = "application/json", value = "/company/{company}/season/{season}/round/{round}/resultBet/{resultBet}")
	public @ResponseBody 
	ResponseDto  resutlBetRound(@PathVariable int company, @PathVariable int season, @PathVariable int round, @PathVariable String resultBet) {
		ResponseDto dto = new ResponseDto();
		UserAlterQ userAlterQ;

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
			if(user.equals("admin"))
			{
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
		
		return dto;
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
	
	@RequestMapping(method = RequestMethod.POST, produces = "application/json", value = "/company/{company}/season/{season}/round/{round}/prize15/{count15}/{amount15}/prize14/{count14}/{amount14}/prize13/{count13}/{amount13}/prize12/{count12}/{amount12}/prize11/{count11}/{amount11}/prize10/{count10}/{amount10}")
	public @ResponseBody 
	ResponseDto  prizesRound(@PathVariable int company, @PathVariable int season, @PathVariable int round, @PathVariable int count15, @PathVariable float amount15, @PathVariable int count14, @PathVariable float amount14, @PathVariable int count13, @PathVariable float amount13, @PathVariable int count12, @PathVariable float amount12, @PathVariable int count11, @PathVariable float amount11, @PathVariable int count10, @PathVariable float amount10) {
		ResponseDto dto = new ResponseDto();
		RoundBets roundBets;
		int numBets;
		double rewardGlobal;
		double betReward = 0;
		UserAlterQ userAlterQ;
		
		
		roundBets = roundBetDao.findAllBets(season, round);
		
		roundBets.setHit10(count10);
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
		
		numBets = roundBets.getBets().size();
		rewardGlobal = roundBets.getJackpot() + count15*amount15 + count14*amount14 + count13*amount13 + count12*amount12 + count11*amount11 + count10*amount10; 
		
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
		
		return dto;
	}
	
	@RequestMapping(method = RequestMethod.POST, produces = "application/json", value = "/company/{company}/season/{season}/round/{round}/{local01}/{visitor01}/{local02}/{visitor02}/{local03}/{visitor03}/{local04}/{visitor04}/{local05}/{visitor05}/{local06}/{visitor06}/{local07}/{visitor07}/{local08}/{visitor08}/{local09}/{visitor09}/{local10}/{visitor10}/{local11}/{visitor11}/{local12}/{visitor12}/{local13}/{visitor13}/{local14}/{visitor14}/{local15}/{visitor15}")
	public @ResponseBody 
	ResponseDto  addRoundGames(@PathVariable int company, @PathVariable int season, @PathVariable int round, @PathVariable String local01, @PathVariable String visitor01, @PathVariable String local02, @PathVariable String visitor02, @PathVariable String local03, @PathVariable String visitor03, @PathVariable String local04, @PathVariable String visitor04, @PathVariable String local05, @PathVariable String visitor05, @PathVariable String local06, @PathVariable String visitor06, @PathVariable String local07, @PathVariable String visitor07, @PathVariable String local08, @PathVariable String visitor08, @PathVariable String local09, @PathVariable String visitor09, @PathVariable String local10, @PathVariable String visitor10, @PathVariable String local11, @PathVariable String visitor11, @PathVariable String local12, @PathVariable String visitor12, @PathVariable String local13, @PathVariable String visitor13, @PathVariable String local14, @PathVariable String visitor14, @PathVariable String local15, @PathVariable String visitor15) {
		ResponseDto dto = new ResponseDto();
		Round myRound = new Round();
		List<Game> lGames = new ArrayList<Game>();
		
		myRound.setCompany(company);
		myRound.setSeason(season);
		myRound.setRound(round);
		
		Game game01 = new Game();
		game01.setId(1);
		game01.setPlayer1(local01);
		game01.setPlayer2(visitor01);
		lGames.add(game01);
		
		Game game02 = new Game();
		game02.setId(2);
		game02.setPlayer1(local02);
		game02.setPlayer2(visitor02);
		lGames.add(game02);

		Game game03 = new Game();
		game03.setId(3);
		game03.setPlayer1(local03);
		game03.setPlayer2(visitor03);
		lGames.add(game03);

		Game game04 = new Game();
		game04.setId(4);
		game04.setPlayer1(local04);
		game04.setPlayer2(visitor04);
		lGames.add(game04);

		Game game05 = new Game();
		game05.setId(5);
		game05.setPlayer1(local05);
		game05.setPlayer2(visitor05);
		lGames.add(game05);

		Game game06 = new Game();
		game06.setId(6);
		game06.setPlayer1(local06);
		game06.setPlayer2(visitor06);
		lGames.add(game06);

		Game game07 = new Game();
		game07.setId(7);
		game07.setPlayer1(local07);
		game07.setPlayer2(visitor07);
		lGames.add(game07);

		Game game08 = new Game();
		game08.setId(8);
		game08.setPlayer1(local08);
		game08.setPlayer2(visitor08);
		lGames.add(game08);

		Game game09 = new Game();
		game09.setId(9);
		game09.setPlayer1(local09);
		game09.setPlayer2(visitor09);
		lGames.add(game09);

		Game game10 = new Game();
		game10.setId(10);
		game10.setPlayer1(local10);
		game10.setPlayer2(visitor10);
		lGames.add(game10);

		Game game11 = new Game();
		game11.setId(11);
		game11.setPlayer1(local11);
		game11.setPlayer2(visitor11);
		lGames.add(game11);

		Game game12 = new Game();
		game12.setId(12);
		game12.setPlayer1(local12);
		game12.setPlayer2(visitor12);
		lGames.add(game12);

		Game game13 = new Game();
		game13.setId(2);
		game13.setPlayer1(local13);
		game13.setPlayer2(visitor13);
		lGames.add(game13);

		Game game14 = new Game();
		game14.setId(14);
		game14.setPlayer1(local14);
		game14.setPlayer2(visitor14);
		lGames.add(game14);

		Game game15 = new Game();
		game15.setId(15);
		game15.setPlayer1(local15);
		game15.setPlayer2(visitor15);
		lGames.add(game15);

		myRound.setGames(lGames);
		
		roundDao.addRound(myRound);
		
		return dto;
	}
}
