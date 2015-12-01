package org.arch.core.mail;

import org.alterq.domain.UserAlterQ;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/applicationContext.xml" })
public class SendMailTest {
	@Autowired
	SendMail sendMail;
	@Autowired
	SendMailer sendMailer;

	public void testSendMail() throws Exception {
		sendMail.sendMail("goldbittledev@gmail.com", "racsor@gmail.com", "FELICIDADES", "FELICIDADES DESDE ALTERQ");
		String pwd = RandomStringUtils.random(10, true, true);
		sendMail.sendForgotMail("racsor@gmail.com","CAMBIO PWDALTERQ", pwd);
		return;
	}
	@Test
	public void testHtmlMail() throws Exception {
		String body="<html><body>" +
				"<img src=\"cid:logo_1035_205\" alt=\"\" width=\"1035\" height=\"255\" id=\"logo\"/>" +
				"<div class=\"contenedor\">" +
				" <p id=\"texto1\"> Hola amigos de Quinigold, os mandamos este correo para bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla </p>" +
				"<a href=\"http://www.google.es\" id=\"enlace\">Aqui teneis el enlace</a>" +
				"<br><br></div>" +
				"<br><br><br><br><br><br>" +
				"<div class=\"contenedor\">" +
				"<p id=\"texto2\"> Recordad que siempre que bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla</p>" +
				"</div></body></html>";
				
		UserAlterQ user=new UserAlterQ();
		user.setId("racsor@gmail.com");
		user.setPwd("newPwd");
		sendMailer.sendForgotMail(user);
		return;
	}

}
