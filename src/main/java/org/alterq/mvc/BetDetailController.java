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


@Controller
@RequestMapping(value = "/betDetail")
public class BetDetailController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private AdminDataDao adminDataDao;


	@RequestMapping(method = RequestMethod.GET, value = "/{bet}/{typeReduction}/{reduction}")
	public 
	String getBetDetail(HttpServletResponse response, @PathVariable String bet, @PathVariable String typeReduction, @PathVariable String reduction) {
		AdminData adminData = null;
		
		log.debug("getBetDetail init");
		log.debug("getBetDetail bet=" + bet);
		log.debug("getBetDetail typeReduction=" + typeReduction);
		log.debug("getBetDetail reduction=" + reduction);
		
		adminData = adminDataDao.findById(AlterQConstants.DEFECT_ADMINDATA);
		
		log.debug("getBetDetail season=" + adminData.getSeason());
		log.debug("getBetDetail round=" + adminData.getRound());
		log.debug("getBetDetail company=" + adminData.getCompany());

		//-----------------------------------------------------
		//hay que pasar estos datos al JSP --> betDetail.jsp
		//-----------------------------------------------------
		//bet
		//typeReduction
		//reduction
		//company
		//season
		//round
		//-----------------------------------------------------
		
		return "betDetail";
	}

}
