package org.alterq.repo;

import java.util.List;

import org.alterq.domain.AdminData;
import org.alterq.domain.GeneralData;

public interface AdminDataDao {
	public AdminData findById(Object id);

	public AdminData get();

	public AdminData findByCompany(int id);

	public void add(AdminData generalData);

	public void update(AdminData generalData);

	public void delete(AdminData generalData);

	public List<AdminData> findAll();
}
