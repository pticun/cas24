package org.alterq.repo.impl;

import java.security.SecureRandom;

import org.alterq.domain.SessionAlterQ;
import org.alterq.repo.SessionAlterQDao;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SessionAlterQDaoImpl implements SessionAlterQDao {
	@Autowired
	private MongoTemplate mongoTemplate;
	public static final String COLLECTION_NAME = "sessionalterq";

	@Override
	public String findUserAlterQIdBySessionId(String sessionId) {
		SessionAlterQ sessionAlterQ = mongoTemplate.findById(sessionId, SessionAlterQ.class, COLLECTION_NAME);
		if (sessionAlterQ != null)
			return sessionAlterQ.getUserAlterQ();
		return null;
	}

	@Override
	public String startSession(String id) {
		// get 32 byte random number. that's a lot of bits.
		SecureRandom generator = new SecureRandom();
		byte randomBytes[] = new byte[32];
		generator.nextBytes(randomBytes);

		String sessionID = Base64.encodeBase64String(randomBytes);

		// build the BSON object
		SessionAlterQ sessionAlterQ = new SessionAlterQ();
		sessionAlterQ.setUserAlterQ(id);
		sessionAlterQ.setId(sessionID);

		mongoTemplate.insert(sessionAlterQ, COLLECTION_NAME);

		return sessionAlterQ.getId();
	}

	@Override
	public void endSession(String sessionID) {
		SessionAlterQ sessionAlterQ = new SessionAlterQ();
		sessionAlterQ.setId(sessionID);
		mongoTemplate.remove(sessionAlterQ, COLLECTION_NAME);
	}

	@Override
	public SessionAlterQ getSession(String sessionId) {
		SessionAlterQ sessionAlterQ = mongoTemplate.findById(sessionId, SessionAlterQ.class, COLLECTION_NAME);
		return sessionAlterQ;
	}

}
