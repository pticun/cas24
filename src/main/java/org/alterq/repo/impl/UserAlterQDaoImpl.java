package org.alterq.repo.impl;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.alterq.domain.RolCompany;
import org.alterq.domain.UserAlterQ;
import org.alterq.repo.UserAlterQDao;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mongodb.DBObject;

@Repository
public class UserAlterQDaoImpl implements UserAlterQDao {
	@Autowired
	private MongoTemplate mongoTemplate;
	public static final String COLLECTION_NAME = "useralterq";

	public UserAlterQ findById(String id) {
		return mongoTemplate.findById(id, UserAlterQ.class, COLLECTION_NAME);
	}

	public List<UserAlterQ> findAllOrderedByName() {
		return mongoTemplate.findAll(UserAlterQ.class, COLLECTION_NAME);
	}

	public void create(UserAlterQ userAlterQ) throws Exception {
		String password = userAlterQ.getPwd();
		// TODO makePasswordHash made in layer Controller
		// String passwordHash = makePasswordHash(password, userAlterQ.getId());

		userAlterQ.setPwd(password);
		UserAlterQ dao = mongoTemplate.findById(userAlterQ.getId(), UserAlterQ.class, COLLECTION_NAME);
		if (dao != null)
			throw new Exception();
		mongoTemplate.insert(userAlterQ, COLLECTION_NAME);

	}

	private String makePasswordHash(String password, String salt) {
		try {
			String saltedAndHashed = password + "," + salt;
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			digest.update(saltedAndHashed.getBytes());
			byte hashedBytes[] = (new String(digest.digest(), "UTF-8")).getBytes();
			return Base64.encodeBase64String(hashedBytes) + "," + salt;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("SAH1 is not available", e);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("UTF-8 unavailable?  Not a chance", e);
		}
	}

	@Override
	public UserAlterQ validateLogin(String id, String password) {
		UserAlterQ dao = mongoTemplate.findById(id, UserAlterQ.class, COLLECTION_NAME);
		if (dao == null)
			return null;
		String hashedAndSalted = password;

		if (!hashedAndSalted.equals(dao.getPwd())) {
			System.out.println("Submitted password is not a match");
			return null;
		}
		/*
		 * String salt = hashedAndSalted.split(",")[1]; if
		 * (!hashedAndSalted.equals(makePasswordHash(password, salt))) {
		 * System.out.println("Submitted password is not a match"); return null;
		 * }
		 */
		return dao;

	}

	@Override
	public void save(UserAlterQ userAlterQ) {
		mongoTemplate.save(userAlterQ, COLLECTION_NAME);

	}

	@Override
	public List<UserAlterQ> findUserWithAutomatics(int company) {
		Query query = new Query(Criteria.where("company").is(company).and("automatics").gt(0));
		return mongoTemplate.find(query, UserAlterQ.class, COLLECTION_NAME);
	}

	@Override
	public DBObject getLastError() {
		return mongoTemplate.getDb().getLastError();
	}

	@Override
	public UserAlterQ findAdminByCompany(int company) {
		Query query = new Query(Criteria.where("company").is(company)).addCriteria(Criteria.where("admin").is(Boolean.TRUE));
		return mongoTemplate.findOne(query, UserAlterQ.class, COLLECTION_NAME);
	}

	@Override
	public void remove(UserAlterQ userAlterQ) throws Exception {
		mongoTemplate.remove(userAlterQ, COLLECTION_NAME);

	}

	@Override
	public void addRolForCompany(UserAlterQ userAlterQ, RolCompany rc) {
		Query query = new Query(Criteria.where("id").is(userAlterQ.getId()));
		query.addCriteria(Criteria.where("rols.company").is(rc.getCompany()));
		query.addCriteria(Criteria.where("rols.rol").is(rc.getRol()));
		UserAlterQ uaq = mongoTemplate.findOne(query, UserAlterQ.class, COLLECTION_NAME);
		if (uaq == null) {
			uaq = mongoTemplate.findById(userAlterQ.getId(), UserAlterQ.class, COLLECTION_NAME);
			if(uaq!=null){
				uaq.getRols().add(rc);
				mongoTemplate.save(uaq, COLLECTION_NAME);
			}
		}
	}

	@Override
	public void deleteRolForCompany(UserAlterQ userAlterQ, RolCompany rc) {
		Query query = new Query(Criteria.where("id").is(userAlterQ.getId()));
		query.addCriteria(Criteria.where("rols.company").is(rc.getCompany()));
		query.addCriteria(Criteria.where("rols.rol").is(rc.getRol()));
		UserAlterQ uaq = mongoTemplate.findOne(query, UserAlterQ.class, COLLECTION_NAME);
		if (uaq != null) {
			uaq = mongoTemplate.findById(userAlterQ.getId(), UserAlterQ.class, COLLECTION_NAME);
			if (uaq != null) {
				uaq.getRols().remove(rc);
				mongoTemplate.save(uaq, COLLECTION_NAME);
			}
		}
	}

	@Override
	public List<RolCompany> getRols(UserAlterQ userAlterQ) {
		Query query = new Query(Criteria.where("id").is(userAlterQ.getId()));
		UserAlterQ uaqL = mongoTemplate.findOne(query, UserAlterQ.class, COLLECTION_NAME);
		//user not exists or not rol for this company
		List<RolCompany> rcL=new ArrayList<RolCompany>();
		if (uaqL != null) {
			rcL= userAlterQ.getRols();
		}
		return rcL;
	}

	@Override
	public List<RolCompany> getRolsForCompany(UserAlterQ userAlterQ, RolCompany rc) {
		Query query = new Query(Criteria.where("id").is(userAlterQ.getId()));
		query.addCriteria(Criteria.where("rols.company").is(rc.getCompany()));
		UserAlterQ uaqL = mongoTemplate.findOne(query, UserAlterQ.class, COLLECTION_NAME);
		//user not exists or not rol for this company
		List<RolCompany> rcL=new ArrayList<RolCompany>();
		if (uaqL != null) {
			rcL= userAlterQ.getRols();
		}
		return rcL;
	}
}
