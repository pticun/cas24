package org.arch.core.mail;

import java.io.StringWriter;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.alterq.domain.UserAlterQ;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class SendMailer {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private VelocityEngine velocityEngine;

	private String from;

	public JavaMailSender getMailSender() {
		return mailSender;
	}

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public VelocityEngine getVelocityEngine() {
		return velocityEngine;
	}

	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}

	public void sendForgotMail(UserAlterQ userAlterQ) {
		MimeMessage message = mailSender.createMimeMessage();

		// use the true flag to indicate you need a multipart message
		MimeMessageHelper helper;
		try {
			Template template = velocityEngine.getTemplate("./templates/forgetPassword.vm");
			
			VelocityContext velocityContext = new VelocityContext();
			velocityContext.put("newPassword", userAlterQ.getPwd());
			StringWriter stringWriter = new StringWriter();

			template.merge(velocityContext, stringWriter);

			helper = new MimeMessageHelper(message, true);
			helper.setFrom(from);
			helper.setTo(userAlterQ.getId());
			helper.setSubject("forgotPwd");
			
			
			helper.setText(stringWriter.toString(), true);

			log.debug("body:"+stringWriter.toString());

//			FileSystemResource file = new FileSystemResource(new File("D:\\temp\\logo_1035_255.png"));
//			helper.addInline("logo_1035_205", file);

			mailSender.send(message);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

}
