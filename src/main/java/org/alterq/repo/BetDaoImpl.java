package org.alterq.repo;

import java.util.List;

import org.alterq.domain.Bet;
import org.alterq.domain.RoundBets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;


@Repository
public class BetDaoImpl implements BetDao {
	@Autowired
	private MongoTemplate mongoTemplate;
	public static final String COLLECTION_NAME = "bets";

	public RoundBets findAllBets(int season, int round) {
		Query query = new Query(Criteria.where("season").is(season).and("round").is(round));
		RoundBets aux =mongoTemplate.findOne(query, RoundBets.class, COLLECTION_NAME);
		return aux;
	}
	
	public boolean addBet(int season, int round, Bet bet){
		Query query = new Query();
		query.addCriteria(Criteria.where("season").is(season).and("round").is(round));
 
		Update update = new Update();
		update.push("bets", bet);
 
		mongoTemplate.upsert(query, update, RoundBets.class);
		return true;
	}
}


