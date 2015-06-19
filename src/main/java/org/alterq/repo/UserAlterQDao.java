package org.alterq.repo;

import java.util.List;

import org.alterq.domain.RolCompany;
import org.alterq.domain.UserAlterQ;

import com.mongodb.DBObject;

public interface UserAlterQDao {
	public UserAlterQ findById(String id);

	@Deprecated
	public UserAlterQ findAdminByCompany(int company);

	public List<UserAlterQ> findAllOrderedByName();

	public void create(UserAlterQ userAlterQ) throws Exception;

	public void save(UserAlterQ userAlterQ);

	public UserAlterQ validateLogin(String id, String password);

	@Deprecated
	public List<UserAlterQ> findUserWithAutomatics(int company);

	public void remove(UserAlterQ userAlterQ) throws Exception;

	public void addRolForCompany(UserAlterQ userAlterQ, RolCompany rc);

	public void deleteRolForCompany(UserAlterQ userAlterQ, RolCompany rc);

	public boolean isUserRolForCompany(UserAlterQ userAlterQ, RolCompany rc);

	/**
	 * Method uses for knowing if user is authorized for this operation
	 * is based max rol for user is greater or equal than RolCompany passed 
	 * @param userAlterQ
	 * @param rc
	 * @return
	 */
	public boolean isUserAuthorizedRolForCompany(UserAlterQ userAlterQ, RolCompany rc);

	public DBObject getLastError();
}
