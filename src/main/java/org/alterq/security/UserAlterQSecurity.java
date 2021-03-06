package org.alterq.security;

import org.alterq.domain.UserAlterQ;
import org.alterq.dto.AlterQConstants;
import org.alterq.dto.ErrorDto;
import org.alterq.exception.SecurityException;
import org.alterq.repo.SessionAlterQDao;
import org.alterq.repo.UserAlterQDao;
import org.alterq.util.enumeration.MessageResourcesNameEnum;
import org.apache.commons.lang3.StringUtils;
import org.arch.core.i18n.resources.MessageLocalizedResources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserAlterQSecurity {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	@Qualifier("messageLocalizedResources")
	private MessageLocalizedResources messageLocalizedResources;
	@Autowired
	private SessionAlterQDao sessionDao;
	@Autowired
	private UserAlterQDao userDao;

	public boolean isSameUserInSession(String idUserAlterQ, String cookieSession) throws SecurityException {
		if (log.isDebugEnabled()) {
			log.debug("UserAlterQSecurity:isSameUserInSession:idUserAlterQ:" + idUserAlterQ);
		}

		if (StringUtils.isBlank(cookieSession)) {
			ErrorDto error = new ErrorDto();
			error.setIdError(MessageResourcesNameEnum.USER_NOT_IN_SESSION);
			error.setStringError(messageLocalizedResources.resolveLocalizedErrorMessage(MessageResourcesNameEnum.USER_NOT_IN_SESSION));
			throw new SecurityException(error);
		}
		String idUser = sessionDao.findUserAlterQIdBySessionId(cookieSession);
		if (!StringUtils.equals(idUserAlterQ, idUser)) {
			ErrorDto error = new ErrorDto();
			error.setIdError(MessageResourcesNameEnum.USER_NOT_SAME);
			error.setStringError(messageLocalizedResources.resolveLocalizedErrorMessage(MessageResourcesNameEnum.USER_NOT_SAME));
			throw new SecurityException(error);
		}
		return true;
	}

	//TODO is admin for this company??
	public boolean isAdminUserInSession(String cookieSession, int company) throws SecurityException {
		if (log.isDebugEnabled()) {
			log.debug("UserAlterQSecurity:isAdminUserInSession:");
		}

		if (StringUtils.isBlank(cookieSession)) {
			ErrorDto error = new ErrorDto();
			error.setIdError(MessageResourcesNameEnum.USER_NOT_IN_SESSION);
			error.setStringError(messageLocalizedResources.resolveLocalizedErrorMessage(MessageResourcesNameEnum.USER_NOT_IN_SESSION));
			throw new SecurityException(error);
		}
		//UserAlterQ idAdmin = userDao.findAdminByCompany(AlterQConstants.DEFECT_COMPANY);
		UserAlterQ idAdmin = userDao.findAdminByCompany(company);
		String idUser = sessionDao.findUserAlterQIdBySessionId(cookieSession);
		if (!StringUtils.equals(idAdmin.getId(), idUser)) {
			ErrorDto error = new ErrorDto();
			error.setIdError(MessageResourcesNameEnum.USER_NOT_ADMIN);
			error.setStringError(messageLocalizedResources.resolveLocalizedErrorMessage(MessageResourcesNameEnum.USER_NOT_ADMIN));
			throw new SecurityException(error);
		}
		return true;
	}

	public boolean isSuperAdminUserInSession(String cookieSession) throws SecurityException {
		if (log.isDebugEnabled()) {
			log.debug("UserAlterQSecurity:isSuperAdminUserInSession:");
		}

		if (StringUtils.isBlank(cookieSession)) {
			ErrorDto error = new ErrorDto();
			error.setIdError(MessageResourcesNameEnum.USER_NOT_IN_SESSION);
			error.setStringError(messageLocalizedResources.resolveLocalizedErrorMessage(MessageResourcesNameEnum.USER_NOT_IN_SESSION));
			throw new SecurityException(error);
		}
		UserAlterQ idAdmin = userDao.findSuperAdmin();
		String idUser = sessionDao.findUserAlterQIdBySessionId(cookieSession);
		if (!StringUtils.equals(idAdmin.getId(), idUser)) {
			ErrorDto error = new ErrorDto();
			error.setIdError(MessageResourcesNameEnum.USER_NOT_ADMIN);
			error.setStringError(messageLocalizedResources.resolveLocalizedErrorMessage(MessageResourcesNameEnum.USER_NOT_ADMIN));
			throw new SecurityException(error);
		}
		return true;
	}
	
	public void existsUserAlterQ(UserAlterQ user) throws SecurityException {
		// User exists
		UserAlterQ bean = userDao.findById(user.getId());
		if (bean != null) {
			ErrorDto error = new ErrorDto();
			error.setIdError(MessageResourcesNameEnum.USER_ALREADY_EXIST);
			error.setStringError(messageLocalizedResources.resolveLocalizedErrorMessage(MessageResourcesNameEnum.USER_ALREADY_EXIST));
			throw new SecurityException(error);
		}
	}
	public void notExistsUserAlterQ(UserAlterQ user) throws SecurityException {
		// User not exists
		UserAlterQ bean = userDao.findById(user.getId());
		if (bean == null) {
			ErrorDto error = new ErrorDto();
			error.setIdError(MessageResourcesNameEnum.USER_NOT_EXIST);
			error.setStringError(messageLocalizedResources.resolveLocalizedErrorMessage(MessageResourcesNameEnum.USER_NOT_EXIST));
			throw new SecurityException(error);
		}
	}
}
