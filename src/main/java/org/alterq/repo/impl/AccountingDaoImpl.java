package org.alterq.repo.impl;

import java.util.List;

import org.alterq.domain.Account;
import org.alterq.domain.RoundBets;
import org.alterq.repo.AccountingDao;
import org.alterq.repo.MongoCollection;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class AccountingDaoImpl extends MongoCollection implements AccountingDao {
	public static final String COLLECTION_NAME = "accounting";

	public AccountingDaoImpl() {
		super.COLLECTION_NAME = COLLECTION_NAME;
	}

	@Override
	public List<Account> findAll() {
		return mongoTemplate.findAll(Account.class, COLLECTION_NAME);
	}

	@Override
	public List<Account> findAccounts(int season){
		Query query = new Query(Criteria.where("season").is(season));
		List<Account> listAccount = mongoTemplate.find(query, Account.class, COLLECTION_NAME);
		return listAccount;
	}

	@Override
	public List<Account> findAccounts(int season, int round){
		Query query = new Query(Criteria.where("season").is(season).and("round").is(round));
		List<Account> listAccount = mongoTemplate.find(query, Account.class, COLLECTION_NAME);
		return listAccount;
	}

	@Override
	public List<Account> findUserAccounts(String user){
		Query query = new Query(Criteria.where("userID").is(user));
		List<Account> listAccount = mongoTemplate.find(query, Account.class, COLLECTION_NAME);
		return listAccount;
	}

	@Override
	public List<Account> findUserAccounts(String user, int season){
		Query query = new Query(Criteria.where("userID").is(user).and("season").is(season));
		List<Account> listAccount = mongoTemplate.find(query, Account.class, COLLECTION_NAME);
		return listAccount;
	}

	@Override
	public List<Account> findUserAccounts(String user, int season, int round){
		Query query = new Query(Criteria.where("userID").is(user).and("season").is(season).and("round").is(round));
		List<Account> listAccount = mongoTemplate.find(query, Account.class, COLLECTION_NAME);
		return listAccount;
	}

	@Override
	public void add(Account account) {
		mongoTemplate.insert(account, COLLECTION_NAME);
	}

	@Override
	public void update(Account account) {
		mongoTemplate.save(account, COLLECTION_NAME);
	}
	public boolean deleteAllBets(int season, int round){
		Query query = new Query();
		query.addCriteria(Criteria.where("season").is(season).and("round").is(round));
		mongoTemplate.remove(query, RoundBets.class);
		return true;
	}
	@Override
	public boolean deleteAccounts(int season){
		Query query = new Query(Criteria.where("season").is(season));
		mongoTemplate.remove(query, RoundBets.class);
		return true;
	}

	@Override
	public boolean deleteAccounts(int season, int round){
		Query query = new Query(Criteria.where("season").is(season).and("round").is(round));
		mongoTemplate.remove(query, RoundBets.class);
		return true;
		
	}
	
	@Override
	public boolean deleteUserAccounts(String user){
		Query query = new Query(Criteria.where("userID").is(user));
		mongoTemplate.remove(query, RoundBets.class);
		return true;
	}

	@Override
	public boolean deleteUserAccounts(String user, int season){
		Query query = new Query(Criteria.where("userID").is(user).and("season").is(season));
		mongoTemplate.remove(query, RoundBets.class);
		return true;
	}

	@Override
	public boolean deleteUserAccounts(String user, int season, int round){
		Query query = new Query(Criteria.where("userID").is(user).and("season").is(season).and("round").is(round));
		mongoTemplate.remove(query, RoundBets.class);
		return true;
		
	}
	
}
