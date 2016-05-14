package org.alterq.repo;

import java.util.List;

import org.alterq.domain.RolCompany;
import org.alterq.domain.UserAlterQ;
import org.alterq.util.enumeration.BetTypeEnum;

import com.mongodb.DBObject;

public interface UserAlterQDao {
	public UserAlterQ findById(String id);

	public UserAlterQ findByIdIgnoreCase(String id);

	public UserAlterQ findAdminByCompany(int company);

	public UserAlterQ findSuperAdmin();

	public List<UserAlterQ> findAllOrderedByName();

	public List<UserAlterQ> findAllUserActive();

	public void create(UserAlterQ userAlterQ) throws Exception;

	public void save(UserAlterQ userAlterQ);

	public void updateBalance(UserAlterQ userAlterQ);
	
	public void updateCompanyAutomaticBet(String userID, int company, int numAutomatics);

	public UserAlterQ validateLogin(String id, String password);

	public List<UserAlterQ> findUserWithTypeSpecialBets(int company, BetTypeEnum betType);

	public void remove(UserAlterQ userAlterQ) throws Exception;

	public void addRolForCompany(UserAlterQ userAlterQ, RolCompany rc);

	public void deleteRolForCompany(UserAlterQ userAlterQ, RolCompany rc);

	public List<RolCompany> getRols(UserAlterQ userAlterQ);

	public List<RolCompany> getRolsForCompany(UserAlterQ userAlterQ, RolCompany rc);
	
	public List<UserAlterQ> findUsersCompany(int company);

	public DBObject getLastError();
}
