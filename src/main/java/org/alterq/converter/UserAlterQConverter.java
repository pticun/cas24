package org.alterq.converter;

import java.lang.reflect.InvocationTargetException;

import org.alterq.domain.UserAlterQ;
import org.alterq.util.DateFormatUtil;
import org.alterq.util.NumericUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAlterQConverter {
	@Autowired
	private NumericUtil numericUtil;
	@Autowired
	private DateFormatUtil dateFormatUtil;

	public UserAlterQ converterUserAlterQInResponseDto(UserAlterQ userAlterQ){
		UserAlterQ userCopy=new UserAlterQ();
		try {
			BeanUtils.copyProperties(userCopy, userAlterQ);
			userCopy.setPwd("*******");
			userCopy.setBalance(numericUtil.getTwoDecimalFormat(userAlterQ.getBalance()));
			if (userCopy.getBirthday()!=null)
				userCopy.setBirthday(dateFormatUtil.convertIsoTimeToFormatDay(userAlterQ.getBirthday()));
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userCopy;
	}
	
}
