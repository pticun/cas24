package org.alterq.util.test;


import org.alterq.util.*;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/applicationContext.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CalculateRigthsTest {

	@Test
	public void test01Calculate01() {
		int rdo[] = {0,0,0,0,0,0};
		CalculateRigths aux = new CalculateRigths();
		rdo = aux.Calculate("1111111111111100", "1111111111111100", "NNNNNNNNNNNNNNNN", 0);
		
		Assert.assertNull(rdo);
	}
	
}
