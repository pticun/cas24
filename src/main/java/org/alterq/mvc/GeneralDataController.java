package org.alterq.mvc;

import java.util.List;

import org.alterq.domain.GeneralData;
import org.alterq.repo.GeneralDataDao;
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
@RequestMapping(value = "/generalData")
public class GeneralDataController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private GeneralDataDao dao;

	@RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "{id}")
	public @ResponseBody GeneralData getGeneralDataCompany(@PathVariable int id) {
		log.debug("getGeneralData");
		return dao.findByCompany(id);
	}
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<GeneralData> getGeneralDataCompany() {
		log.debug("getGeneralData");
		return dao.findAll();
	}
	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody GeneralData createGeneralDataCompany(@RequestBody GeneralData generalData) {
		log.debug("postGeneralData");
		dao.add(generalData);
		return dao.findByCompany(generalData.getCompany());
	}
	@RequestMapping(method = RequestMethod.PUT, produces = "application/json")
	public @ResponseBody GeneralData updateGeneralDataCompany(@RequestBody GeneralData generalData) {
		log.debug("putGeneralData");
		dao.update(generalData);
		return dao.findByCompany(generalData.getCompany());
	}
	@RequestMapping(method = RequestMethod.DELETE, produces = "application/json", value = "{id}")
	public @ResponseBody GeneralData deleteGeneralDataCompany(@PathVariable int id) {
		log.debug("deleteGeneralData");
		GeneralData generalData=dao.findByCompany(id);
		dao.delete(generalData);
		return null;
	}


}
