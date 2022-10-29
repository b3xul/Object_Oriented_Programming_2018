package elezioni;

import java.util.Collection;


public class Elezione {

	public Elezione(){
		
	}
	
	public Cittadino aggiungiElettore(String nome, String cognome){
		return null;
	}
	
	public Collection getElettori(){
		return null;
	}
	
	public Cittadino getElettore(String nome, String cognome){
		return null;
	}
	
	public void registraLista(Lista lista){
	}

    /**
     * Il cittadino votante esprime un voto per la lista ed 
     * un voto di preferenza per il candidato identificato
     * da nome e cognome
     * @throws TentatoDoppioVoto se il cittadino ha già votato
     * @throws TaglioNonPermesso se il candidato per cui si esprime
     * 							la preferenza non appartiene alla lista
     */	
	public void vota(Cittadino votante, String lista, String nome, String cognome)
		throws TentatoDoppioVoto, TaglioNonPermesso{
	}

	/**
	 * Il cittadino votante esprime un voto per la lista
	 * il voto di preferenza va automaticamente al capolista
	 * @throws TentatoDoppioVoto se il cittadino ha già votato
	 */	
	public void vota(Cittadino votante, String lista)
		throws TentatoDoppioVoto{
	}
	
	public long getNumeroVotanti(){
		return -1;
	}
	
	public Collection getRisultatiListe(){
		return null;
	}

	public Collection getRisultatiCandidati(){
		return null;
	}
	
	
}
