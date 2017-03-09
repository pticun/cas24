package org.alterq.dto;

import java.util.ArrayList;
import java.util.List;

import org.alterq.domain.Account;
import org.alterq.domain.AccountingEntry;
import org.alterq.domain.AdminData;
import org.alterq.domain.Bet;
import org.alterq.domain.Company;
import org.alterq.domain.Round;
import org.alterq.domain.RoundBets;
import org.alterq.domain.RoundRanking;
import org.alterq.domain.UserAlterQ;

public class ResponseDto {
	private UserAlterQ userAlterQ;
	private List<ErrorDto> errorDto = new ArrayList<ErrorDto>();
	private Round round;
	private RoundBets roundBet;
	private AdminData adminData;
	private RoundRanking roundRanking;
	private Bet bet;
	private List<Company> company;
	private List<UserAlterQ> users;
	private AccountingEntry accountEntry;


	public AccountingEntry getAccountEntry() {
		return accountEntry;
	}

	public void setAccountEntry(AccountingEntry accountEntry) {
		this.accountEntry = accountEntry;
	}

	public Bet getBet() {
		return bet;
	}

	public void setBet(Bet bet) {
		this.bet = bet;
	}

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

	public void addErrorDto(List<ErrorDto> error) {
		this.errorDto = error;
	}

	public void addErrorDto(String idError, String stringError) {
		ErrorDto error = new ErrorDto(idError, stringError);
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

	public AdminData getAdminData() {
		return adminData;
	}

	public void setAdminData(AdminData adminData) {
		this.adminData = adminData;
	}

	public RoundRanking getRoundRanking() {
		return roundRanking;
	}

	public void setRoundRanking(RoundRanking roundRanking) {
		this.roundRanking = roundRanking;
	}

	public void setErrorDto(List<ErrorDto> errorDto) {
		this.errorDto = errorDto;
	}

	public List<Company> getCompany() {
		return company;
	}

	public void setCompany(List<Company> company) {
		this.company = company;
	}

	public void setCompany(Company company) {
		if (this.company == null) {
			this.company = new ArrayList<Company>();
		}
		this.company.add(company);
	}

	public List<UserAlterQ> getUsers() {
		return users;
	}

	public void setUsers(List<UserAlterQ> users) {
		this.users = users;
	}

	public void setUser(UserAlterQ users) {
		if (this.users == null) {
			this.users = new ArrayList<UserAlterQ>();
		}
		this.users.add(users);
	}
}
