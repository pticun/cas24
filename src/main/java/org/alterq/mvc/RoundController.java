package org.alterq.mvc;

import org.alterq.domain.GeneralData;
import org.alterq.domain.Round;
import org.alterq.dto.ErrorDto;
import org.alterq.dto.AlterQConstants;
import org.alterq.dto.ResponseDto;
import org.alterq.repo.GeneralDataDao;
import org.alterq.repo.RoundBetDao;
import org.alterq.repo.RoundDao;
import org.alterq.repo.SessionAlterQDao;
import org.alterq.repo.UserAlterQDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/myaccount/")
public class RoundController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private RoundDao roundDao;
	@Autowired
	private RoundBetDao betDao;
	@Autowired
	private UserAlterQDao userDao;
	@Autowired
	private SessionAlterQDao sessionDao;
	@Autowired
	private GeneralDataDao generalDataDao;

	// TODO get company from user, session .....
	int company = 1;

	@RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "{id:.+}/season/{season}/round/{round}")
	public @ResponseBody
	ResponseDto getRound(@PathVariable(value = "id") String id,@PathVariable(value = "season") int season, @PathVariable(value = "round") int round) {
		ResponseDto dto = new ResponseDto();
		Round j = new Round();
		try {
		    //TODO control security by id user
		    //TODO control security by id-company
			if(round==-1){
				GeneralData generalData = generalDataDao.findByCompany(company);
				round=generalData.getRound();
			}
			// TODO create a new Service layer
			j = roundDao.findBySeasonRound(season, round);
		} catch (Exception e) {
			ErrorDto error = new ErrorDto();
			error.setIdError(AlterQConstants.GET_LAST_ROUND);
			error.setStringError("getRound (i18n error)");
			dto.addErrorDto(error);
			dto.setRound(null);
		}
		dto.setRound(j);
		return dto;
	}

}
