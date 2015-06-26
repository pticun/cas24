package org.alterq.security;

import java.util.List;

import org.alterq.domain.RolCompany;
import org.alterq.domain.UserAlterQ;
import org.alterq.repo.UserAlterQDao;
import org.alterq.util.enumeration.RolNameEnum;
import org.arch.core.i18n.resources.MessageLocalizedResources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class RolCompanySecurity {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	@Qualifier("messageLocalizedResources")
	private MessageLocalizedResources messageLocalizedResources;
	@Autowired
	private UserAlterQDao userDao;

	/**
	 * Method uses for knowing if user is authorized for this operation is based
	 * max rol for user is greater or equal than RolCompany passed
	 * 
	 * @param userAlterQ
	 * @param rc
	 * @return
	 */
	public boolean isUserAuthorizedRolForCompany(UserAlterQ userAlterQ, RolCompany rc) {
		boolean result = false;
		List<RolCompany> rcL = userDao.getRolsForCompany(userAlterQ, rc);
		int maxRol = RolNameEnum.ROL_PUBLIC.getValue();
		// find out the max value rol
		for (RolCompany rolCompany : rcL) {
			if (maxRol <= rolCompany.getRol()) {
				maxRol = rolCompany.getRol();
			}
		}
		// if rol user >=
		if (maxRol >= rc.getRol()) {
			result = true;
		}
		return result;
	}

	public boolean isUserRolForCompany(UserAlterQ userAlterQ, RolCompany rc) {
		boolean result = false;
		List<RolCompany> rcL = userDao.getRolsForCompany(userAlterQ, rc);
		for (RolCompany rolCompany : rcL) {
			if (rc.getRol() == rolCompany.getRol()) {
				result = true;
			}
		}
		return result;
	}
}
