package org.alterq.mvc;

import org.alterq.repo.MemberDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/quiniela")
public class QuinielaController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MemberDao memberDao;

	@RequestMapping(method = RequestMethod.GET)
	public String initPage(Model model) {
		log.debug("init quiniela.jsp");
		return "quiniela";
	}

}
