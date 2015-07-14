package org.alterq.repo.impl;

import java.util.List;

import org.alterq.domain.GeneralData;
import org.alterq.repo.GeneralDataDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class GeneralDataDaoImpl implements GeneralDataDao {
	@Autowired
	private MongoTemplate mongoTemplate;
	public static final String COLLECTION_NAME = "generalData";

	@Override
	public GeneralData findById(Object id) {
		GeneralData dao = mongoTemplate.findById(id, GeneralData.class, COLLECTION_NAME);
		return dao;
	}
	@Override
	public GeneralData findByCompany(int id) {
		Query query = new Query(Criteria.where("company").is(id));
		GeneralData dao = mongoTemplate.findOne(query, GeneralData.class, COLLECTION_NAME);
		return dao;
	}

	@Override
	public void add(GeneralData generalData) {
		mongoTemplate.insert(generalData, COLLECTION_NAME);
	}

	@Override
	public void update(GeneralData generalData) {
		mongoTemplate.save(generalData, COLLECTION_NAME);
	}

	@Override
	public void delete(GeneralData generalData) {
		mongoTemplate.remove(generalData, COLLECTION_NAME);
	}
	@Override
	public List<GeneralData> findAll() {
		return mongoTemplate.findAll(GeneralData.class, COLLECTION_NAME);
	}

}
