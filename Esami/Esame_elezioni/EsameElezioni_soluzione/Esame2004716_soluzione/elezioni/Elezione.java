package elezioni;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;


public class Elezione {
	private long votanti;
    HashMap elettori = new HashMap();
	HashMap liste = new HashMap();

	public Elezione(){
		
	}
	
	public Cittadino aggiungiElettore(String nome, String cognome){
		Cittadino c = new Elettore(nome,cognome);
		elettori.put(nome+cognome,c);
		return c;
	}
	
	public Collection getElettori(){
		return elettori.values();
	}
	
	public Cittadino getElettore(String nome, String cognome){
		return (Cittadino)elettori.get(nome+cognome);
	}
	
	public void registraLista(Lista lista){
		liste.put(lista.getNome(),lista);
		lista.setElezione(this);
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
		if(votante.haVotato()) throw new TentatoDoppioVoto();
		
		Lista l = (Lista)liste.get(lista);
		
		Elettore c = l.getElettore(nome,cognome);
		
		if(c==null) throw new TaglioNonPermesso();
		
		l.addVoto();
		c.addVoto();
		((Elettore)votante).setVotato();
		votanti++;
	}

	/**
	 * Il cittadino votante esprime un voto per la lista
	 * il voto di preferenza va automaticamente al capolista
	 * @throws TentatoDoppioVoto se il cittadino ha già votato
	 */	
	public void vota(Cittadino votante, String lista)
		throws TentatoDoppioVoto{
		if(votante.haVotato()) throw new TentatoDoppioVoto();
		Lista l = (Lista)liste.get(lista);
		
		l.addVoto();
		((Elettore)l.getCapolista()).addVoto();
		((Elettore)votante).setVotato();
		votanti++;
	}
	
	public long getNumeroVotanti(){
		return votanti;
	}
	
	public Collection getRisultatiListe(){
		LinkedList ll = new LinkedList(liste.values());
		
		Collections.sort(ll);
		
		return ll;
	}

	public Collection getRisultatiCandidati(){
		LinkedList candidati = new LinkedList();
		for (Iterator iter = liste.values().iterator(); iter.hasNext();) {
      		Lista lista = (Lista) iter.next();
      		candidati.add(lista.getCapolista());
      		candidati.addAll(lista.getCandidati());
    	}
    	Collections.sort(candidati);
		return candidati;
	}
	
	
}
