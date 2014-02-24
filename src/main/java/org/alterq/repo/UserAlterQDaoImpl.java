package org.alterq.repo;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.alterq.domain.UserAlterQ;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserAlterQDaoImpl implements UserAlterQDao {
	@Autowired
	private MongoTemplate mongoTemplate;
	public static final String COLLECTION_NAME = "useralterq";

	public UserAlterQ findById(String id) {
		//TODO if exist control throw
		return mongoTemplate.findById(id, UserAlterQ.class, COLLECTION_NAME);
	}

	public List<UserAlterQ> findAllOrderedByName() {
		return mongoTemplate.findAll(UserAlterQ.class, COLLECTION_NAME);
	}

	public void create(UserAlterQ userAlterQ) throws Exception {
		String password = userAlterQ.getPwd();
		//TODO makePasswordHash made in layer Controller
//		String passwordHash = makePasswordHash(password, userAlterQ.getId());

		userAlterQ.setPwd(password);
		UserAlterQ dao = mongoTemplate.findById(userAlterQ.getId(), UserAlterQ.class,COLLECTION_NAME);
		if(dao!=null)
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
		UserAlterQ dao = mongoTemplate.findById(id, UserAlterQ.class,COLLECTION_NAME);
		if (dao==null)
			return null;
		String hashedAndSalted = password;
		
		if(!hashedAndSalted.equals(dao.getPwd())){
			System.out.println("Submitted password is not a match");
			return null;
		}
/*
		String salt = hashedAndSalted.split(",")[1];
		if (!hashedAndSalted.equals(makePasswordHash(password, salt))) {
			System.out.println("Submitted password is not a match");
			return null;
		}
*/
		return dao;

	}

	@Override
	public void save(UserAlterQ userAlterQ) {
		mongoTemplate.save(userAlterQ, COLLECTION_NAME);
		
	}
}


