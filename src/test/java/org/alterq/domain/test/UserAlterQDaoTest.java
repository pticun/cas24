package org.alterq.domain.test;

import java.util.List;

import junit.framework.Assert;

import org.alterq.domain.UserAlterQ;
import org.alterq.repo.UserAlterQDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/applicationContext.xml" })
public class UserAlterQDaoTest {
	@Autowired
	private UserAlterQDao dao;

	@Test
	public void testFindById() {
		UserAlterQ userAlterQ = dao.findById("52768270b6076017b653980f");

		Assert.assertEquals("John Smith", userAlterQ.getName());
		Assert.assertEquals("john.smith@mailinator.com", userAlterQ.getEmail());
		Assert.assertEquals("2125551212", userAlterQ.getPhoneNumber());
		return;
	}

	@Test
	public void testFindByEmail() {
		UserAlterQ userAlterQ = dao.findByEmail("john.smith@mailinator.com");

		Assert.assertEquals("John Smith", userAlterQ.getName());
		Assert.assertEquals("john.smith@mailinator.com", userAlterQ.getEmail());
		Assert.assertEquals("2125551212", userAlterQ.getPhoneNumber());
		return;
	}

	@Test
	public void testRegister() {
		UserAlterQ userAlterQ = new UserAlterQ();
		userAlterQ.setEmail("jane.doe@mailinator.com");
		userAlterQ.setName("Jane Doe");
		userAlterQ.setPhoneNumber("2125552121");

		dao.register(userAlterQ);
		String id = userAlterQ.getId();
		Assert.assertNotNull(id);

		Assert.assertEquals(2, dao.findAllOrderedByName().size());
		UserAlterQ userAlterQNew = dao.findById(id);

		Assert.assertEquals("Jane Doe", userAlterQNew.getName());
		Assert.assertEquals("jane.doe@mailinator.com", userAlterQNew.getEmail());
		Assert.assertEquals("2125552121", userAlterQNew.getPhoneNumber());
		return;
	}

	@Test
	public void testFindAllOrderedByName() {
		UserAlterQ userAlterQ = new UserAlterQ();
		userAlterQ.setEmail("jane.doe@mailinator.com");
		userAlterQ.setName("Jane Doe");
		userAlterQ.setPhoneNumber("2125552121");
		dao.register(userAlterQ);

		List<UserAlterQ> userList = dao.findAllOrderedByName();
		Assert.assertEquals(2, userList.size());
		UserAlterQ userAlterQNew = userList.get(0);

		Assert.assertEquals("Jane Doe", userAlterQNew.getName());
		Assert.assertEquals("jane.doe@mailinator.com", userAlterQNew.getEmail());
		Assert.assertEquals("2125552121", userAlterQNew.getPhoneNumber());
		return;
	}
}
