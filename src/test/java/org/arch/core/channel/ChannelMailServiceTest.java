package org.arch.core.channel;

import org.alterq.domain.UserAlterQ;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.core.PollableChannel;
import org.springframework.integration.message.GenericMessage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/applicationContext.xml" })
public class ChannelMailServiceTest {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	MessageChannel sendingChannel;
	@Autowired
	PollableChannel receivingChannel;

	@Test
	public void testSendMail() throws Exception {
		GenericMessage<String> message = new GenericMessage<String>("esta impresión");
		sendingChannel.send(message);
		UserAlterQ user=new UserAlterQ();
		user.setId("racsor@gmail.com");
		user.setPwd("changePwd");
		GenericMessage<UserAlterQ> messageUser = new GenericMessage<UserAlterQ>(user);
		sendingChannel.send(messageUser);
	}

}
