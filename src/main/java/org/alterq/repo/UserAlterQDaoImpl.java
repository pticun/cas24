package org.alterq.repo;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

import org.alterq.domain.UserAlterQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import sun.misc.BASE64Encoder;

@Repository
public class UserAlterQDaoImpl implements UserAlterQDao {
	@Autowired
	private MongoTemplate mongoTemplate;
	public static final String COLLECTION_NAME = "useralterq";
	private Random random = new SecureRandom();

	public UserAlterQ findById(String id) {
		//TODO if exist control throw
		return mongoTemplate.findById(id, UserAlterQ.class, COLLECTION_NAME);
	}

	public List<UserAlterQ> findAllOrderedByName() {
		return mongoTemplate.findAll(UserAlterQ.class, COLLECTION_NAME);
	}

	public void create(UserAlterQ userAlterQ) {
		String password = userAlterQ.getPwd();
		String passwordHash = makePasswordHash(password, Integer.toString(random.nextInt()));

		userAlterQ.setPwd(passwordHash);
		mongoTemplate.insert(userAlterQ, COLLECTION_NAME);

	}

	private String makePasswordHash(String password, String salt) {
		try {
			String saltedAndHashed = password + "," + salt;
			MessageDigest digest = MessageDigest.getInstance("MD5");
			digest.update(saltedAndHashed.getBytes());
			BASE64Encoder encoder = new BASE64Encoder();
			byte hashedBytes[] = (new String(digest.digest(), "UTF-8")).getBytes();
			return encoder.encode(hashedBytes) + "," + salt;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("MD5 is not available", e);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("UTF-8 unavailable?  Not a chance", e);
		}
	}

	@Override
	public UserAlterQ validateLogin(String id, String password) {
		UserAlterQ dao = mongoTemplate.findById(id, UserAlterQ.class,COLLECTION_NAME);
		String hashedAndSalted = dao.getPwd();

		String salt = hashedAndSalted.split(",")[1];
		if (!hashedAndSalted.equals(makePasswordHash(password, salt))) {
			System.out.println("Submitted password is not a match");
			return null;
		}
		return dao;

	}
}


