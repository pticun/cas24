package org.alterq.repo.impl;

import java.util.List;

import org.alterq.domain.SequenceId;
import org.alterq.repo.SequenceIdDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

public class SequenceIdDaoImpl implements SequenceIdDao {
	@Autowired
	private MongoTemplate mongoTemplate;
	public static final String COLLECTION_NAME = "SequenceId";

	@Override
	public SequenceId findById(Object id) {
		SequenceId dao = mongoTemplate.findById(id, SequenceId.class, COLLECTION_NAME);
		return dao;
	}

	@Override
	public void add(SequenceId bean) {
		mongoTemplate.insert(bean, COLLECTION_NAME);
	}

	@Override
	public void update(SequenceId bean) {
		mongoTemplate.save(bean, COLLECTION_NAME);
	}

	@Override
	public void delete(SequenceId bean) {
		mongoTemplate.remove(bean, COLLECTION_NAME);
	}

	@Override
	public List<SequenceId> findAll() {
		return mongoTemplate.findAll(SequenceId.class, COLLECTION_NAME);
	}

	@Override
	public int getNextSequenceId(String key) {
		// get sequence id
		Query query = new Query(Criteria.where("_id").is(key));

		// increase sequence id by 1
		Update update = new Update();
		update.inc("sequence", 1);

		// return new increased id
		FindAndModifyOptions options = new FindAndModifyOptions();
		options.returnNew(true);

		// this is the magic happened.
		SequenceId seqId = mongoTemplate.findAndModify(query, update, options, SequenceId.class);

		// if no id, throws SequenceException
		// optional, just a way to tell user when the sequence id is failed to
		// generate.
		if (seqId == null) {
			return 0;
		}

		return seqId.getSequence();
	}

}
