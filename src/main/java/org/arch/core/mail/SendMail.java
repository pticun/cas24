package org.arch.core.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class SendMail {
	@Autowired
	private MailSender mailSender;

	@Autowired
	private SimpleMailMessage templateMailMessage;
	
	public void setSimpleMailMessage(SimpleMailMessage templateMailMessage) {
		this.templateMailMessage = templateMailMessage;
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
	
	public void sendMailWithTemplate(String dear, String content) {			 
		   SimpleMailMessage message = new SimpleMailMessage(templateMailMessage);
	 	   message.setText(String.format( templateMailMessage.getText(), dear, content));
	 	   mailSender.send(message);
	}
}
