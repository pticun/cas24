package org.alterq.mvc;


import java.util.Date;
import java.util.List;

import org.alterq.domain.Bet;
import org.alterq.domain.GeneralData;
import org.alterq.domain.Ranking;
import org.alterq.domain.RoundBets;
import org.alterq.domain.UserAlterQ;
import org.alterq.dto.ResponseDto;
import org.alterq.exception.SecurityException;
import org.alterq.repo.GeneralDataDao;
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
		List<Bet> lBets = bean.getBets();
		for (Bet bet : lBets){
			String apu = bet.getBet();
			String user = bet.getUser();
			userAlterQ = userAlterQDao.findById(user);
			
			if (userAlterQ== null){
				log.debug("closeRound: user("+user+") Error resultBet user not find");  
				//STEP 1.1.error - Send an email to the admin ("ERROR resultBet user not find")
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
					lastUser = user;
					lastUserAlterQ = userAlterQ;
					vMaxAciertos[0] = vAciertos[0];
					vMaxAciertos[1] = vAciertos[1];
					vMaxAciertos[2] = vAciertos[2];
					vMaxAciertos[3] = vAciertos[3];
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
	
	@RequestMapping(method = RequestMethod.POST, produces = "application/json", value = "/company/{company}/season/{season}/round/{round}/price15/{count15}/{amount15}/price14/{count14}/{amount14}/price13/{count13}/{amount13}/price12/{count12}/{amount12}/price11/{count11}/{amount11}/price10/{count10}/{amount10}")
	public @ResponseBody 
	ResponseDto  pricesRound(@PathVariable int company, @PathVariable int season, @PathVariable int round, @PathVariable int count15, @PathVariable double amount15, @PathVariable int count14, @PathVariable double amount14, @PathVariable int count13, @PathVariable double amount13, @PathVariable int count12, @PathVariable double amount12, @PathVariable int count11, @PathVariable double amount11, @PathVariable int count10, @PathVariable double amount10) {
		ResponseDto dto = new ResponseDto();
		RoundBets roundBets;
		
		return dto;
	}
	
}
