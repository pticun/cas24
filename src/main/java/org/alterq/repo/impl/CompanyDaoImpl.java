package org.alterq.repo.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.alterq.domain.Company;
import org.alterq.dto.AlterQConstants;
import org.alterq.repo.CompanyDao;
import org.alterq.repo.MongoCollection;
import org.alterq.util.enumeration.CompanyTypeEnum;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class CompanyDaoImpl extends MongoCollection implements CompanyDao {
	public static final String COLLECTION_NAME = "company";

	public CompanyDaoImpl() {
		super.COLLECTION_NAME = COLLECTION_NAME;
	}

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

	@Override
	public List<Company> findAllVisibleCompany() {
		Query query = new Query(Criteria.where("company").ne(AlterQConstants.DEFECT_COMPANY));
		query.addCriteria(Criteria.where("visibility").is(Boolean.TRUE));
		List<Company> listCompany = mongoTemplate.find(query, Company.class, COLLECTION_NAME);
		return listCompany;
	}

	@Override
	public List<Company> findAllPublicCompany() {
		Query query = new Query(Criteria.where("company").ne(AlterQConstants.DEFECT_COMPANY));
		Collection<Integer> valores=new ArrayList<Integer>();
		valores.add(new Integer(CompanyTypeEnum.COMPANY_COLLABORATIVE_PUBLIC.getValue()));
		valores.add(new Integer(CompanyTypeEnum.COMPANY_NON_COLLABORATIVE_PUBLIC.getValue()));
		query.addCriteria(Criteria.where("type").in(valores));
		List<Company> listCompany = mongoTemplate.find(query, Company.class, COLLECTION_NAME);
		return listCompany;
	}

}
