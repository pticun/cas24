package org.alterq.repo.impl;

import java.security.SecureRandom;
import java.util.Date;
import java.util.List;

import org.alterq.domain.SessionAlterQ;
import org.alterq.repo.MongoCollection;
import org.alterq.repo.SessionAlterQDao;
import org.apache.commons.codec.binary.Base64;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class SessionAlterQDaoImpl extends MongoCollection implements SessionAlterQDao {
	public static final String COLLECTION_NAME = "sessionAlterq";

	public SessionAlterQDaoImpl() {
		super.COLLECTION_NAME = COLLECTION_NAME;
	}

	@Override
	public String findUserAlterQIdBySessionId(String sessionId) {
		SessionAlterQ sessionAlterQ = mongoTemplate.findById(sessionId, SessionAlterQ.class, COLLECTION_NAME);
		if (sessionAlterQ != null){
			sessionAlterQ.setDateUpdated(new Date());
			mongoTemplate.save(sessionAlterQ,COLLECTION_NAME);
			return sessionAlterQ.getUserAlterQ();
		}
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
		sessionAlterQ.setDateUpdated(new Date());
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

	@Override
	public void deleteInactiveSession(Date timeCaducate) {
		Query query = new Query(Criteria.where("dateUpdated").lt(timeCaducate));
		mongoTemplate.findAndRemove(query, SessionAlterQ.class, COLLECTION_NAME);
//		List<SessionAlterQ> sessionAlterQ = mongoTemplate.find(query, SessionAlterQ.class, COLLECTION_NAME);
//		for (SessionAlterQ sessionAlterQ2 : sessionAlterQ) {
//			System.out.println(sessionAlterQ2.getId()+"-"+sessionAlterQ2.getUserAlterQ()+"-"+ sessionAlterQ2.getDateUpdated());
//		}
		
	}

}
