package org.alterq.util;

import java.util.Comparator;

import org.alterq.domain.RolCompany;

public class RolCompanyComparator implements Comparator<RolCompany> {

	@Override
	public int compare(RolCompany rc1, RolCompany rc2) {
		int company1 = rc1.getCompany();
		int company2 = rc2.getCompany();
		if (company1 == company2) {
			int rol1 = rc1.getRol();
			int rol2 = rc2.getRol();
			return rol2 - rol1;
		} else {
			return company2 - company1;
		}
	}

}
