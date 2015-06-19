package org.alterq.repo.impl;

import java.util.List;

import org.alterq.domain.SequenceId;
import org.alterq.repo.SequenceIdDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
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
		SequenceId bean = mongoTemplate.findById(key, SequenceId.class, COLLECTION_NAME);
		int seq = bean.getSequence();
		seq++;
		bean.setSequence(seq);
		mongoTemplate.save(bean, COLLECTION_NAME);
		return bean.getSequence();
	}

}
