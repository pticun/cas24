package org.alterq.repo;

import java.util.List;

import org.alterq.domain.Account;


public interface AccountingDao {
	public List<Account> findAll();

	public List<Account> findAccounts(int season);

	public List<Account> findAccounts(int season, int round);

	public List<Account> findUserAccounts(String user);

	public List<Account> findUserAccounts(String user, int season);

	public List<Account> findUserAccounts(String user, int season, int round);
	
	public void add(Account bean);

	public boolean deleteAccounts(int season);

	public boolean deleteAccounts(int season, int round);
	
	public boolean deleteUserAccounts(String user);

	public boolean deleteUserAccounts(String user, int season);

	public boolean deleteUserAccounts(String user, int season, int round);

	public void update(Account account);
}