package org.alterq.repo;

import java.util.List;

import org.alterq.domain.Round;
import org.alterq.domain.RoundBets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Order;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class RoundDaoImpl implements RoundDao {
	@Autowired
	private MongoTemplate mongoTemplate;
	public static final String COLLECTION_NAME = "round";

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

	public void deleteRound(int company, int season, int round) {
		Query query = new Query();
		query.addCriteria(Criteria.where("season").is(season).and("round").is(round));
		mongoTemplate.remove(query, Round.class);
		
		return;
	}
}
