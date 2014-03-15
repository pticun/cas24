package org.alterq.mvc;


import java.util.List;

import org.alterq.domain.GeneralData;
import org.alterq.repo.GeneralDataDao;
import org.alterq.repo.SessionAlterQDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
		//if exist, update active=true
		if (generalData != null){
			log.debug("openRound: active=true");
			generalData.setActive(true);
			dao.update(generalData);
		}
		else{//create a new generalData
			log.debug("openRound: new generalData active=true");
			generalData = new GeneralData();
			generalData.setActive(true);
			generalData.setCompany(company);
			generalData.setRound(round);
			generalData.setSeason(season);
			
			dao.add(generalData);
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
		//---------------------
		
		//STEP 1: if exist, update active=false
		if (generalData != null){
			log.debug("openRound: active=true");
			generalData.setActive(false);
			dao.update(generalData);
		}
		else{ //there is not round to close
			return null;
		}
		
		//STEP 2: Automatics Bets
			//STEP 2.1 - Get Users with automatic bets
			//STEP 2.2 - For each user do as bets as automatic bets (It has to check user amount before make automatics bets)
				//STEP 2.2.1 - Check User Balance
				//STEP 2.2.1 - Calc RandomBet
				//STEP 2.2.2 - Make Automatic User Bet
		
		//STEP 3: Fixed Bets (¿?)
		
		//STEP 4: Quiniela
			//STEP 4.1 - Calc Number Bets
			//STEP 4.2 - Calc Number of Doubles and Triples
			//STEP 4.3 - Calc Quiniela Price and Round Jackpot 
			//STEP 4.4 - Update RoundData
			//STEP 4.5 - Calc Final Quiniela
		
		
		log.debug("closeRound: end");
		return generalData;
	}
}
