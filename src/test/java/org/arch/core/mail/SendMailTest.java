package org.arch.core.mail;

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

	@Test
	public void testSendMail() throws Exception {
		sendMail.sendMail("goldbittledev@gmail.com", "scabellog@gmail.com", "FELICIDADES", "FELICIDADES DESDE ALTERQ");
		sendMail.sendMailWithTemplate("ALTERQ", "Cumpeaños feliz desde ALTERQ");
		return;
	}

}
