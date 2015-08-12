package org.alterq.repo;

import java.util.List;

import org.alterq.domain.AdminData;

public interface AdminDataDao {
	/**
	 * 
	 * Return de Defect AdminData data 
	 * @param id
	 * @return AdminData
	 */
	public AdminData findById(int id);

	public void add(AdminData generalData);

	public void update(AdminData generalData);

	public void delete(AdminData generalData);

	public List<AdminData> findAll();
}
