package org.arch.core.cryptographic;

import org.alterq.domain.SequenceId;
import org.alterq.repo.SequenceIdDao;
import org.alterq.util.enumeration.SequenceNameEnum;
import org.arch.core.crypto.Cryptographic;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/applicationContext.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public final class CryptographicTest {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SequenceIdDao dao;
	@Test
	public void test00Cryptographic() throws Exception {
		SequenceId bean = dao.findById(SequenceNameEnum.SECRET_KEY.getValue());
		Cryptographic cryp=new Cryptographic(bean.getSequence());
		String toEncrypt="---StringToEncrypt@mail.es--";
		log.debug("String toEncrypt:"+toEncrypt);
		String stringEncrypt=cryp.encrypt(toEncrypt);
		log.debug("encrypt:"+stringEncrypt);
		log.debug("decrypt:"+cryp.decrypt(stringEncrypt));
		return;
	}

}
