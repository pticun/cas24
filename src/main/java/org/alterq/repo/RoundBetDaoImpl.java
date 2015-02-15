package org.alterq.repo;

import java.util.List;

import org.alterq.domain.Bet;
import org.alterq.domain.Prize;
import org.alterq.domain.RoundBets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;





import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.unwind;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;

@Repository
public class RoundBetDaoImpl implements RoundBetDao {
	@Autowired
	private MongoTemplate mongoTemplate;
	public static final String COLLECTION_NAME = "roundBets";

	public RoundBets findAllBets(int season, int round) {
		Query query = new Query(Criteria.where("season").is(season).and("round").is(round));
		RoundBets aux =mongoTemplate.findOne(query, RoundBets.class, COLLECTION_NAME);
		return aux;
	}
	
	public int countAllBets(int season, int round) {
		Query query = new Query(Criteria.where("season").is(season).and("round").is(round));
		RoundBets aux =mongoTemplate.findOne(query, RoundBets.class, COLLECTION_NAME);
		return (aux.getBets()==null?0:aux.getBets().size());
	}

	public RoundBets findAllUserBets(int season, int round, String user) {
        Aggregation agg = newAggregation( 
                unwind("bets"),
                //match(Criteria.where("bets.user").is(user).and("season").is(season).and("round").is(round).and("company").is(company)),
                //group("_id").first("company").as("company").first("season").as("season").first("round").as("round").push("bets").as("bets")
                match(Criteria.where("bets.user").is(user).and("season").is(season).and("round").is(round)),
                group("_id").first("season").as("season").first("round").as("round").push("bets").as("bets")
        );
        AggregationResults<RoundBets> result = mongoTemplate.aggregate(agg, "roundBets", RoundBets.class);
        if ( !result.getMappedResults().isEmpty()){
        	RoundBets aux = result.getMappedResults().get(0);
        	return aux;
        }
        else
        	return null;
	}

	public boolean addBet(int season, int round, Bet bet){
		Query query = new Query();
		query.addCriteria(Criteria.where("season").is(season).and("round").is(round));
 
		Update update = new Update();
		update.push("bets", bet);
 
		mongoTemplate.upsert(query, update, RoundBets.class);
		return true;
	}
	
	public boolean deleteAllBets(int season, int round){
		Query query = new Query();
		query.addCriteria(Criteria.where("season").is(season).and("round").is(round));
		mongoTemplate.remove(query, RoundBets.class);
		return true;
	}
	public boolean deleteAllUserBets(int season, int round, String user){

	       Aggregation agg = newAggregation( 
	                unwind("bets"),
	                //match(Criteria.where("bets.user").is(user).and("season").is(season).and("round").is(round).and("company").is(company)),
	                //group("_id").first("company").as("company").first("season").as("season").first("round").as("round").push("bets").as("bets")
	                match(Criteria.where("bets.user").is(user).and("season").is(season).and("round").is(round)),
	                group("_id").first("season").as("season").first("round").as("round").push("bets").as("bets")
	        );
	        AggregationResults<RoundBets> result = mongoTemplate.aggregate(agg, "roundBets", RoundBets.class);
	        
	        if ( !result.getMappedResults().isEmpty()){
	        	for (RoundBets roundBets : result) {
					for (Bet bets : roundBets.getBets()) {
						Query query = new Query();
						query.addCriteria(Criteria.where("season").is(season).and("round").is(round));
						Update update = new Update();
						update.pull("bets", bets);
						mongoTemplate.upsert(query,update, RoundBets.class);
					} 
				} 
	        }
//	        mongoTemplate.remove(result, RoundBets.class);
		
/*		
		Query query = new Query();
		query.addCriteria(Criteria.where("season").is(season).and("round").is(round));
		 
		Update update = new Update();
		Bet bet=new Bet();
		bet.setUser(user);
		update.pull("bets", bet);
		
		mongoTemplate.upsert(query,update, RoundBets.class);
*/		
		return true;
	}
	public boolean deleteUserBet(int season, int round, Bet bet){
		Query query = new Query();
		query.addCriteria(Criteria.where("season").is(season).and("round").is(round));
		 
		Update update = new Update();
		update.pull("bets", bet);
		
		mongoTemplate.upsert(query,update, RoundBets.class);
		return true;
	}

	@Override
	public void add(RoundBets bean) {
		mongoTemplate.insert(bean,COLLECTION_NAME);
	}
	
	public boolean addPrize(int season, int round, Prize prize){
		Query query = new Query();
		query.addCriteria(Criteria.where("season").is(season).and("round").is(round));
 
		Update update = new Update();
		update.push("prizes", prize);
 
		mongoTemplate.upsert(query, update, RoundBets.class);
		return true;
	}

	public void update(RoundBets rounBets){
		Query query = new Query();
		query.addCriteria(Criteria.where("company").is(rounBets.getCompany()).and("season").is(rounBets.getSeason()).and("round").is(rounBets.getRound()));

		Update update = new Update();
		
		/*
		List<Prize> lPrizes = rounBets.getPrizes();
		if (lPrizes != null){
			for (Prize prize : lPrizes){
				addPrize(rounBets.getSeason(), rounBets.getRound(), prize);
			}
		}
		*/
		
		update.addToSet("reward", rounBets.getReward());
		
		update.addToSet("jackpot", rounBets.getJackpot());

		mongoTemplate.upsert(query,update, RoundBets.class);
	}
}


