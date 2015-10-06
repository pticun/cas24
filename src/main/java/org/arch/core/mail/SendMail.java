package org.arch.core.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class SendMail {
	@Autowired
	private MailSender mailSender;

	@Autowired
	private SimpleMailMessage forgotPwdMailMessage;
	
	public void setSimpleMailMessage(SimpleMailMessage forgotPwdMailMessage) {
		this.forgotPwdMailMessage = forgotPwdMailMessage;
	}
	
	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}
 
	public void sendMail(String from, String to, String subject, String msg) {
 
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
 
		simpleMailMessage.setFrom(from);
		simpleMailMessage.setTo(to);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(msg);
		mailSender.send(simpleMailMessage);	
	}
	
	public void sendForgotMail(String to, String subject, String pwd) {			 
		   SimpleMailMessage message = new SimpleMailMessage(forgotPwdMailMessage);
		   message.setTo(to);
		   message.setSubject(subject);
	 	   message.setText(String.format( forgotPwdMailMessage.getText(), pwd));
	 	   mailSender.send(message);
	}
}
