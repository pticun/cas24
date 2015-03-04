package org.arch.core.file;

import org.apache.commons.lang3.StringUtils;

public class BetElectronicFile {
	
	private HeaderBetElectronicFile cabecera;
	private RegistroBetElectronicFile registro[];

	private String nameFile="AD243";

	public String getNameFile() {
		return nameFile;
	}

	public void setNameFile(String nameFile) {
		this.nameFile = nameFile;
	}

	
	public HeaderBetElectronicFile getCabecera() {
		return cabecera;
	}

	public void setCabecera(HeaderBetElectronicFile cabecera) {
		this.cabecera = cabecera;
	}

	public RegistroBetElectronicFile[] getRegistro() {
		return registro;
	}

	public void setRegistro(RegistroBetElectronicFile[] registro) {
		this.registro = registro;
	}
	
	
	public String getCabeceraString(){
		StringBuffer sb=new StringBuffer();
		
		sb.append(getCabecera().getClaveRegistro());
		sb.append(StringUtils.leftPad(getCabecera().getNumSecuencialVolumen(), 2, '0') );
		sb.append(getCabecera().getTipoJuego());
		
		return sb.toString();
	}
	
	
}
