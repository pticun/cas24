package org.alterq.mvc;

import org.alterq.domain.Jornada;
import org.alterq.dto.ErrorDto;
import org.alterq.dto.ResponseDto;
import org.alterq.repo.JornadaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/bet")
public class BetController {
	@Autowired
	private JornadaDao jornadaDao;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	ResponseDto getLastJornada() {
		ResponseDto dto = new ResponseDto();
		Jornada j=new Jornada();
		try {
			j=jornadaDao.findLastJornada();
		} catch (Exception e) {
			ErrorDto error = new ErrorDto();
			error.setIdError("10");
			error.setStringError("getLastJornada (i18n error)");
			dto.setErrorDto(error);
			dto.setJornada(null);
		}
		dto.setJornada(j);
		return dto;
	}

}
