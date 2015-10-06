package org.arch.core.mail;

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

	@Test
	public void testSendMail() throws Exception {
		sendMail.sendMail("goldbittledev@gmail.com", "racsor@gmail.com", "FELICIDADES", "FELICIDADES DESDE ALTERQ");
		String pwd = RandomStringUtils.random(10, true, true);
		sendMail.sendForgotMail("racsor@gmail.com","CAMBIO PWDALTERQ", pwd);
		return;
	}

}
