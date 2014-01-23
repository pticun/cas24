package org.alterq.repo;

import org.alterq.domain.GeneralData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class GeneralDataDaoImpl implements GeneralDataDao {
	@Autowired
	private MongoTemplate mongoTemplate;
	public static final String COLLECTION_NAME = "generalData";

	@Override
	public GeneralData findById(int id) {
		GeneralData dao = mongoTemplate.findById(id, GeneralData.class, COLLECTION_NAME);
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

}
