package org.jboss.tools.example.springmvc.test;

import org.jboss.tools.example.springmvc.domain.Jornada;
import org.jboss.tools.example.springmvc.repo.JornadaDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/applicationContext.xml" })
public class JornadaDaoTest {

	   @Autowired
	    private JornadaDao jornadaDao;

	    @Test
	    public void testFindByTemporadaJornada()
	    {
	        Jornada bean = jornadaDao.findByTemporadaJornada(2013, 9);
	        
	        Assert.assertEquals(2013, bean.getTemporada());
	        
	        
	        return;
	    }

}
