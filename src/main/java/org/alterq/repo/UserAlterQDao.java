package org.alterq.repo;

import java.util.List;

import org.alterq.domain.UserAlterQ;

public interface UserAlterQDao {
	public UserAlterQ findById(String id);

	public List<UserAlterQ> findAllOrderedByName();

	public void create(UserAlterQ userAlterQ);
	
	public UserAlterQ validateLogin(String id, String password);
}
