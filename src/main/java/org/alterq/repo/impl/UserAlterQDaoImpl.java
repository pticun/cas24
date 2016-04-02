package org.alterq.repo.impl;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.unwind;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.data.domain.Sort;
import org.alterq.domain.RolCompany;
import org.alterq.domain.UserAlterQ;
import org.alterq.dto.AlterQConstants;
import org.alterq.repo.MongoCollection;
import org.alterq.repo.UserAlterQDao;
import org.alterq.util.enumeration.BetTypeEnum;
import org.alterq.util.enumeration.RolNameEnum;
import org.apache.commons.codec.binary.Base64;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.DBObject;
import com.mongodb.WriteResult;

@Repository
public class UserAlterQDaoImpl extends MongoCollection implements UserAlterQDao {

	public UserAlterQDaoImpl() {
		super.COLLECTION_NAME = COLLECTION_NAME;
	}

	public static final String COLLECTION_NAME = "userAlterq";

	public UserAlterQ findById(String id) {
		return mongoTemplate.findById(id, UserAlterQ.class, COLLECTION_NAME);
	}

	@Override
	public UserAlterQ findByIdIgnoreCase(String id) {
		Pattern p = Pattern.compile(id, Pattern.CASE_INSENSITIVE);
		Query query = new Query(Criteria.where("id").regex(p));
		return mongoTemplate.findOne(query, UserAlterQ.class, COLLECTION_NAME);
	}

	@Override
	public List<UserAlterQ> findAllUserActive() {
		Query query = new Query();
		query.addCriteria(Criteria.where("active").is(Boolean.TRUE));
		query.with(new Sort(Sort.Direction.ASC, "_id"));
		return mongoTemplate.find(query,UserAlterQ.class, COLLECTION_NAME);
	}

	public List<UserAlterQ> findAllOrderedByName() {
		Query query = new Query().with(new Sort(Sort.Direction.ASC, "_id"));
		return mongoTemplate.find(query,UserAlterQ.class, COLLECTION_NAME);
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
//		UserAlterQ dao = mongoTemplate.findById(id, UserAlterQ.class, COLLECTION_NAME);
		UserAlterQ dao = this.findByIdIgnoreCase(id);
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
	public UserAlterQ findAdminByCompany(int company) {
		Aggregation agg = newAggregation(unwind("rols"),
				match(Criteria.where("rols.company").is(company).and("rols.rol").is(RolNameEnum.ROL_ADMIN.getValue())), group("_id").push("rols").as("rols"));
		AggregationResults<UserAlterQ> result = mongoTemplate.aggregate(agg, "userAlterq", UserAlterQ.class);
		if (!result.getMappedResults().isEmpty()) {
			UserAlterQ aux = result.getMappedResults().get(0);
			return aux;
		} else
			return null;
	}

	@Override
	public UserAlterQ findSuperAdmin() {
		Aggregation agg = newAggregation(unwind("rols"),
				match(Criteria.where("rols.company").is(AlterQConstants.DEFECT_COMPANY).and("rols.rol").is(RolNameEnum.ROL_SUPER_ADMIN.getValue())),
				group("_id").push("rols").as("rols"));
		AggregationResults<UserAlterQ> result = mongoTemplate.aggregate(agg, "userAlterq", UserAlterQ.class);
		if (!result.getMappedResults().isEmpty()) {
			UserAlterQ aux = result.getMappedResults().get(0);
			return aux;
		} else
			return null;
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
			if (uaq != null) {
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
		// user not exists or not rol for this company
		List<RolCompany> rcL = new ArrayList<RolCompany>();
		if (uaqL != null) {
			rcL = uaqL.getRols();
		}
		return rcL;
	}

	@Override
	public List<RolCompany> getRolsForCompany(UserAlterQ userAlterQ, RolCompany rc) {
		Query query = new Query(Criteria.where("id").is(userAlterQ.getId()));
		query.addCriteria(Criteria.where("rols.company").is(rc.getCompany()));
		UserAlterQ uaqL = mongoTemplate.findOne(query, UserAlterQ.class, COLLECTION_NAME);
		// user not exists or not rol for this company
		List<RolCompany> rcL = new ArrayList<RolCompany>();
		if (uaqL != null) {
			rcL = userAlterQ.getRols();
		}
		return rcL;
	}

	@Override
	public List<UserAlterQ> findUserWithTypeSpecialBets(int company, BetTypeEnum betType) {
		List<UserAlterQ> userAlterQList = new ArrayList<UserAlterQ>();
		Aggregation agg = newAggregation(
		// project().andInclude("_id","balance","name","specialBets"),
				unwind("specialBets"), match(Criteria.where("specialBets.type").is(betType.getValue()).and("specialBets.company").is(company)),
				// group("_id","balance","name").push("specialBets").as("specialBets")
				group("_id").push("specialBets").as("specialBets"));
		AggregationResults<UserAlterQ> result = mongoTemplate.aggregate(agg, "userAlterq", UserAlterQ.class);
		if (!result.getMappedResults().isEmpty()) {
			// retrieve all userAlterQ object
			for (UserAlterQ userAlterQ : result) {
				userAlterQ = mongoTemplate.findById(userAlterQ.getId(), UserAlterQ.class, COLLECTION_NAME);
				userAlterQList.add(userAlterQ);
			}
		}
		return userAlterQList;
	}

	@Override
	public DBObject getLastError() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateBalance(UserAlterQ userAlterQ) {
		// TODO not working
		Query query = new Query(Criteria.where("id").is(userAlterQ.getId()));
		query.fields().include("balance");

		Update update = new Update().set("balance", userAlterQ.getBalance());
		mongoTemplate.findAndModify(query, Update.update("balance", userAlterQ.getBalance()),UserAlterQ.class, COLLECTION_NAME);
		
//		WriteResult wr = mongoTemplate.updateFirst(query, new Update().set("balance", userAlterQ.getBalance()), UserAlterQ.class);
//		System.out.println(wr.isUpdateOfExisting());
	}

	public List<UserAlterQ> findUsersCompany(int company){
		List<UserAlterQ> userAlterQList = new ArrayList<UserAlterQ>();
		Aggregation agg = newAggregation(
		// project().andInclude("_id","balance","name","specialBets"),
				unwind("rols"), match(Criteria.where("rols.company").is(company)),
				// group("_id","balance","name").push("specialBets").as("specialBets")
				group("_id").push("rols").as("rols"));
		AggregationResults<UserAlterQ> result = mongoTemplate.aggregate(agg, "userAlterq", UserAlterQ.class);
		if (!result.getMappedResults().isEmpty()) {
			for (UserAlterQ userAlterQ : result) {
				userAlterQ = mongoTemplate.findById(userAlterQ.getId(), UserAlterQ.class, COLLECTION_NAME);
				userAlterQList.add(userAlterQ);
			}
		}
		return userAlterQList;
	}
}
