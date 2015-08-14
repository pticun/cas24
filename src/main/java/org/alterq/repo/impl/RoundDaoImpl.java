package org.alterq.repo.impl;

import java.util.List;

import org.alterq.domain.Round;
import org.alterq.repo.MongoCollection;
import org.alterq.repo.RoundDao;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
//import org.springframework.data.mongodb.core.query.Order;

@Repository
public class RoundDaoImpl extends MongoCollection implements RoundDao {
	public static final String COLLECTION_NAME = "round";

	public RoundDaoImpl() {
		super.COLLECTION_NAME = COLLECTION_NAME;
	}

	public Round findBySeasonRound(int temporada, int jornada) {
		Query query = new Query(Criteria.where("season").is(temporada).and("round").is(jornada));
		return mongoTemplate.findOne(query, Round.class, COLLECTION_NAME);
	}

	public List<Round> findAllOrderedBySeason() {
		return mongoTemplate.findAll(Round.class, COLLECTION_NAME);
	}

	public void addRound(Round jornada) {
		mongoTemplate.insert(jornada, COLLECTION_NAME);
		return;
	}

	public void deleteRound(int season, int round) {
		Query query = new Query();
		query.addCriteria(Criteria.where("season").is(season).and("round").is(round));
		mongoTemplate.remove(query, Round.class);
		
		return;
	}
}
