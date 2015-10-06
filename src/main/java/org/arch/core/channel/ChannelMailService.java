package org.arch.core.channel;

import org.alterq.domain.UserAlterQ;
import org.arch.core.mail.SendMail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class ChannelMailService {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	SendMail sendMail;

	public void printService(String print){
		log.debug("es la salida:"+print);
	}
	public void sendMailForgotPassword(UserAlterQ userAlterQ){
		log.debug("ChannelMailService:forgotPwd():"+userAlterQ.getId());
		sendMail.sendForgotMail(userAlterQ.getId(), "CAMBIO PWDALTERQ", userAlterQ.getPwd());
	}
}
