package org.alterq.repo;

import java.util.List;

import org.alterq.domain.RolCompany;
import org.alterq.domain.UserAlterQ;

import com.mongodb.DBObject;

public interface UserAlterQDao {
	public UserAlterQ findById(String id);

	public UserAlterQ findAdminByCompany(int company);
	public UserAlterQ findSuperAdmin();

	public List<UserAlterQ> findAllOrderedByName();

	public void create(UserAlterQ userAlterQ) throws Exception;

	public void save(UserAlterQ userAlterQ);

	public UserAlterQ validateLogin(String id, String password);

	@Deprecated
	public List<UserAlterQ> findUserWithAutomatics(int company);

	public void remove(UserAlterQ userAlterQ) throws Exception;

	public void addRolForCompany(UserAlterQ userAlterQ, RolCompany rc);

	public void deleteRolForCompany(UserAlterQ userAlterQ, RolCompany rc);

	public List<RolCompany> getRols(UserAlterQ userAlterQ);

	public List<RolCompany> getRolsForCompany(UserAlterQ userAlterQ, RolCompany rc);

	public DBObject getLastError();
}
