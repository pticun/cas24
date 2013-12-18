package org.alterq.mvc;

import org.alterq.dto.ResponseDto;
import org.alterq.repo.SessionAlterQDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = { "/", "/index" })
public class IndexController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SessionAlterQDao sessionDao;

	@RequestMapping(method = RequestMethod.GET)
	public String initPage() {
		log.debug("init index.jsp");
		return "index";
	}
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public @ResponseBody
	ResponseDto logout(@CookieValue(value = "session", defaultValue = "") String cookieSession) {
		log.debug("init LoginController.logout");
		log.debug("session:" + cookieSession);
		sessionDao.endSession(cookieSession);
		ResponseDto dto = new ResponseDto();
		return dto;
	}

}
