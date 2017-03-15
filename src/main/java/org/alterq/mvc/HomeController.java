package org.alterq.mvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.alterq.domain.Bet;
import org.alterq.domain.RoundBets;
import org.alterq.dto.MailQueueDto;
import org.alterq.dto.ResponseDto;
import org.alterq.repo.SessionAlterQDao;
import org.alterq.util.MailTools;
import org.alterq.util.enumeration.BetTypeEnum;
import org.alterq.util.enumeration.QueueMailEnum;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.arch.core.channel.ProcessMailQueue;
import org.arch.core.util.CoreUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(value = {  "/", "/home" })
public class HomeController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SessionAlterQDao sessionDao;

	@Autowired
	MailTools mailTools;
	
	@Autowired
	ProcessMailQueue processMailQueue;
	
	@RequestMapping(method = RequestMethod.GET)
	public String initPage() {
		log.debug("init home.jsp");
		return "home";
	}
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public @ResponseBody
	ResponseDto logout(@CookieValue(value = "session", defaultValue = "") String cookieSession, HttpServletResponse response) {
		log.debug("init LoginController.logout");
		log.debug("session:" + cookieSession);
		sessionDao.endSession(cookieSession);
		Cookie sessionCookie=new Cookie("session","");
		sessionCookie.setMaxAge(0);
		sessionCookie.setPath("/quinimobile");
		response.addCookie(sessionCookie);
		ResponseDto dto = new ResponseDto();
		return dto;
	}

	@RequestMapping(value = "contact/{name}/{email}/{message}", method = RequestMethod.POST)
	public @ResponseBody
	ResponseDto contact(@PathVariable String name, @PathVariable String email, @PathVariable String message, @CookieValue(value = "session", defaultValue = "") String cookieSession, HttpServletRequest request) {
		log.debug("init LoginController.contact");
		
		String ccoMail = "quinilagold@gmail.com";
		
		MailQueueDto mailDto=new MailQueueDto();
		mailDto.setType(QueueMailEnum.Q_CONTACTMAIL);
		
		
		mailDto.setContactEmail(email);
		mailDto.setContactName(name);
		mailDto.setContactMenssage(message);
		
		
		if(!StringUtils.contains(CoreUtils.getCurrentHostName(),"pro")){
			//para pruebas
			ccoMail = "quinielagold@gmail.com";
		}

		log.debug("ccoMail="+ccoMail);
		mailDto.setCco(ccoMail);
		
		processMailQueue.process(mailDto);
		
		
		ResponseDto dto = new ResponseDto();
		return dto;
	}
}
