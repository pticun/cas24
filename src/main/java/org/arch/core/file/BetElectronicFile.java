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
		sb.append(StringUtils.leftPad(getCabecera().getTipoJuego(), 3, '0'));
		sb.append(StringUtils.leftPad(getCabecera().getNumJornada(), 2, '0'));
		sb.append(StringUtils.leftPad(getCabecera().getFechaJornada(), 6, '0'));
		sb.append(StringUtils.leftPad(getCabecera().getIdDelegacion(), 2, '0'));
		sb.append(StringUtils.leftPad(getCabecera().getIdReceptor(), 5, '0'));
		sb.append(StringUtils.leftPad(getCabecera().getNumTotalApuestas(), 6, '0'));
		sb.append(StringUtils.leftPad(getCabecera().getNumTotalSoporte(), 2, '0'));
		sb.append(StringUtils.leftPad(getCabecera().getNumTotalBloquess(), 6, '0'));
		sb.append(StringUtils.leftPad(getCabecera().calculoCaracterControl(), 2, '0'));
		sb.append(StringUtils.leftPad("", 87, ' '));
		
		return sb.toString();
	}
	
	public String getRegistroString(){
		StringBuffer sb=new StringBuffer();
		
		for (RegistroBetElectronicFile registroBetElectronicFile : registro) {
			sb.append(registroBetElectronicFile.getClaveRegistro());
			sb.append(StringUtils.leftPad(registroBetElectronicFile.getNumBloque(), 8, '0') );
			sb.append(registroBetElectronicFile.getNumApuestaBloque());
			sb.append(registroBetElectronicFile.getPronosticoPartido());
			sb.append(registroBetElectronicFile.getPronostico15());
		}
			
		return sb.toString();
	}
	
	
}
