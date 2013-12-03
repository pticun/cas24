package org.alterq.repo;

import java.util.List;

import org.alterq.domain.Jornada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Order;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class JornadaDaoImpl implements JornadaDao {
	@Autowired
	private MongoTemplate mongoTemplate;
	public static final String COLLECTION_NAME = "jornadas";

	public Jornada findByTemporadaJornada(int temporada, int jornada) {
		Query query = new Query(Criteria.where("temporada").is(temporada).and("jornada").is(jornada));
		return mongoTemplate.findOne(query, Jornada.class, COLLECTION_NAME);
	}

	public Jornada findLastJornada() {
		Query query = new Query();
		// TODO not use deprecated
		query.sort().on("temporada", Order.ASCENDING).on("jornada", Order.DESCENDING);
		return mongoTemplate.findOne(query, Jornada.class, COLLECTION_NAME);
	}

	public List<Jornada> findAllOrderedByTemporada() {
		return mongoTemplate.findAll(Jornada.class, COLLECTION_NAME);
	}

	public void addJornada(Jornada jornada) {
		mongoTemplate.insert(jornada, COLLECTION_NAME);
		return;
	}

}
