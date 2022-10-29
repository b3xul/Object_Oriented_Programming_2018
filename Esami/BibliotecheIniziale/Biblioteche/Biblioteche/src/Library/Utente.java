package Library;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Utente {
	int codice;
	String nome;
	String cognome;
	List<Libro> libri;

	public Utente (int cod, String n , String c ){
		codice = cod;
		nome = n;
		cognome = c;
		libri = new LinkedList<>();
	}
	
	public Integer getCodice() {
		return codice;
	}
	
	public String toString(){
		return codice + "- " + nome + " "+ cognome;
	}
	
	public void addLibro(Libro l) {
		libri.add(l);
	}
	
	public void retLibro(Libro l) {
		libri.remove(l);
	}
	
	public List<Libro> prestiti() {
		Collections.sort(libri);
		return libri;
	}
}
