package org.alterq.mvc;


import org.alterq.repo.SessionAlterQDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = { "/carrusel" })
public class CarruselController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SessionAlterQDao sessionDao;

	@RequestMapping(method = RequestMethod.GET)
	public String initPage() {
		log.debug("init carrusel.jsp");
		return "carrusel";
	}
}
