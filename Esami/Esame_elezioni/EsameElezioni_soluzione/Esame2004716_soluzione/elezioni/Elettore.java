package elezioni;


class Elettore implements Cittadino, Comparable {
  private int numVoti;
  private String nome;
  private String cognome;
  private boolean votato;
  private boolean candidato;
  private boolean capolista;
  
  Elettore(String nome, String cognome){
  	this.nome = nome;
  	this.cognome = cognome;	
  	votato = false;
  	candidato = false;
	capolista = false;
  }
  
  public String getNome() {
    return nome;
  }

  public String getCognome() {
    return cognome;
  }

  public boolean haVotato() {
    return votato;
  }

  void setCandidato() {
  	candidato = true;
  }

	void setCapolista() {
		capolista = true;
	}


  public boolean isCapolista() {
    return capolista;
  }

  public long getNumeroVoti() {
    return numVoti;
  }

  public boolean isCandidato() {
  	return candidato;
  }

  public void addVoto() {
  	numVoti++;
  }

  public void setVotato() {
  	votato = true;
  }

  public int compareTo(Object arg0) {
  	Elettore other = (Elettore)arg0;
    return other.numVoti - this.numVoti;
  }
  
  public String toString(){
  	return nome + " " + cognome;
  }

}
