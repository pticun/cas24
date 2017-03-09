package org.alterq.domain;


import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class AccountingEntry {

	@Id
	private String id;
	
	private List<Account> accounts;
	private double debit;	// -
	private double credit;	// +
	private double balance;	// +
	float bets; 			// +
	float prizes;			// -
	float deposits;			// +
	float withDrawals;		// -
	float initialBalances;	// +
	float refunds;			// -
	float finalBets;		// -
	float jackpots;			// +
	
	public float getJackpots() {
		return jackpots;
	}
	public void setJackpots(float jackpots) {
		this.jackpots = jackpots;
	}
	public float getFinalBets() {
		return finalBets;
	}
	public void setFinalBets(float finalBets) {
		this.finalBets = finalBets;
	}
	public float getBets() {
		return bets;
	}
	public void setBets(float bets) {
		this.bets = bets;
	}
	public float getPrizes() {
		return prizes;
	}
	public void setPrizes(float prizes) {
		this.prizes = prizes;
	}
	public float getDeposits() {
		return deposits;
	}
	public void setDeposits(float deposits) {
		this.deposits = deposits;
	}
	public float getWithDrawals() {
		return withDrawals;
	}
	public void setWithDrawals(float withDrawals) {
		this.withDrawals = withDrawals;
	}
	public float getInitialBalances() {
		return initialBalances;
	}
	public void setInitialBalances(float initialBalances) {
		this.initialBalances = initialBalances;
	}
	public float getRefunds() {
		return refunds;
	}
	public void setRefunds(float refunds) {
		this.refunds = refunds;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public List<Account> getAccounts() {
		return accounts;
	}
	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	public double getDebit() {
		return debit;
	}
	public void setDebit(double debit) {
		this.debit = debit;
	}
	public double getCredit() {
		return credit;
	}
	public void setCredit(double credit) {
		this.credit = credit;
	}
	


}