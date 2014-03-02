package org.alterq.security;

import org.alterq.dto.ErrorDto;
import org.alterq.dto.ErrorType;
import org.alterq.exception.SecurityException;
import org.alterq.repo.SessionAlterQDao;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAlterQSecurity {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SessionAlterQDao sessionDao;

	public boolean isSameUserInSession(String idUserAlterQ, String cookieSession) throws SecurityException {
		if (log.isDebugEnabled()){
			log.debug("UserAlterQSecurity:isSameUserInSession:idUserAlterQ:"+idUserAlterQ);
		}

		if (StringUtils.isBlank(cookieSession)) {
			ErrorDto error = new ErrorDto();
			error.setIdError(ErrorType.USER_NOT_IN_SESSION);
			error.setStringError("user not in Session (i18n error)");
			throw new SecurityException(error);
		}
		String idUser = sessionDao.findUserAlterQIdBySessionId(cookieSession);
		if (!StringUtils.equals(idUserAlterQ, idUser)) {
			ErrorDto error = new ErrorDto();
			error.setIdError(ErrorType.USER_NOT_SAME);
			error.setStringError("USER_NOT_SAME (i18n error)");
			throw new SecurityException(error);
		}
		return true;
	}
}
