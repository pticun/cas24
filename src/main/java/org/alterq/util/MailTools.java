package org.alterq.util;

import java.lang.Math;
import java.util.ArrayList;
import java.util.List;

import org.alterq.domain.AdminData;
import org.alterq.domain.Bet;
import org.alterq.domain.Prize;
import org.alterq.domain.RoundBets;
import org.alterq.domain.UserAlterQ;
import org.alterq.dto.AlterQConstants;
import org.alterq.dto.MailQueueDto;
import org.alterq.repo.AdminDataDao;
import org.alterq.repo.RoundBetDao;
import org.alterq.repo.UserAlterQDao;
import org.alterq.util.enumeration.BetTypeEnum;
import org.alterq.util.enumeration.QueueMailEnum;
import org.apache.commons.lang3.StringUtils;
import org.arch.core.channel.ProcessMailQueue;
import org.arch.core.util.CoreUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MailTools{
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private AdminDataDao adminDataDao;
	@Autowired
	private RoundBetDao roundBetDao;
	@Autowired
	private UserAlterQDao userAlterQDao;
	@Autowired
	private BetTools betTools;

	@Autowired
	ProcessMailQueue processMailQueue;
	

	public ArrayList<String> getCCOFinalBet(int company, int season, int round){
		String CCO = "";
		ArrayList<String> CCOArray = new ArrayList<String>();

		RoundBets bean = roundBetDao.findRoundBetWithBets(season, round, company);
		
		List<Bet> lBets = bean.getBets();

		for (Bet bet : lBets) {
			if ((bet.getType() == BetTypeEnum.BET_NORMAL.getValue()))
			{
				//Avoid repeat users
				if (CCO.indexOf(bet.getUser()) < 0){
					CCO += bet.getUser() + ";";
					CCOArray.add(bet.getUser());
				}
			}
		}
		log.debug("getCCOFinalBet: CCO:" + CCO);
		
		return CCOArray;
	}
	
	public ArrayList<String> getCCOUsersWithoutBet(int company, int season, int round){
		String CCO = "";
		ArrayList<String> CCOArray = new ArrayList<String>();

		ArrayList<String> UserWithBets = new ArrayList<String>();
		
		UserWithBets = getCCOFinalBet(company, season, round);
		
		List<UserAlterQ> lusers = userAlterQDao.findUsersCompany(company);
		for (UserAlterQ user : lusers){
	        if(UserWithBets.indexOf(user.getId()) == -1) {
				CCO+= user.getId() + ";";
				CCOArray.add(user.getId());
	        }
		}

		log.debug("getCCOUsersWithoutBet: CCO:" + CCO);
		
		return CCOArray;
	}

	public ArrayList<String> getCCOUsersWithoutMoney(){
		String CCO = "";
		ArrayList<String> CCOArray = new ArrayList<String>();
		int numApuestas;
		
		
		List<UserAlterQ> lusers = userAlterQDao.findAllOrderedByName();
		
		for (UserAlterQ user : lusers){
			numApuestas = 0;
			
			List<Bet> lSpecialBets = user.getSpecialBets();
			
			if (lSpecialBets != null){
				for (Bet bet : lSpecialBets){
					numApuestas+= bet.getNumBets();
				}
			}
			if (numApuestas==0) numApuestas++;
			
			float balance = new Float(user.getBalance()).floatValue();
	        
	        
			if(balance < (numApuestas * betTools.getPriceBet())) {
				CCO+= user.getId() + ";";
				CCOArray.add(user.getId());
	        }
		}		
		log.debug("getCCOUsersWithoutMoney: CCO:" + CCO);
		
		return CCOArray;
	}
	
	public void sendMailUsersWithoutMoney(){
		String CCO = "";
		int numApuestas;
		
		List<UserAlterQ> lusers = userAlterQDao.findAllOrderedByName();
		
		for (UserAlterQ user : lusers){
			numApuestas = 0;
			
			List<Bet> lSpecialBets = user.getSpecialBets();
			
			if (lSpecialBets != null){
				for (Bet bet : lSpecialBets){
					numApuestas+= bet.getNumBets();
				}
			}
			if (numApuestas==0) numApuestas++;
			
			float balance = new Float(user.getBalance()).floatValue();
	        
	        
			if(balance < (numApuestas * betTools.getPriceBet())) {
				MailQueueDto mailDto=new MailQueueDto();
				
				if(!StringUtils.contains(CoreUtils.getCurrentHostName(),"pro")){
					user.setId("quinielagold@gmail.com");
				}
				mailDto.setUser(user);
				mailDto.setType(QueueMailEnum.Q_WITHOUTMONEYMAIL);
//				GenericMessage<MailQueueDto> messageUser = new GenericMessage<MailQueueDto>(mailDto);

				processMailQueue.process(mailDto);
	        }
		}		
		
		return;
	} 
}