package org.arch.core.file;

import org.alterq.util.CalculateRigths;
import org.apache.commons.lang3.StringUtils;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.MethodSorters;

@RunWith(BlockJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BetElectronicFileTest {

	@Test
	public void test01BetElectronicFile() {
		
		HeaderBetElectronicFile cb = new HeaderBetElectronicFile();
		cb.setIdDelegacion("64");
		cb.setIdReceptor("65428");

		String rdo[]; 
		CalculateRigths aux = new CalculateRigths();
		rdo = aux.Despliegue("77771111111111ff", "TTTTNNNNNNNNNNNN", 1);

		cb.setFechaJornada("010115");
		cb.setNumTotalApuestas(StringUtils.leftPad(""+rdo.length, 6, '0'));
		cb.setNumTotalBloques(StringUtils.leftPad(""+rdo.length, 6, '0'));
		BetElectronicFile befile=new BetElectronicFile();
		befile.setCabecera(cb);

		RegistroBetElectronicFile[] registro=new RegistroBetElectronicFile[rdo.length];

		for (int i = 0; i < rdo.length; i++) {
			String linea = rdo[i];
			RegistroBetElectronicFile registroBe=new RegistroBetElectronicFile();
			registroBe.setNumApuestaBloque("1");
			registroBe.setNumBloque(StringUtils.leftPad(""+i, 6, '0') );
			registroBe.setPronostico15(StringUtils.right(linea, 2));
			registroBe.setPronosticoPartido(StringUtils.right(linea, 14));
			registro[i]=registroBe;
			System.out.println(linea);
		}
		
		befile.setRegistro(registro);
		
		
		System.out.println(befile.getCabeceraString());
		System.out.println(befile.getRegistroString());

	}

}
