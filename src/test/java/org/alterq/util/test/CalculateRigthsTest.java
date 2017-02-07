package org.alterq.util.test;


import org.alterq.util.CalculateRigths;
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
	public void test01Calculate() {
		int rdo[] = {0,0,0,0,0,0};
		CalculateRigths aux = new CalculateRigths();
		rdo = aux.calculate("1111111111111100", "1111111111111100", "NNNNNNNNNNNNNNNN", 0);

		Assert.assertNotNull(rdo);
	}
	@Test
	public void test02Calculate() {
		int rdo[] = {0,0,0,0,0,0};
		CalculateRigths aux = new CalculateRigths();
		rdo = aux.calculate("1111111111111100", "3567111111111100", "NNNNNNNNNNNNNNNN", 0);
		
		Assert.assertNotNull(rdo);
	}
	@Test
	public void test03Calculate() {
		int rdo[] = {0,0,0,0,0,0};
		CalculateRigths aux = new CalculateRigths();
		rdo = aux.calculate("1111111111111100", "11111111111111ff", "NNNNNNNNNNNNNNNN", 0);
		
		Assert.assertNotNull(rdo);
	}
	@Test
	public void test04Desglose() {
		String rdo[]; 
		CalculateRigths aux = new CalculateRigths();
		rdo = aux.unfolding("77771111111111ff", "TTTTNNNNNNNNNNNN", 1);
		
		for (int i = 0; i < rdo.length; i++) {
			String linea = rdo[i];
			System.out.println(linea);
			
		}
		
		
		Assert.assertNotNull(rdo);
	}
	@Test
	public void test05Calculate() {
		int rdo[] = {0,0,0,0,0,0};
		CalculateRigths aux = new CalculateRigths();
		rdo = aux.calculate("4211214142442444", "4471714744764422", "NNTNTNNTNNTNNN", 1);
		
		Assert.assertNotNull(rdo);
	}
}
