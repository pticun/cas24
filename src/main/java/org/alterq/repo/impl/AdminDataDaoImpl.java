/**
 * 
 */
package org.alterq.repo.impl;

import java.util.List;

import org.alterq.domain.AdminData;
import org.alterq.dto.AlterQConstants;
import org.alterq.repo.AdminDataDao;
import org.alterq.repo.MongoCollection;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 * @author kotto
 *
 */
@Repository
public class AdminDataDaoImpl extends MongoCollection implements AdminDataDao {
	public static final String COLLECTION_NAME = "adminData";

	public AdminDataDaoImpl() {
		super.COLLECTION_NAME = COLLECTION_NAME;
	}

	@Override
	public AdminData findById(Object id) {
		AdminData dao = mongoTemplate.findById(id, AdminData.class, COLLECTION_NAME);
		return dao;
	}

	@Override
	public AdminData get() {
		Query query = new Query(Criteria.where("company").is(AlterQConstants.ADMIN_COMPANY));
		AdminData dao = mongoTemplate.findOne(query, AdminData.class, COLLECTION_NAME);
		return dao;
	}

	@Override
	public AdminData findByCompany(int id) {
		Query query = new Query(Criteria.where("company").is(id));
		AdminData dao = mongoTemplate.findOne(query, AdminData.class, COLLECTION_NAME);
		return dao;
	}

	@Override
	public void add(AdminData bean) {
		mongoTemplate.insert(bean, COLLECTION_NAME);
	}

	@Override
	public void update(AdminData bean) {
		mongoTemplate.save(bean, COLLECTION_NAME);
	}

	@Override
	public void delete(AdminData bean) {
		mongoTemplate.remove(bean, COLLECTION_NAME);
	}

	@Override
	public List<AdminData> findAll() {
		return mongoTemplate.findAll(AdminData.class, COLLECTION_NAME);
	}

}
