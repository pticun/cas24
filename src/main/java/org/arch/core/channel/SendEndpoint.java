/*
 * Copyright 2007-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.arch.core.channel;

import java.util.List;

import org.alterq.domain.AdminData;
import org.alterq.domain.Bet;
import org.alterq.domain.Company;
import org.alterq.domain.Prize;
import org.alterq.domain.RoundBets;
import org.alterq.domain.UserAlterQ;
import org.alterq.dto.AlterQConstants;
import org.alterq.dto.MailQueueDto;
import org.alterq.repo.AdminDataDao;
import org.alterq.repo.CompanyDao;
import org.apache.commons.lang3.StringUtils;
import org.arch.core.mail.SendMailer;
import org.arch.core.util.CoreUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.message.GenericMessage;

/**
 * The endpoint for a delivery order.
 *
 * @author ALTERQ
 */
@MessageEndpoint
public class SendEndpoint {

    final Logger logger = LoggerFactory.getLogger(SendEndpoint.class);
	@Autowired
	SendMailer sendMailer;
	
	@Autowired
	private AdminDataDao adminDataDao;	
	
	@Autowired
	private CompanyDao companyDao;	
	

    /**
     * Process a delivery order for sending by mail.
     */
	public void sendingJoinToCompany(GenericMessage<MailQueueDto> message) {
		MailQueueDto mailQueue=  message.getPayload();
		UserAlterQ user=mailQueue.getUser();
		String joinToLink="";
		if(!StringUtils.contains(CoreUtils.getCurrentHostName(),"pro")){
			//para pruebas
			joinToLink = "http://localhost:8080/quinimobile/myaccount/"+user.getId()+"/rolcompany";
		}
		else{
			joinToLink = "http://www.quinigold.com/myaccount/"+user.getId()+"/rolcompany";
		}
		sendMailer.sendJoinToCompany(mailQueue,joinToLink);
	}
	/**
	 * Process a delivery order for sending by mail.
	 */
	public void sendingDailyMail(GenericMessage<MailQueueDto> message) {
		MailQueueDto mailQueue=  message.getPayload();
		UserAlterQ userAlterQ=mailQueue.getUser();
		
		sendMailer.sendDailyMail(userAlterQ);
	}
	/**
	 * Process a delivery order for sending by mail.
	 */
	public void sendingForgotMail(GenericMessage<MailQueueDto> message) {
		MailQueueDto mailQueue=  message.getPayload();
		UserAlterQ userAlterQ=mailQueue.getUser();
		
		sendMailer.sendForgotMail(userAlterQ);
	}
	/**
	 * Process a delivery order for sending by mail.
	 */
	public void sendingResultsMail(GenericMessage<MailQueueDto> message) {
		MailQueueDto mailQueue=  message.getPayload();
		//UserAlterQ userAlterQ=mailQueue.getUser();
		
		//sendMailer.sendResultsMail(cco, round, jackPot, betReward, rewardDivided, lPrizes);
		String cco=mailQueue.getCco();
		RoundBets roundBet=mailQueue.getRoundBet();
		Bet bet = roundBet.getBets().get(0);
		double rewardDivided = (roundBet.getReward() + roundBet.getJackpot()) / bet.getNumBets();
		List<Prize> lPrizes = bet.getPrizes();
		
		sendMailer.sendResultsMail(cco, roundBet.getRound(), roundBet.getJackpot(), roundBet.getReward(), rewardDivided, lPrizes);
	}

	/**
	 * Process a delivery order for sending by mail.
	 */
	public void sendingResultUserMail(GenericMessage<MailQueueDto> message) {
		MailQueueDto mailQueue=  message.getPayload();
		
		String cco=mailQueue.getCco();
		RoundBets roundBet=mailQueue.getRoundBet();
		Bet bet = roundBet.getBets().get(0);
		List<Prize> lPrizes = bet.getPrizes();
		
		sendMailer.sendResultUserMail(cco, roundBet.getRound(), roundBet.getReward(), lPrizes);
	}

	/**
	 * Process a delivery order for sending by mail.
	 */
	public void sendingWithoutMoneyMail(GenericMessage<MailQueueDto> message) {
		MailQueueDto mailQueue=  message.getPayload();
		UserAlterQ userAlterQ=mailQueue.getUser();
		
		sendMailer.sendWithoutMoneyMail(userAlterQ);
	}

	/**
	 * Process a delivery order for sending by mail.
	 */
	public void sendingWithoutBetMail(GenericMessage<MailQueueDto> message) {
		MailQueueDto mailQueue=  message.getPayload();
		UserAlterQ userAlterQ=mailQueue.getUser();
		
		sendMailer.sendWithoutBetMail(userAlterQ);
	}

	/**
	 * Process a delivery order for sending by mail.
	 */
	public void sendingBirthdayMail(GenericMessage<MailQueueDto> message) {
		MailQueueDto mailQueue=  message.getPayload();
		UserAlterQ userAlterQ=mailQueue.getUser();
		
		sendMailer.sendBirthdayMail(userAlterQ);
	}

	/**
	 * Process a delivery order for sending by mail.
	 */
	public void sendingFinalBetMail(GenericMessage<MailQueueDto> message) {
		MailQueueDto mailQueue=  message.getPayload();
		//UserAlterQ userAlterQ=mailQueue.getUser();
		
		String cco=mailQueue.getCco();
		RoundBets roundBet=mailQueue.getRoundBet();
		String betID="a cambiar";
		int numBets=0;
		String linkBet="a cambbiar";
		
		AdminData ad = adminDataDao.findById(AlterQConstants.DEFECT_COMPANY);
		float priceBet = ad.getPrizeBet();
		
		Bet bet = roundBet.getBets().get(0);
		

		betID = "ID" + bet.getId().toUpperCase().substring((bet.getId().length() - 6),bet.getId().length());
		
		numBets = (int) (roundBet.getPrice() / priceBet);
		
		Company myCompany = companyDao.findByCompany(bet.getCompany());
		if(!StringUtils.contains(CoreUtils.getCurrentHostName(),"pro")){
			//para pruebas
			linkBet = "http://localhost:8080/quinimobile/betDetail/"+bet.getCompany()+"/"+myCompany.getNick()+"/"+bet.getBet()+"/"+bet.getTypeReduction()+"/"+bet.getReduction();
		}
		else{
			linkBet = "http://www.quinigold.com/betDetail/"+bet.getCompany()+"/"+myCompany.getNick()+"/"+bet.getBet()+"/"+bet.getTypeReduction()+"/"+bet.getReduction();
		}
		
		sendMailer.sendFinalBetMail(cco, roundBet.getRound(), roundBet.getSeason(), betID, roundBet.getPrice(), numBets, linkBet);
		
		//sendMailer.sendFinalBetMail(CCOusers, round, season, betID, betPrize, numBets, linkBet);endFinalBetMail(userAlterQ);
	}
}
