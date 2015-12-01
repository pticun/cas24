package org.arch.core.mail;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class SendMail {
	@Autowired
	private MailSender mailSender;

	@Autowired
	private SimpleMailMessage forgotPwdMailMessage;
	@Autowired
	private JavaMailSender htmlMailMessage;
	
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
	public void sendHtmlMail(String from, String to, String subject, String body) {	
		MimeMessage message = htmlMailMessage.createMimeMessage();
		
		// use the true flag to indicate you need a multipart message
		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(message, true);
			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(body,true);
			
//		helper.setText(String.format(helper.getMimeMessage().get, pwd));
			
			// let's attach the infamous windows Sample file (this time copied to c:/)
			FileSystemResource file = new FileSystemResource(new File("D:\\temp\\logo_1035_255.png"));
			helper.addInline("logo_1035_205", file);
			
			htmlMailMessage.send(message);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
