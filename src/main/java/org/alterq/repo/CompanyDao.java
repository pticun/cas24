package org.alterq.repo;

import java.util.List;

import org.alterq.domain.Company;

public interface CompanyDao {
	public Company findById(Object id);

	public Company findByCompany(int id);

	public void add(Company company);

	public void update(Company company);

	public void delete(Company company);

	public List<Company> findAll();

	public List<Company> findAllVisibleCompany();

	List<Company> findAllPublicCompany();
}
