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
	private GeneralDataDao dao;

	@RequestMapping(method = RequestMethod.GET)
	public String initPage() {
		log.debug("init admin.jsp");
		return "admin";
	}
	
	@RequestMapping(method = RequestMethod.POST, produces = "application/json", value = "{company, round, season}")
	public @ResponseBody 
	void openRound(@PathVariable int company, @PathVariable int round, @PathVariable int season) {
		GeneralData generalData = new GeneralData();
		generalData.setActive(true);
		generalData.setCompany(company);
		generalData.setRound(round);
		generalData.setSeason(season);
		
		log.debug("openRound");
		//if exist, update active=true
		dao.update(generalData);
		//return the actual round/session
		return;
	}

	
}
