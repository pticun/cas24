package org.alterq.repo;

import java.util.List;

import org.alterq.domain.SequenceId;

public interface SequenceIdDao {
	public SequenceId findById(Object id);

	public void add(SequenceId sequence);

	public void update(SequenceId sequence);

	public void delete(SequenceId sequence);

	public List<SequenceId> findAll();

	public int getNextSequenceId(String key);

	public String getSequenceId(String key);
}
