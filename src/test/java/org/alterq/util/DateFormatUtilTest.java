package org.alterq.util;


import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.alterq.util.DateFormatUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/applicationContext.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DateFormatUtilTest {
	
	@Autowired
	DateFormatUtil dateFormatUtil;
	
	@Test
	public void test01ConvertIsoTimeToFormatDay() {
		String string1 = "2015-10-13T22:09:27.920Z";
		String result=dateFormatUtil.convertIsoTimeToFormatDay(string1);

		Assert.assertEquals(result,"13/10/2015");
	}
	@Test
	public void test02convertFormatDayToIsoTime() {
		String string1 = "04/07/2001";
		String result=dateFormatUtil.convertFormatDayToIsoTime(string1);
		
		System.out.println(result);

		Assert.assertEquals(result,"2001-07-04T00:00:00.000Z");
	}
}
