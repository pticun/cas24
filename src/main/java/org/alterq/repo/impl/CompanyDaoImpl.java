package org.alterq.repo.impl;

import java.util.List;

import org.alterq.domain.Company;
import org.alterq.repo.CompanyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class CompanyDaoImpl implements CompanyDao {
	@Autowired
	private MongoTemplate mongoTemplate;
	public static final String COLLECTION_NAME = "company";

	@Override
	public Company findById(Object id) {
		Company dao = mongoTemplate.findById(id, Company.class, COLLECTION_NAME);
		return dao;
	}
	@Override
	public Company findByCompany(int id) {
		Query query = new Query(Criteria.where("company").is(id));
		Company dao = mongoTemplate.findOne(query, Company.class, COLLECTION_NAME);
		return dao;
	}

	@Override
	public void add(Company company) {
		mongoTemplate.insert(company, COLLECTION_NAME);
	}

	@Override
	public void update(Company company) {
		mongoTemplate.save(company, COLLECTION_NAME);
	}

	@Override
	public void delete(Company company) {
		mongoTemplate.remove(company, COLLECTION_NAME);
	}
	@Override
	public List<Company> findAll() {
		return mongoTemplate.findAll(Company.class, COLLECTION_NAME);
	}

}
