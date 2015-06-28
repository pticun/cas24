package org.alterq.mvc;

import org.alterq.domain.AdminData;
import org.alterq.domain.Round;
import org.alterq.dto.AlterQConstants;
import org.alterq.dto.ErrorDto;
import org.alterq.dto.ResponseDto;
import org.alterq.repo.AdminDataDao;
import org.alterq.repo.GeneralDataDao;
import org.alterq.repo.RoundBetDao;
import org.alterq.repo.RoundDao;
import org.alterq.repo.SessionAlterQDao;
import org.alterq.repo.UserAlterQDao;
import org.alterq.util.enumeration.MessageResourcesNameEnum;
import org.arch.core.i18n.resources.MessageLocalizedResources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	@Autowired
	private AdminDataDao adminDataDao;
	@Autowired
	@Qualifier("messageLocalizedResources")
	private MessageLocalizedResources messageLocalizedResources;

	@RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "{id:.+}/season/{season}/round/{round}")
	public @ResponseBody
	ResponseDto getRound(@PathVariable(value = "id") String id,@PathVariable(value = "season") int season, @PathVariable(value = "round") int round) {
		ResponseDto dto = new ResponseDto();
		Round roundBean = new Round();
		try {
		    //TODO control security by id user
		    //TODO control security by id-company
			if(round==-1){
				AdminData adminData = adminDataDao.findByCompany(AlterQConstants.DEFECT_COMPANY);
				round=adminData.getRound();
			}
			// TODO create a new Service layer
			roundBean = roundDao.findBySeasonRound(season, round);
		} catch (Exception e) {
			ErrorDto error = new ErrorDto();
			error.setIdError(MessageResourcesNameEnum.GET_LAST_ROUND);
			error.setStringError(messageLocalizedResources.resolveLocalizedErrorMessage(MessageResourcesNameEnum.GET_LAST_ROUND));
			dto.addErrorDto(error);
			dto.setRound(null);
		}
		dto.setRound(roundBean);
		return dto;
	}

}
