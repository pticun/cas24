package org.arch.core.file;

public class CabeceraBetElectronicFile {
	private String claveRegistro = "C";
	private String numSecuencialVolumen = "1";
	private String tipoJuego = "243";
	private String numTotalSoporte = "1";

	private String idDelegacion = "";
	private String idReceptor = "";

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
