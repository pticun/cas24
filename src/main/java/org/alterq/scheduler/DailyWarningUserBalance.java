package org.alterq.scheduler;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.alterq.domain.AdminData;
import org.alterq.domain.Bet;
import org.alterq.domain.Company;
import org.alterq.domain.RolCompany;
import org.alterq.domain.Round;
import org.alterq.domain.RoundBets;
import org.alterq.domain.UserAlterQ;
import org.alterq.dto.AlterQConstants;
import org.alterq.dto.MailQueueDto;
import org.alterq.repo.AdminDataDao;
import org.alterq.repo.RoundBetDao;
import org.alterq.repo.RoundDao;
import org.alterq.repo.UserAlterQDao;
import org.alterq.util.enumeration.QueueMailEnum;
import org.alterq.util.enumeration.RolNameEnum;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.arch.core.channel.ProcessMailQueue;
import org.arch.core.util.CoreUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

public class DailyWarningUserBalance {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private AdminDataDao adminDataDao;
	@Autowired
	private RoundDao roundDao;
	@Autowired
	private RoundBetDao betDao;
	@Autowired
	private UserAlterQDao dao;

	@Autowired
	ProcessMailQueue processMailQueue;
	
	@Scheduled(cron = "${app.scheduler.dailyWarningUserBalance}")
	public void dailyWarningUserBalance() {
		executeDailyWarning();
	}

	public void executeDailyWarning() {
		int numApuestas;
		boolean isUserBirthDay = false;
		boolean isUserWithoutBets = false;
		boolean isUserWithoutMoney = false;
		float fUserBalance = 0;
		
		log.debug("Method executed at every day.");
		AdminData adminData = adminDataDao.findById(AlterQConstants.DEFECT_ADMINDATA);
		Round roundBean = roundDao.findBySeasonRound(adminData.getSeason(), adminData.getRound());

		Date roundDate = roundBean.getDateRound();

		Date now = new Date();
		DateFormat dfDay = new SimpleDateFormat("dd/MM/yyyy");

		List<UserAlterQ> allUser = dao.findAllUserActive();
		for (UserAlterQ userAlterQ : allUser) {
			
			Date dateBD = null;
			
			
			if (!userAlterQ.isActive())
				continue;
			
			try {
				String dateUserB = userAlterQ.getBirthday();
				if (dateUserB != null){
					dateBD = dfDay.parse(dateUserB);
					
					Calendar cal = Calendar.getInstance(); // current date
					int currMonth = cal.get(Calendar.MONTH);
					int currDay = cal.get(Calendar.DAY_OF_MONTH);
					cal.setTime(dateBD); // now born date
					int bornMonth = cal.get(Calendar.MONTH);
					int bornDay = cal.get(Calendar.DAY_OF_MONTH);
					isUserBirthDay = (bornMonth==currMonth) && (currDay == bornDay);					
				}
					
				//if (DateUtils.isSameDay(DateUtils.addDays(roundDate, -2), now) || DateUtils.isSameDay(DateUtils.addDays(roundDate, -1), now)) {
				//Check it On Thursdays (3 days before round)
				if (DateUtils.isSameDay(DateUtils.addDays(roundDate, -3), now)) {
					log.debug("execute sending mail");
					//CHECK FOR ALL USER COMPANIES
					List<RolCompany> listRols = dao.getRols(userAlterQ);
					for (RolCompany rolCompany : listRols)
					{
						isUserWithoutBets = false;
						
						if(rolCompany.getRol() == RolNameEnum.ROL_USER.getValue()){
						
							// or don't have enough money for special bets
							RoundBets roundBets = betDao.findAllUserBets(adminData.getSeason(), adminData.getRound(), userAlterQ.getId(), rolCompany.getCompany());
							
							

							if (roundBets == null)
								isUserWithoutBets = true;
							
							numApuestas = 0;
							List<Bet> lSpecialBets = userAlterQ.getSpecialBets();
							
							if (lSpecialBets != null){
								for (Bet bet : lSpecialBets){
									numApuestas+= bet.getNumBets();
								}
							}
							
							if (numApuestas==0)
								numApuestas++;
							else
								isUserWithoutBets = false;
							
							
							double calculatePrize=(lSpecialBets == null) ? 0 : numApuestas*adminData.getPrizeBet();
							fUserBalance = new Float(userAlterQ.getBalance()).floatValue();
							isUserWithoutMoney = (calculatePrize > fUserBalance);
							
							if (isUserWithoutMoney) {
								MailQueueDto mailDto=new MailQueueDto();
								//for purpouse test rewrite mail in environment not PRO
								if(!StringUtils.contains(CoreUtils.getCurrentHostName(),"pro")){
									userAlterQ.setId("quinielagold@gmail.com");
								}
								mailDto.setUser(userAlterQ);
								mailDto.setType(QueueMailEnum.Q_WITHOUTMONEYMAIL);
			
								processMailQueue.process(mailDto);
							}
			
							if ((isUserWithoutBets) && (fUserBalance > 0)){
								//user without bets in this company
								Company co = new Company();
								co.setCompany(rolCompany.getCompany());
								
								MailQueueDto mailDto=new MailQueueDto();
								//for purpouse test rewrite mail in environment not PRO
								if(!StringUtils.contains(CoreUtils.getCurrentHostName(),"pro")){
									userAlterQ.setId("quinielagold@gmail.com");
								}
								mailDto.setUser(userAlterQ);
								mailDto.setType(QueueMailEnum.Q_WITHOUTBETMAIL);
								mailDto.setCompany(co);
			
								processMailQueue.process(mailDto);
							}
							
							log.debug(userAlterQ.getId() + ":bets:" + ((userAlterQ.getSpecialBets() == null) ? 0 : userAlterQ.getSpecialBets().size()));
						}
					}
		
				} 
				if(isUserBirthDay){
					//User birthday
					MailQueueDto mailDto=new MailQueueDto();
//					//for purpouse test rewrite mail in environment not PRO
					if(!StringUtils.contains(CoreUtils.getCurrentHostName(),"pro")){
						userAlterQ.setId("quinielagold@gmail.com");
					}
					mailDto.setUser(userAlterQ);
					mailDto.setType(QueueMailEnum.Q_BIRTHDAYMAIL);

					processMailQueue.process(mailDto);
					
				}
				else {
					log.debug("not sending mail");
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
