package org.alterq.dto;

import java.util.ArrayList;
import java.util.List;

import org.alterq.domain.GeneralData;
import org.alterq.domain.Round;
import org.alterq.domain.RoundBets;
import org.alterq.domain.RoundRanking;
import org.alterq.domain.UserAlterQ;

public class ResponseDto {
	private UserAlterQ userAlterQ;
	private List<ErrorDto> errorDto = new ArrayList<ErrorDto>();
	private Round round;
	private RoundBets roundBet;
	private GeneralData generalData;
	private RoundRanking roundRanking;

	public UserAlterQ getUserAlterQ() {
		return userAlterQ;
	}

	public void setUserAlterQ(UserAlterQ userAlterQ) {
		this.userAlterQ = userAlterQ;
	}

	public List<ErrorDto> getErrorDto() {
		return errorDto;
	}

	public void addErrorDto(ErrorDto errorDto) {
		this.errorDto.add(errorDto);
	}

	public void addErrorDto(String path, String message) {
		ErrorDto error = new ErrorDto(path, message);
		errorDto.add(error);
	}

	public Round getRound() {
		return round;
	}

	public void setRound(Round jornada) {
		this.round = jornada;
	}

	public RoundBets getRoundBet() {
		return roundBet;
	}

	public void setRoundBet(RoundBets roundBet) {
		this.roundBet = roundBet;
	}

	public GeneralData getGeneralData() {
		return generalData;
	}

	public void setGeneralData(GeneralData generalData) {
		this.generalData = generalData;
	}

	public RoundRanking getRoundRanking() {
		return roundRanking;
	}

	public void setRoundRanking(RoundRanking roundRanking) {
		this.roundRanking = roundRanking;
	}

}
