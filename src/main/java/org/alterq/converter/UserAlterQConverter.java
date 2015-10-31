package org.alterq.converter;

import org.alterq.domain.UserAlterQ;
import org.alterq.util.DateFormatUtil;
import org.alterq.util.NumericUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAlterQConverter {
	@Autowired
	private NumericUtil numericUtil;
	@Autowired
	private DateFormatUtil dateFormatUtil;

	public UserAlterQ converterUserAlterQ(UserAlterQ userAlterQ){
		userAlterQ.setPwd("*******");
		userAlterQ.setBalance(numericUtil.getTwoDecimalFormat(userAlterQ.getBalance()));
		userAlterQ.setBirthday(dateFormatUtil.convertIsoTimeToFormatDay(userAlterQ.getBirthday()));
		return userAlterQ;
	}
	
}
