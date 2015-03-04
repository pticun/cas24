package org.arch.core.file;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/applicationContext.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CabeceraBetElectronicTest {

	@Test
	public void test01Cabecera() {
		CabeceraBetElectronicFile cb = new CabeceraBetElectronicFile();
		cb.setIdDelegacion("64");
		cb.setIdReceptor("65428");

		System.out.println("calculoCaracterControl(64,65428)=" + cb.calculoCaracterControl());

	}

	@Test
	public void test02File() throws Exception {
		String homeDir = System.getProperty("user.home");
		BetElectronicFile betFile = new BetElectronicFile();
		File file = new File(homeDir + "/" + betFile.getNameFile());
		FileUtils.write(file, "UTF-8");

	}

}
