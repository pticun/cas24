package org.alterq.repo.impl;

import org.alterq.repo.MongoCollection;
import org.alterq.domain.RoundResult;
import org.alterq.repo.RoundResultsDao;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class RoundResultDaoImpl extends MongoCollection implements RoundResultsDao {
	public static final String COLLECTION_NAME = "roundResults";

	public RoundResultDaoImpl() {
		super.COLLECTION_NAME = COLLECTION_NAME;
	}

	public RoundResult findResult(int season, int round) {
		Query query = new Query(Criteria.where("season").is(season).and("round").is(round));
		RoundResult aux = mongoTemplate.findOne(query, RoundResult.class, COLLECTION_NAME);
		return aux;
	}

	public boolean updateResult(int season, int round, String result) {
		Query query = new Query();
		query.addCriteria(Criteria.where("season").is(season).and("round").is(round));

		Update update = new Update();
		update.push("resultBet", result);

		mongoTemplate.upsert(query, update, RoundResult.class);
		return true;
	}

	public boolean deleteResult(int season, int round) {
		Query query = new Query();
		query.addCriteria(Criteria.where("season").is(season).and("round").is(round));
		mongoTemplate.remove(query, RoundResult.class);
		return true;
	}

	@Override
	public void add(RoundResult bean) {
		mongoTemplate.insert(bean, COLLECTION_NAME);
	}

	public void update(RoundResult bean) {

	}

}


