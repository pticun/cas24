package org.arch.core.configuration;

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
public class PropertiesConfigurationTest {
	protected static Logger logger = LoggerFactory.getLogger(PropertiesConfigurationTest.class);

	@Autowired
	@Qualifier("propertiesConfiguration")
	private PropertiesConfiguration propertiesConfiguration;

	@Test
	public void configureTest() {
		final String username = propertiesConfiguration.getProperty("app.jdbc.username");
		logger.debug("username:" + username);
	}

}
