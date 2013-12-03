package org.alterq.mvc.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/applicationContext.xml","classpath:/spring/app-servlet.xml" })
public class BetControllerTest {

	@Autowired  
	private ApplicationContext applicationContext;  	
	@Autowired
	private org.alterq.mvc.BetController controller;

	@Before
	public void setup() {

	}

	@Test
	public void getAccount() throws Exception {
		//TODO add mock 
		System.out.println(controller.getLastJornada());
	}

}
