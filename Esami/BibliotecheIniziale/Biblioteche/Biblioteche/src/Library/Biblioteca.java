package Library;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

public class Biblioteca {
	private String nome;
	private Map<String,Libro> dotazione;
	private Map<Integer,Utente> utenti;
	private Map<Libro,Utente> prestiti;
	private Map<Libro,Queue<Utente>> richieste;

	public Biblioteca (String n) {
		nome = n;
		dotazione = new HashMap<>();
		utenti = new TreeMap<>();
		prestiti = new TreeMap<>();
		richieste = new TreeMap<>();
	}
	
	public void addLibro(Libro lib) throws InvalidIsbn {
		String i = lib.getIsbn();
		Libro l = dotazione.get(i);
		if(l == null)
			dotazione.put(i, lib);
		else
			throw new InvalidIsbn();
	}
	
	public Libro getLibro(String isbn){
	    return dotazione.get(isbn);
	}

	
	public List<Libro> libriPerAutore(){
		List<Libro> elenco = new ArrayList<Libro>(dotazione.values());
		Collections.sort(elenco);
		return elenco;
	}
	
	public void addUtente(Utente ut) throws InvalidCode{
		Integer c = ut.getCodice();
		Utente u = utenti.get(c);
		if(u == null)
			utenti.put(c, ut);
		else
			throw new InvalidCode();
	}
	
	public List<Utente> utenti(){
		return new ArrayList<Utente>(utenti.values());
	}
	
	public Libro prestito(int cu, String is) throws InvalidCode, InvalidIsbn {
		Utente u = utenti.get(cu);
		if(u == null)
			throw new InvalidCode();
		Libro l = dotazione.get(is);
		if(l == null)
			throw new InvalidIsbn();
		Utente up = prestiti.get(l);
		if(up == null) {
			prestiti.put(l, u);
			u.addLibro(l);
			return l;
		}
		else if(u != up) {
				Queue<Utente> q = richieste.get(l);
				if(q == null)
					q = new LinkedList<>();
					q.add(u);
					richieste.put(l, q);
					return null;
			}
			else
				return null;
	}
	
	public Libro restituzione(int cu, String is) throws InvalidCode, InvalidIsbn {
		Utente u = utenti.get(cu);
		if(u == null)
			throw new InvalidCode();
		Libro l = dotazione.get(is);
		if(l == null)
			throw new InvalidIsbn();
		Utente up = prestiti.get(l);
		if(up == u){
			prestiti.remove(l);
			u.retLibro(l);
			Queue<Utente> q = richieste.get(l);
			if(q != null && !q.isEmpty()){
				Utente ur = q.remove();
				prestiti.put(l, ur);
				ur.addLibro(l);
			}
			return l;
		}
		else
			return null;
	}
	
	public Queue<Utente> getRichieste(Libro l) {
		return richieste.get(l);
	}
	
	public List<Libro> elencoPrestiti() {
		return new ArrayList<>(prestiti.keySet());
	}
	
	public List<Libro> elencoRichieste() {
		ArrayList<Libro> keys = new ArrayList<>(richieste.keySet());
		for(int j=0 ; j<keys.size() ; j++)
			if(richieste.get(keys.get(j)).isEmpty()) 
				keys.remove(j);
		return keys;
	}
}
