package org.arch.core.file;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.MethodSorters;

@RunWith(BlockJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HeaderBetElectronicTest {

	@Test
	public void test01Cabecera() {
		HeaderBetElectronicFile cb = new HeaderBetElectronicFile();
		cb.setIdDelegacion("64");
		cb.setIdReceptor("65428");

		System.out.println("calculoCaracterControl(64,65428)=" + cb.calculoCaracterControl());

	}

}
