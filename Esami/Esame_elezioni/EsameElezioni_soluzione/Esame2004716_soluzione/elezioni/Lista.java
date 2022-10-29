package elezioni;

import java.util.Collection;
import java.util.HashMap;

public class Lista implements Comparable {

	private int numVoti;
    private String nome;
	private String motto;
	private Elezione elezione;
	private Elettore capolista;
	private HashMap candidati = new HashMap();
	
	public Lista(String nome, String motto){
		this.nome = nome;
		this.motto = motto;
	}
	
	public String getNome(){
		return nome;
	}

	public String getMotto(){
		return motto;
	}
	
	public void assegnaCapolista(Cittadino capolista)
			throws CandidatoNonValido {
		Elettore e = (Elettore)capolista;
		if(e.isCandidato()) throw new CandidatoNonValido();
		e.setCandidato();
		e.setCapolista();
		this.capolista = e;
	}

	public void aggiungiCandidato(Cittadino capolista)
			throws CandidatoNonValido {
		Elettore e = (Elettore)capolista;
		if(e.isCandidato()) throw new CandidatoNonValido();
		e.setCandidato();
		candidati.put(e.getNome()+e.getCognome(),e);
	}
	
	public Cittadino getCapolista(){
		return capolista;
	}

	/**
	 * Restuisce la collezione dei candidati
	 * (NON include il capolista)
	 */
	public Collection getCandidati(){
		return candidati.values();
	}
	
	
	public long getNumeroVoti(){
		return numVoti;
	}

	public double getPercentualeVoti(){
		return ((double)numVoti)/((double)elezione.getNumeroVotanti());
	}

  Elettore getElettore(String nome, String cognome) {
    Elettore c = (Elettore)candidati.get(nome+cognome);
    
    if(c!=null){
    	return c;
    }else{
    	if(capolista.getNome().equals(nome) && 
    		capolista.getCognome().equals(cognome)){
    	  return capolista;    			
    	}
    }
    return null;
  }

  void addVoto() {
  	numVoti++;
  }

  public int compareTo(Object obj) {
  	Lista other = (Lista)obj;
    return other.numVoti - this.numVoti;
  }
  void setElezione(Elezione elezione) {
    this.elezione = elezione;
  }
  
  public String toString(){
  	return nome;
  }

}
