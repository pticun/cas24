package org.alterq.repo;

import java.util.List;

import org.alterq.domain.UserAlterQ;

import com.mongodb.DBObject;

public interface UserAlterQDao {
	public UserAlterQ findById(String id);
	public UserAlterQ findAdminByCompany(int company);

	public List<UserAlterQ> findAllOrderedByName();

	public void create(UserAlterQ userAlterQ) throws Exception;

	public void save(UserAlterQ userAlterQ);

	public UserAlterQ validateLogin(String id, String password);
	
	public List<UserAlterQ> findUserWithAutomatics(int company);
	
	public DBObject getLastError();
}
