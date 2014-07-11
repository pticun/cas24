package org.arch.core.i18n.resources;

import org.arch.core.config.PropertiesConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/applicationContext.xml" })
public class MessageLocalizedResourcesTest {
	protected static Logger logger = LoggerFactory.getLogger(MessageLocalizedResourcesTest.class);

	@Autowired
	@Qualifier("messageLocalizedResources")
	private MessageLocalizedResources messageLocalizedResources;

	@Test
	public void configureTest() {
		final String username = messageLocalizedResources.resolveLocalizedErrorMessage("USER_NOT_VALIDATE");
		logger.debug("message:" + username);
	}

}
