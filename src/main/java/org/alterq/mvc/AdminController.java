package org.alterq.mvc;


import java.util.Date;
import java.util.List;

import org.alterq.domain.Bet;
import org.alterq.domain.GeneralData;
import org.alterq.domain.RoundBets;
import org.alterq.domain.UserAlterQ;
import org.alterq.exception.SecurityException;
import org.alterq.repo.GeneralDataDao;
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
		
		//CLOSING PROCESS STEPS
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
		RoundBets bean= new RoundBets();
		bean.setCompany(company);
		bean.setRound(round);
		bean.setSeason(season);
		roundBetDao.add(bean);
		
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
			float jackpot = (float)(numBets * DEF_QUINIELA_BET_PRICE - price);
			
			//STEP 4.4 - Update RoundData
			RoundBets rBets = roundBetDao.findAllBets(season, round);
			rBets.setJackpot(jackpot);
			rBets.setPrice(price);
			roundBetDao.update(rBets);

			//STEP 4.5 - Calc Final Quiniela
			calcFinalQuiniela(season, round, doubles, triples, rBets.getBets());
		
		log.debug("closeRound: end");
		return generalData;
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
		triples = ((numBets>3)?(numBets / 3):0);
		doubles = (((numBets - (triples*3))>2)?((numBets - (triples*3)) / 2):0);
		
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
	private static String calcFinalQuiniela(int season, int round, int doubles, int triples, List<Bet> lBets){
		double [][]count1X2 = new double[15][3];
		
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
			
			//Select Max Values & set Quiniela Final
			//...
			
		}
		return null;
	}
	
	private static double calcUserWeight(String user){
		
		return 1.0;
	}
}
