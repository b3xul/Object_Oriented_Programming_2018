package it.polito.po.test;

import java.util.List;

import Library.Biblioteca;
import Library.InvalidCode;
import Library.InvalidIsbn;
import Library.Libro;
import Library.Utente;
import junit.framework.TestCase;

public class TestR5_Elenchi extends TestCase {
    Biblioteca b;
    
    public void setUp(){
    	b = new Biblioteca("Centrale");
    	assertNotNull(b);
    }
    
    public void testElencoRichieste() {
		Libro l1 = new Libro("Deitel,Deitel", "Java How to Program", "Pearson", "0-273-75976-0");
		Libro l2 = new Libro("Aho,Ullman", "Compilers", "Pearson", "0-321-48681-1");
		Libro l3 = new Libro("Tolkien", "Il Signore degli Anelli", "Rusconi", "88-18-12369-6");
		Libro l4 = new Libro("Neruda", "Poesie", "Einaudi", "88-06-02550-3");
		try{
			b.addLibro(l1);
			b.addLibro(l2);
			b.addLibro(l3);
			b.addLibro(l4);
		}
		catch(InvalidIsbn i){
			fail("Il codice ISBN dovrebbe essere valido");
		}
		Utente u1 = new Utente(1, "Mario", "Rossi");
		Utente u2 = new Utente(2, "Giuseppe", "Verdi");
		Utente u3 = new Utente(3, "Pietro", "Bianchi");
		Utente u4 = new Utente(4, "Giovanni", "Rossi");
		Utente u5 = new Utente(5, "Antonio", "Verdi");
		try{
			b.addUtente(u2);
			b.addUtente(u1);
			b.addUtente(u3);
			b.addUtente(u5);
			b.addUtente(u4);
		}
		catch(InvalidCode c){
			fail("Il codice utente dovrebbe essere valido");
		}
		try{
			b.prestito(1,"0-321-48681-1");
		    b.prestito(1,"0-273-75976-0");
			b.prestito(1,"88-06-02550-3");
			b.prestito(3,"0-321-48681-1");
			b.prestito(2,"0-321-48681-1");
			b.restituzione(1,"0-321-48681-1");
			b.prestito(5,"88-06-02550-3");
			b.prestito(5,"88-18-12369-6");
			b.prestito(4,"0-321-48681-1");
			b.restituzione(1,"88-06-02550-3");
			b.prestito(5,"88-18-12369-6");
		}
		catch(InvalidCode c){
			fail("Il codice utente dovrebbe essere valido");
		}
		catch(InvalidIsbn i){
			fail("Il codice ISBN dovrebbe essere valido");
		}
		List<Libro> p = b.elencoPrestiti();
		assertNotNull(p);
		assertEquals(p.size(),4);
		assertEquals(p.get(0),l2);
		assertEquals(p.get(1),l1);
		assertEquals(p.get(2),l4);
		assertEquals(p.get(3),l3);
    }
    
    public void testElencoPrestiti() {
		Libro l1 = new Libro("Deitel,Deitel", "Java How to Program", "Pearson", "0-273-75976-0");
		Libro l2 = new Libro("Aho,Ullman", "Compilers", "Pearson", "0-321-48681-1");
		Libro l3 = new Libro("Tolkien", "Il Signore degli Anelli", "Rusconi", "88-18-12369-6");
		Libro l4 = new Libro("Neruda", "Poesie", "Einaudi", "88-06-02550-3");
		try{
			b.addLibro(l1);
			b.addLibro(l2);
			b.addLibro(l3);
			b.addLibro(l4);
		}
		catch(InvalidIsbn i){
			fail("Il codice ISBN dovrebbe essere valido");
		}
		Utente u1 = new Utente(1, "Mario", "Rossi");
		Utente u2 = new Utente(2, "Giuseppe", "Verdi");
		Utente u3 = new Utente(3, "Pietro", "Bianchi");
		Utente u4 = new Utente(4, "Giovanni", "Rossi");
		Utente u5 = new Utente(5, "Antonio", "Verdi");
		try{
			b.addUtente(u2);
			b.addUtente(u1);
			b.addUtente(u3);
			b.addUtente(u5);
			b.addUtente(u4);
		}
		catch(InvalidCode c){
			fail("Il codice utente dovrebbe essere valido");
		}
		try{
			b.prestito(1,"0-321-48681-1");
		    b.prestito(1,"0-273-75976-0");
			b.prestito(1,"88-06-02550-3");
			b.prestito(3,"0-321-48681-1");
			b.prestito(2,"0-321-48681-1");
			b.restituzione(1,"0-321-48681-1");
			b.prestito(5,"88-06-02550-3");
			b.prestito(5,"88-18-12369-6");
			b.prestito(4,"0-321-48681-1");
			b.restituzione(1,"88-06-02550-3");
			b.prestito(5,"88-18-12369-6");
			b.prestito(2,"0-273-75976-0");
		}
		catch(InvalidCode c){
			fail("Il codice utente dovrebbe essere valido");
		}
		catch(InvalidIsbn i){
			fail("Il codice ISBN dovrebbe essere valido");
		}
		List<Libro> p = b.elencoRichieste();
		assertNotNull(p);
		assertEquals(p.size(),2);
		assertEquals(p.get(0),l2);
		assertEquals(p.get(1),l1);
    }
}
