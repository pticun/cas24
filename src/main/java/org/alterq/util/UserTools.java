package org.alterq.util;

import java.util.List;

import org.alterq.domain.RolCompany;
import org.alterq.domain.UserAlterQ;
import org.alterq.repo.AdminDataDao;
import org.alterq.repo.UserAlterQDao;
import org.alterq.util.enumeration.RolNameEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserTools{
	@Autowired
	private AdminDataDao adminDataDao;
	@Autowired
	private UserAlterQDao userDao;	

	 
	
	
	public boolean isUserAdminCompany (String userID, int company){
		
		UserAlterQ userAlterQ = userDao.findById(userID);
		
		List<RolCompany> rc=userAlterQ.getRols();
		for (RolCompany rolCompany : rc) {
			if (rolCompany.getCompany() == company)
			{
				if(RolNameEnum.ROL_ADMIN.getValue()== rolCompany.getRol()){
					return true;
				}
			}
		}
		
		return false;
	}
}