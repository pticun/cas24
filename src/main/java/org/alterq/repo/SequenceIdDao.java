package org.alterq.repo;

import java.util.List;

import org.alterq.domain.SequenceId;

public interface SequenceIdDao {
	public SequenceId findById(Object id);

	public void add(SequenceId generalData);

	public void update(SequenceId generalData);

	public void delete(SequenceId generalData);

	public List<SequenceId> findAll();
	
	public int getNextSequenceId(String key);
}
