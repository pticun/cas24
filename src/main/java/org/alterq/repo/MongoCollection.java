package org.alterq.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

public class MongoCollection {
	public String COLLECTION_NAME = "MongoCollection";
	@Autowired
	public MongoTemplate mongoTemplate;
	/**
	 * * Create a collection if the collection does not already
	 * exists
	 */
	public void createCollection() {
		if (!mongoTemplate.collectionExists(COLLECTION_NAME)) {
			mongoTemplate.createCollection(COLLECTION_NAME);
		}
	}

	/**
	 * * Drops the collection if the collection does already
	 * exists
	 */
	public void dropCollection(Object o) {
		if (mongoTemplate.collectionExists(this.COLLECTION_NAME)) {
			mongoTemplate.dropCollection(this.COLLECTION_NAME);
		}
	}

}
