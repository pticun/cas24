package org.alterq.repo;

import java.util.List;

import org.alterq.domain.Company;

public interface CompanyDao {
	public Company findById(Object id);

	public Company findByCompany(int id);

	public void add(Company generalData);

	public void update(Company generalData);

	public void delete(Company generalData);

	public List<Company> findAll();
}
