package org.alterq.test;

import org.alterq.domain.Person;
import org.alterq.repo.PersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/META-INF/spring/applicationContext.xml" })
public class MongoDBTest {
	@Autowired
	private PersonService personService;

	@Test
	public void testRegister() {
		Person p = new Person();
		p.setName("santiago con apellido");
		p.setLastName("apellido");
		p.setAge(39);
		personService.addPerson(p);
		return;
	}
	
	
}
