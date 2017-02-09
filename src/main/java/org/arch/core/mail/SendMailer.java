package org.arch.core.mail;

import java.io.StringWriter;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.alterq.domain.Prize;
import org.alterq.domain.UserAlterQ;
import org.alterq.dto.MailQueueDto;
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

	private String from = "QuiniGold";

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

	public void sendJoinToCompany(MailQueueDto mailQueue, String joinToLink) {
		MimeMessage message = mailSender.createMimeMessage();

		// TODO create a mail to send a user to Join a company
		MimeMessageHelper helper;
		try {
			Template template = velocityEngine.getTemplate("./templates/joinToCompanyMail.vm");
			
			VelocityContext velocityContext = new VelocityContext();
			velocityContext.put("nombreCompany", mailQueue.getCompany().getNick());
			velocityContext.put("joinToLink", joinToLink);
			StringWriter stringWriter = new StringWriter();

			template.merge(velocityContext, stringWriter);

			helper = new MimeMessageHelper(message, true);
			helper.setFrom(new InternetAddress(from, "QuiniGold"));
			helper.setTo(mailQueue.getUser().getId());
			helper.setSubject("QuiniGold - Invitación a Grupo");

			helper.setText(stringWriter.toString(), true);

			log.debug("body:" + stringWriter.toString());

			mailSender.send(message);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void sendForgotMail(UserAlterQ userAlterQ) {
		MimeMessage message = mailSender.createMimeMessage();

		// use the true flag to indicate you need a multipart message
		MimeMessageHelper helper;
		try {
			Template template = velocityEngine.getTemplate("./templates/forgetPasswordMail.vm");

			VelocityContext velocityContext = new VelocityContext();
			velocityContext.put("newPassword", userAlterQ.getPwd());
			StringWriter stringWriter = new StringWriter();

			template.merge(velocityContext, stringWriter);

			helper = new MimeMessageHelper(message, true);
			//helper.setFrom(from);
			helper.setFrom(new InternetAddress(from, "QuiniGold"));
			helper.setTo(userAlterQ.getId());
			helper.setSubject("QuiniGold - Recordatorio Password");
			

			helper.setText(stringWriter.toString(), true);

			log.debug("body:" + stringWriter.toString());

			// FileSystemResource file = new FileSystemResource(new
			// File("D:\\temp\\logo_1035_255.png"));
			// helper.addInline("logo_1035_205", file);

			mailSender.send(message);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendDailyMail(UserAlterQ userAlterQ) {
		MimeMessage message = mailSender.createMimeMessage();

		// use the true flag to indicate you need a multipart message
		MimeMessageHelper helper;
		try {
			Template template = velocityEngine.getTemplate("./templates/dailyMail.vm");

			VelocityContext velocityContext = new VelocityContext();
			velocityContext.put("newPassword", userAlterQ.getPwd());
			StringWriter stringWriter = new StringWriter();

			template.merge(velocityContext, stringWriter);

			helper = new MimeMessageHelper(message, true);
			helper.setFrom(new InternetAddress(from, "QuiniGold"));
			helper.setTo(userAlterQ.getId());
			helper.setSubject("forgotPwd");

			helper.setText(stringWriter.toString(), true);

			log.debug("body:" + stringWriter.toString());

			// FileSystemResource file = new FileSystemResource(new
			// File("D:\\temp\\logo_1035_255.png"));
			// helper.addInline("logo_1035_205", file);

//			mailSender.send(message);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void sendResultsMail(String CCOusers, int round, float jackPot, float betReward, double rewardDivided, List<Prize> prizes) {
		MimeMessage message = mailSender.createMimeMessage();

		// use the true flag to indicate you need a multipart message
		MimeMessageHelper helper;
		try {
			Template template = velocityEngine.getTemplate("./templates/resultsMail.vm");

			VelocityContext velocityContext = new VelocityContext();
			velocityContext.put("resultBoleto", round);
			for (Prize prize : prizes) {

				velocityContext.put("resultAciertos" + prize.getId(), prize.getCount());
				velocityContext.put("resultPremio" + prize.getId(), prize.getAmount());
				velocityContext.put("resultTotal" + prize.getId(), prize.getCount() * prize.getAmount());
			}
			velocityContext.put("resultBote", jackPot);
			velocityContext.put("resultTotal", betReward + jackPot);
			velocityContext.put("resultReparto", String.format("%.2f", rewardDivided));

			StringWriter stringWriter = new StringWriter();

			template.merge(velocityContext, stringWriter);

			helper = new MimeMessageHelper(message, true);
			helper.setFrom(new InternetAddress(from, "QuiniGold"));
			// helper.setTo(userAlterQ.getId());
			helper.setBcc(CCOusers);
			helper.setSubject("QuiniGold - Resumen Premios");

			helper.setText(stringWriter.toString(), true);

			log.debug("body:" + stringWriter.toString());

			// FileSystemResource file = new FileSystemResource(new
			// File("D:\\temp\\logo_1035_255.png"));
			// helper.addInline("logo_1035_205", file);

			mailSender.send(message);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void sendResultUserMail(String CCOusers, int round, float betReward, List<Prize> prizes) {
		MimeMessage message = mailSender.createMimeMessage();

		// use the true flag to indicate you need a multipart message
		MimeMessageHelper helper;
		try {
			Template template = velocityEngine.getTemplate("./templates/resultUserMail.vm");

			VelocityContext velocityContext = new VelocityContext();
			velocityContext.put("resultBoleto", round);
			for (Prize prize : prizes) {

				velocityContext.put("resultAciertos" + prize.getId(), prize.getCount());
				velocityContext.put("resultPremio" + prize.getId(), prize.getAmount());
				velocityContext.put("resultTotal" + prize.getId(), prize.getCount() * prize.getAmount());
			}
			velocityContext.put("resultTotal", betReward);

			StringWriter stringWriter = new StringWriter();

			template.merge(velocityContext, stringWriter);

			helper = new MimeMessageHelper(message, true);
			helper.setFrom(new InternetAddress(from, "QuiniGold"));
			// helper.setTo(userAlterQ.getId());
			helper.setBcc(CCOusers);
			helper.setSubject("QuiniGold - Boleto Premiado");

			helper.setText(stringWriter.toString(), true);

			log.debug("body:" + stringWriter.toString());

			mailSender.send(message);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void sendWithoutMoneyMail(UserAlterQ userAlterQ) {
		MimeMessage message = mailSender.createMimeMessage();
		float balance;

		// use the true flag to indicate you need a multipart message
		MimeMessageHelper helper;
		try {
			Template template = velocityEngine.getTemplate("./templates/withoutMoneyMail.vm");

			VelocityContext velocityContext = new VelocityContext();
			balance = new Float(userAlterQ.getBalance()).floatValue();
			velocityContext.put("currentBalance", String.format("%.2f", balance));
			StringWriter stringWriter = new StringWriter();

			template.merge(velocityContext, stringWriter);

			helper = new MimeMessageHelper(message, true);
			helper.setFrom(new InternetAddress(from, "QuiniGold"));
			helper.setTo(userAlterQ.getId());
			helper.setSubject("QuiniGold - Saldo Agotado");

			helper.setText(stringWriter.toString(), true);

			log.debug("body:" + stringWriter.toString());

			// FileSystemResource file = new FileSystemResource(new
			// File("D:\\temp\\logo_1035_255.png"));
			// helper.addInline("logo_1035_205", file);

			mailSender.send(message);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void sendWithoutBetMail(UserAlterQ userAlterQ) {
		MimeMessage message = mailSender.createMimeMessage();

		// use the true flag to indicate you need a multipart message
		MimeMessageHelper helper;
		try {
			Template template = velocityEngine.getTemplate("./templates/withoutBetMail.vm");

			VelocityContext velocityContext = new VelocityContext();
			StringWriter stringWriter = new StringWriter();

			template.merge(velocityContext, stringWriter);

			helper = new MimeMessageHelper(message, true);
			helper.setFrom(new InternetAddress(from, "QuiniGold"));
			helper.setTo(userAlterQ.getId());
			helper.setSubject("QuiniGold - Recordatorio Apuesta");

			helper.setText(stringWriter.toString(), true);

			log.debug("body:" + stringWriter.toString());

			// FileSystemResource file = new FileSystemResource(new
			// File("D:\\temp\\logo_1035_255.png"));
			// helper.addInline("logo_1035_205", file);

			mailSender.send(message);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void sendBirthdayMail(UserAlterQ userAlterQ) {
		MimeMessage message = mailSender.createMimeMessage();

		// use the true flag to indicate you need a multipart message
		MimeMessageHelper helper;
		try {
			Template template = velocityEngine.getTemplate("./templates/birthdayMail.vm");

			VelocityContext velocityContext = new VelocityContext();
			velocityContext.put("userName", userAlterQ.getName());
			StringWriter stringWriter = new StringWriter();

			template.merge(velocityContext, stringWriter);

			helper = new MimeMessageHelper(message, true);
			helper.setFrom(new InternetAddress(from, "QuiniGold"));
			helper.setTo(userAlterQ.getId());
			helper.setSubject("QuiniGold - Felicidades");

			helper.setText(stringWriter.toString(), true);

			log.debug("body:" + stringWriter.toString());

			// FileSystemResource file = new FileSystemResource(new
			// File("D:\\temp\\logo_1035_255.png"));
			// helper.addInline("logo_1035_205", file);

			mailSender.send(message);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void sendFinalBetMail(String CCOusers, int round, int season, String betID, float betPrize, int numBets, String linkBet) {
		MimeMessage message = mailSender.createMimeMessage();

		// use the true flag to indicate you need a multipart message
		MimeMessageHelper helper;
		try {
			Template template = velocityEngine.getTemplate("./templates/finalBetMail.vm");

			VelocityContext velocityContext = new VelocityContext();
			velocityContext.put("finalBetRound", round);
			velocityContext.put("finalBetTemporada", season);
			velocityContext.put("finalBetID", betID);
			velocityContext.put("finalBetPrize", betPrize);
			velocityContext.put("finalBetNumBets", numBets);
			velocityContext.put("finalBetLinkBet", linkBet);
			StringWriter stringWriter = new StringWriter();

			template.merge(velocityContext, stringWriter);

			helper = new MimeMessageHelper(message, true);
			helper.setFrom(new InternetAddress(from, "QuiniGold"));
			helper.setBcc(CCOusers);
			helper.setSubject("QuiniGold - Boleto " + round + " Temporada " + season + "/" + (season - 2000 + 1));

			helper.setText(stringWriter.toString(), true);

			log.debug("body:" + stringWriter.toString());

			mailSender.send(message);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
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
