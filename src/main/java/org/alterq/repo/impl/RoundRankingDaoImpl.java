package org.alterq.repo.impl;

import org.alterq.domain.Ranking;
import org.alterq.domain.RoundRanking;
import org.alterq.repo.MongoCollection;
import org.alterq.repo.RoundRankingDao;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class RoundRankingDaoImpl extends MongoCollection implements RoundRankingDao {
	public static final String COLLECTION_NAME = "roundRanking";

	public RoundRanking findRanking(int company, int season, int round) {
		Query query = new Query(Criteria.where("company").is(company).and("season").is(season).and("round").is(round));
		query.fields().include("rankings.user._id");
		query.fields().include("rankings.user.name");
		RoundRanking aux = mongoTemplate.findOne(query, RoundRanking.class, COLLECTION_NAME);
		return aux;
	}

	public RoundRankingDaoImpl() {
		super.COLLECTION_NAME = COLLECTION_NAME;
	}

	public boolean addRanking(int company, int season, int round, Ranking ranking) {
		Query query = new Query();
		query.addCriteria(Criteria.where("company").is(company).and("season").is(season).and("round").is(round));

		Update update = new Update();
		update.push("rankings", ranking);

		mongoTemplate.upsert(query, update, RoundRanking.class);
		return true;
	}

	public boolean deleteRanking(int company, int season, int round) {
		Query query = new Query();
		query.addCriteria(Criteria.where("company").is(company).and("season").is(season).and("round").is(round));
		mongoTemplate.remove(query, RoundRanking.class);
		return true;
	}

	@Override
	public void add(RoundRanking bean) {
		mongoTemplate.insert(bean, COLLECTION_NAME);
	}

	public void update(RoundRanking roundRanking) {

	}
}
