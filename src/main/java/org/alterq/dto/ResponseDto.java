package org.alterq.dto;

import org.alterq.domain.GeneralData;
import org.alterq.domain.Round;
import org.alterq.domain.RoundBets;
import org.alterq.domain.RoundRanking;
import org.alterq.domain.UserAlterQ;

public class ResponseDto {
	private UserAlterQ userAlterQ;
	private ErrorDto errorDto;
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

	public ErrorDto getErrorDto() {
		return errorDto;
	}

	public void setErrorDto(ErrorDto errorDto) {
		this.errorDto = errorDto;
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
