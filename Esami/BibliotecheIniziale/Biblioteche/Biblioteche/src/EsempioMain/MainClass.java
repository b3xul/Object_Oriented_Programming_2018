package EsempioMain;

import Library.Biblioteca;
import Library.InvalidCode;
import Library.InvalidIsbn;
import Library.Libro;
import Library.Utente;

public class MainClass {

	public static void main(String[] args) {
		Biblioteca b1 = new Biblioteca("Centrale");
		Libro l1 = new Libro("Deitel,Deitel", "Java How to Program", "Pearson", "0-273-75976-0");
		Libro l2 = new Libro("Aho,Ullman", "Compilers", "Pearson", "0-321-48681-1");
		Libro l3 = new Libro("Tolkien", "Il Signore degli Anelli", "Rusconi", "88-18-12369-6");
		Libro l4 = new Libro("Neruda", "Poesie", "Einaudi", "88-06-02550-3");
		try{
			b1.addLibro(l1);
			b1.addLibro(l2);
			b1.addLibro(l3);
			b1.addLibro(l4);
		}
		catch(InvalidIsbn i){
			i.printStackTrace();
		}
		System.out.println("l1 = " + b1.getLibro("0-273-75976-0"));
		for(Libro lib : b1.libriPerAutore()) 
			System.out.println(lib);
		
		Utente u1 = new Utente(1, "Mario", "Rossi");
		Utente u2 = new Utente(2, "Giuseppe", "Verdi");
		Utente u3 = new Utente(3, "Pietro", "Bianchi");
		Utente u4 = new Utente(4, "Giovanni", "Rossi");
		Utente u5 = new Utente(5, "Antonio", "Verdi");
		try{
			b1.addUtente(u2);
			b1.addUtente(u1);
			b1.addUtente(u3);
			b1.addUtente(u5);
			b1.addUtente(u4);
		}
		catch(InvalidCode c){
			c.printStackTrace();
		}
		for(Utente u : b1.utenti()) 
			System.out.println(u);
		try{
			System.out.println("prestato: " + b1.prestito(1,"0-321-48681-1"));
			System.out.println("prestato: " + b1.prestito(1,"0-273-75976-0"));
			System.out.println("prestato: " + b1.prestito(1,"88-06-02550-3"));
			System.out.println("prestato: " + b1.prestito(3,"0-321-48681-1"));
			System.out.println("prestato: " + b1.prestito(2,"0-321-48681-1"));
			System.out.println("restituito: " + b1.restituzione(1,"0-321-48681-1"));
			System.out.println("prestato: " + b1.prestito(5,"88-06-02550-3"));
			System.out.println("prestato: " + b1.prestito(5,"88-18-12369-6"));
			System.out.println("prestato: " + b1.prestito(4,"0-321-48681-1"));
			System.out.println("prestato: " + b1.restituzione(1,"88-06-02550-3"));
			System.out.println("prestato: " + b1.prestito(5,"88-18-12369-6"));
			System.out.println("prestato: " + b1.prestito(2,"0-273-75976-0"));
		}
		catch(InvalidCode c){
			c.printStackTrace();
		}
		catch(InvalidIsbn i){
			i.printStackTrace();
		}
		System.out.println("Elenco prestiti utente " + u5 + " :");
		for(Libro lib : u1.prestiti()) 
			System.out.println("  " + lib);
		System.out.println("Elenco richeste libro " + l2 + " :");
		for(Utente u : b1.getRichieste(l2)) 
			System.out.println("  " + u);
		System.out.println("Elenco prestiti:");
		for(Libro lib : b1.elencoPrestiti()) 
			System.out.println(" " + lib);
		System.out.println("Elenco richieste:");
			for(Libro lib : b1.elencoRichieste()){
					System.out.println("  " + lib);	
		}
	}
}
