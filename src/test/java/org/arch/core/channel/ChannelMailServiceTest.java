package org.arch.core.channel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.core.PollableChannel;
import org.springframework.integration.message.GenericMessage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/applicationContext.xml" })
public class ChannelMailServiceTest {
	@Autowired
	MessageChannel sendingChannel;
	@Autowired
	PollableChannel receivingChannel;

	@Test
	public void testSendMail() throws Exception {
		GenericMessage<String> message = new GenericMessage<String>("esta impresión");
		sendingChannel.send(message);
		
		
	}

}
