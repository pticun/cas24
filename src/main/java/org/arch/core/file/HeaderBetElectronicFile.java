package org.arch.core.file;

/**
 * 
 * Example Header
 * C12430101011401
 * @author racsor
 *
 */
public class HeaderBetElectronicFile {
	private String claveRegistro = "C";
	private String numSecuencialVolumen = "1";
	private String tipoJuego = "243";
	private String numTotalSoporte = "01";
	private String numTotalApuestas = "123456";
	private String numTotalBloques = "123456";

	private String idDelegacion = "00";
	private String idReceptor = "00000";
	
	private String numJornada="01";
	private String fechaJornada="010101";

	public String calculoCaracterControl() {
		int caractControl = 0;
		String stringControl="00";

		char[] chardel = getIdDelegacion().toCharArray();
		char[] charrec = getIdReceptor().toCharArray();
		char[] total = new char[7];

		System.arraycopy(chardel, 0, total, 0, 2);
		System.arraycopy(charrec, 0, total, 2, 5);

		for (int i = 0; i < total.length; i++) {
			System.out.println((total[i]- '0') * (i + 1));
			caractControl += (total[i]- '0') * (i + 1);
		}
		
		caractControl=caractControl%12;
		System.out.println(caractControl%12);
		if(caractControl<10){
			stringControl="0"+caractControl;
		}
		else{
			stringControl=new String(""+caractControl);
		}

		return stringControl;
	}

	public String getNumTotalApuestas() {
		return numTotalApuestas;
	}

	public void setNumTotalApuestas(String numTotalApuestas) {
		this.numTotalApuestas = numTotalApuestas;
	}

	public String getNumTotalBloques() {
		return numTotalBloques;
	}

	public void setNumTotalBloques(String numTotalBloques) {
		this.numTotalBloques = numTotalBloques;
	}

	public String getNumJornada() {
		return numJornada;
	}

	public void setNumJornada(String numJornada) {
		this.numJornada = numJornada;
	}

	public String getFechaJornada() {
		return fechaJornada;
	}

	public void setFechaJornada(String fechaJornada) {
		this.fechaJornada = fechaJornada;
	}

	public String getClaveRegistro() {
		return claveRegistro;
	}

	public void setClaveRegistro(String claveRegistro) {
		this.claveRegistro = claveRegistro;
	}

	public String getNumSecuencialVolumen() {
		return numSecuencialVolumen;
	}

	public void setNumSecuencialVolumen(String numSecuencialVolumen) {
		this.numSecuencialVolumen = numSecuencialVolumen;
	}

	public String getTipoJuego() {
		return tipoJuego;
	}

	public void setTipoJuego(String tipoJuego) {
		this.tipoJuego = tipoJuego;
	}

	public String getNumTotalSoporte() {
		return numTotalSoporte;
	}

	public void setNumTotalSoporte(String numTotalSoporte) {
		this.numTotalSoporte = numTotalSoporte;
	}

	public String getIdDelegacion() {
		return idDelegacion;
	}

	public void setIdDelegacion(String idDelegacion) {
		this.idDelegacion = idDelegacion;
	}

	public String getIdReceptor() {
		return idReceptor;
	}

	public void setIdReceptor(String idReceptor) {
		this.idReceptor = idReceptor;
	}

}
