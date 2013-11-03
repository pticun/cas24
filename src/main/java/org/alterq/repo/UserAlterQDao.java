package org.alterq.repo;

import java.util.List;

import org.alterq.domain.UserAlterQ;

public interface UserAlterQDao {
	public UserAlterQ findById(String id);

	public UserAlterQ findByEmail(String email);

	public List<UserAlterQ> findAllOrderedByName();

	public void register(UserAlterQ userAlterQ);
}
