package org.alterq.repo;

import java.util.List;

import org.alterq.domain.UserAlterQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserAlterQDaoImpl implements UserAlterQDao {
	@Autowired
	private MongoTemplate mongoTemplate;
	public static final String COLLECTION_NAME = "useralterq";

	public UserAlterQ findById(String id) {
		return mongoTemplate.findById(id, UserAlterQ.class, COLLECTION_NAME);
	}

	public UserAlterQ findByEmail(String email) {
		return null;
	}

	public List<UserAlterQ> findAllOrderedByName() {
		return mongoTemplate.findAll(UserAlterQ.class, COLLECTION_NAME);
	}

	public void register(UserAlterQ userAlterQ) {
		mongoTemplate.insert(userAlterQ, COLLECTION_NAME);
		return;
	}
}
