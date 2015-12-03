package org.alterq.scheduler;

import java.util.Date;
import java.util.List;

import org.alterq.domain.AdminData;
import org.alterq.domain.Bet;
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
import org.apache.commons.lang3.time.DateUtils;
import org.arch.core.channel.ProcessMailQueue;
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
		log.debug("Method executed at every day.");
		AdminData adminData = adminDataDao.findById(AlterQConstants.DEFECT_ADMINDATA);
		Round roundBean = roundDao.findBySeasonRound(adminData.getSeason(), adminData.getRound());

		Date roundDate = roundBean.getDateRound();

		Date now = new Date();

		if (DateUtils.isSameDay(DateUtils.addDays(roundDate, -2), now) || DateUtils.isSameDay(DateUtils.addDays(roundDate, -1), now)) {
			log.debug("execute sending mail");
			List<UserAlterQ> allUser = dao.findAllUserActive();
			for (UserAlterQ userAlterQ : allUser) {
				// check if user don't have bets
				// or don't have enough money for special bets
				RoundBets roundBets = betDao.findAllUserBets(adminData.getSeason(), adminData.getRound(), userAlterQ.getId(), 1);

				numApuestas = 0;
				List<Bet> lSpecialBets = userAlterQ.getSpecialBets();
				
				if (lSpecialBets != null){
					for (Bet bet : lSpecialBets){
						numApuestas+= bet.getNumBets();
					}
				}
				if (numApuestas==0) numApuestas++;
				
				double calculatePrize=(lSpecialBets == null) ? 0 : numApuestas*adminData.getPrizeBet();
				if (roundBets == null || calculatePrize>new Float(userAlterQ.getBalance()).floatValue()) {
					//send mail 
//					GenericMessage<UserAlterQ> messageUser = new GenericMessage<UserAlterQ>(userAlterQ);
//					//TODO
//					//for purpouse test rewrite mail
//					sendingChannel.send(messageUser);
					MailQueueDto mailDto=new MailQueueDto();
					userAlterQ.setId("racsor@gmail.com");
					mailDto.setUser(userAlterQ);
					mailDto.setType(QueueMailEnum.Q_DAILYMAIL);
//					GenericMessage<MailQueueDto> messageUser = new GenericMessage<MailQueueDto>(mailDto);

					processMailQueue.process(mailDto);
					
				}

				log.debug(userAlterQ.getId() + ":bets:" + ((userAlterQ.getSpecialBets() == null) ? 0 : userAlterQ.getSpecialBets().size()));
			}

		} else {
			log.debug("not sending mail");
		}

	}

}
