package org.jboss.tools.example.springmvc.test;

import org.jboss.tools.example.springmvc.domain.Person;
import org.jboss.tools.example.springmvc.repo.PersonService;
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
	p.setName("nombre");
	personService.addPerson(p);
	return;
    }

}
