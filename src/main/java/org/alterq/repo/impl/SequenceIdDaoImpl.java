package org.alterq.repo.impl;

import java.util.List;

import org.alterq.domain.SequenceId;
import org.alterq.repo.MongoCollection;
import org.alterq.repo.SequenceIdDao;
import org.springframework.stereotype.Repository;

@Repository
public class SequenceIdDaoImpl extends MongoCollection implements SequenceIdDao {
	public String COLLECTION_NAME = "sequenceId";

	public SequenceIdDaoImpl() {
		super.COLLECTION_NAME = COLLECTION_NAME;
	}

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
		String seq = bean.getSequence();
		int seqInt=Integer.parseInt(seq);
		seqInt++;
		bean.setSequence(""+seqInt);
		mongoTemplate.save(bean, COLLECTION_NAME);
		return seqInt;
	}

	@Override
	public String getSequenceId(String key) {
		SequenceId bean = mongoTemplate.findById(key, SequenceId.class, COLLECTION_NAME);
		String seq = bean.getSequence();
		return seq;
	}

}
