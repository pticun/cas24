package org.alterq.mvc;


import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value = "/betDetail")
public class BetDetailController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());


	@RequestMapping(method = RequestMethod.GET, value = "/{bet}/{typeReduction}/{reduction}")
	public 
	String getBetDetail(HttpServletResponse response) {
		log.debug("getBetDetail init");
		return "betDetail";
	}

}
