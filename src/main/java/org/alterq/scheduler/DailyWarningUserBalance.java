package org.alterq.scheduler;

import java.util.Date;

import org.alterq.domain.AdminData;
import org.alterq.domain.Round;
import org.alterq.dto.AlterQConstants;
import org.alterq.repo.AdminDataDao;
import org.alterq.repo.RoundDao;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class DailyWarningUserBalance {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private AdminDataDao adminDataDao;
	@Autowired
	private RoundDao roundDao;

	@Scheduled(cron = "${app.scheduler.dailyWarningUserBalance}")
	public void dailyWarningUserBalance() {
		executeDailyWarning();
	}
	
	public void executeDailyWarning(){
		log.debug("Method executed at every day.");
		AdminData adminData = adminDataDao.findById(AlterQConstants.DEFECT_ADMINDATA);
		Round roundBean = roundDao.findBySeasonRound(adminData.getSeason(), adminData.getRound());
		
		Date roundDate=roundBean.getDateRound();
		
		Date now=new Date();
		
		if (DateUtils.isSameDay(DateUtils.addDays(roundDate, -2), now) || DateUtils.isSameDay(DateUtils.addDays(roundDate, -1), now)){
			log.debug("execute sending mail");
		}
		


	}
}