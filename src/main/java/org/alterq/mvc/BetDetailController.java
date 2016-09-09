package org.alterq.mvc;


import javax.servlet.http.HttpServletResponse;

import org.alterq.domain.AdminData;
import org.alterq.dto.AlterQConstants;
import org.alterq.repo.AdminDataDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/betDetail")
public class BetDetailController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private AdminDataDao adminDataDao;


	@RequestMapping(method = RequestMethod.GET, value = "/{company}/{sCompany}/{bet}/{typeReduction}/{reduction}")
	public 
	ModelAndView getBetDetail(HttpServletResponse response, @PathVariable String company,@PathVariable String sCompany,@PathVariable String bet, @PathVariable String typeReduction, @PathVariable String reduction) {
		AdminData adminData = null;
		
		log.debug("getBetDetail init");
		log.debug("getBetDetail company=" + company);
		log.debug("getBetDetail sCompany=" + sCompany);
		log.debug("getBetDetail bet=" + bet);
		log.debug("getBetDetail typeReduction=" + typeReduction);
		log.debug("getBetDetail reduction=" + reduction);
		
		adminData = adminDataDao.findById(AlterQConstants.DEFECT_ADMINDATA);
		
		//int company=adminData.getCompany();
		int season=adminData.getSeason();
		int round=adminData.getRound();
		
		log.debug("getBetDetail season=" + season);
		log.debug("getBetDetail round=" + round);
		//log.debug("getBetDetail company=" + company);

		ModelAndView model = new ModelAndView("betDetail");
		model.addObject("msg", " ");		//-----------------------------------------------------
		model.addObject("bet", bet);		
		model.addObject("typeReduction", typeReduction);		
		model.addObject("reduction", reduction);		
		model.addObject("company", company);
		model.addObject("scompany", sCompany);
		model.addObject("season", season);		
		model.addObject("round", round);		
		//hay que pasar estos datos al JSP --> betDetail.jsp
		//-----------------------------------------------------
		//bet
		//typeReduction
		//reduction
		//company
		//season
		//round
		//-----------------------------------------------------
		
		return model;
	}

}
