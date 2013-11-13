package org.alterq.mvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = { "/", "/index" })
public class IndexController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(method = RequestMethod.GET)
	public String initPage() {
		log.debug("init index.jsp");
		return "index";
	}

}
