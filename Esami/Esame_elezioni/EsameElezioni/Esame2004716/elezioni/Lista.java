package elezioni;

import java.util.Collection;


public class Lista {

	public Lista(String nome, String motto){
	}
	
	public String getNome(){
		return null;
	}

	public String getMotto(){
		return null;
	}
	
	public void assegnaCapolista(Cittadino capolista)
			throws CandidatoNonValido {
	}

	public void aggiungiCandidato(Cittadino capolista)
			throws CandidatoNonValido {
	}

	public Cittadino getCapolista(){
		return null;
	}

	/**
	 * Restuisce la collezione dei candidati
	 * (NON include il capolista)
	 */
	public Collection getCandidati(){
		return null;
	}
	
	
	public long getNumeroVoti(){
		return -1;
	}

	public double getPercentualeVoti(){
		return -1.1;
	}
}
