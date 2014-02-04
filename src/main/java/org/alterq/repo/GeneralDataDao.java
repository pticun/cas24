package org.alterq.repo;

import org.alterq.domain.GeneralData;

public interface GeneralDataDao {
	public GeneralData findById(Object id);

	public GeneralData findByCompany(int id);

	public void add(GeneralData generalData);

	public void update(GeneralData generalData);

	public void delete(GeneralData generalData);
}
