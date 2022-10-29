package Library;

import java.util.List;
import java.util.Queue;

public class Biblioteca {


	public Biblioteca (String n) {
	}
	
	public void addLibro(Libro lib) throws InvalidIsbn {
	}
	
	public Libro getLibro(String isbn){
	    return null;
	}

	
	public List<Libro> libriPerAutore(){
		return null;
	}
	
	public void addUtente(Utente ut) throws InvalidCode{
	}
	
	public List<Utente> utenti(){
		return null;
	}
	
	public Libro prestito(int cu, String isbn) throws InvalidCode, InvalidIsbn {
		return null;
	}
	
	public Libro restituzione(int cu, String isbn) throws InvalidCode, InvalidIsbn {
			return null;
	}
	
	public Queue<Utente> getRichieste(Libro l) {
		return null;
	}
	
	public List<Libro> elencoPrestiti() {
		return null;
	}
	
	public List<Libro> elencoRichieste() {
		return null;
	}
}
