package org.arch.core.file;

public class CabeceraBetElectronicTest {

	public static void main(String[] args) {
		CabeceraBetElectronicFile cb = new CabeceraBetElectronicFile();
		cb.setIdDelegacion("64");
		cb.setIdReceptor("65428");
		
		System.out.println("calculoCaracterControl(64,65428)="+ cb.calculoCaracterControl());

	}
}
