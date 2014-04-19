package org.alterq.mvc;

import org.alterq.domain.RoundRanking;
import org.alterq.dto.ErrorDto;
import org.alterq.dto.ErrorType;
import org.alterq.dto.ResponseDto;
import org.alterq.repo.RoundRankingDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/myaccount/{id:.+}/season/{season}/round/{round}/ranking")
public class RankingController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private RoundRankingDao rankingDao;

	// TODO get company from user, session .....
	int company = 1;

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	ResponseDto getRanking(@PathVariable(value = "id") String id, @PathVariable(value = "season") int season, @PathVariable(value = "round") int round) {
		ResponseDto dto = new ResponseDto();
		RoundRanking roundRanking = new RoundRanking();
		try {
			// TODO control security by id user
			// TODO control security by id-company
			roundRanking = rankingDao.findRanking(company, season, round);
		} catch (Exception e) {
			ErrorDto error = new ErrorDto();
			error.setIdError(ErrorType.GET_LAST_ROUND);
			error.setStringError("getRound (i18n error)");
			dto.setErrorDto(error);
			dto.setRound(null);
		}
		dto.setRoundRanking(roundRanking);
		return dto;
	}

}
