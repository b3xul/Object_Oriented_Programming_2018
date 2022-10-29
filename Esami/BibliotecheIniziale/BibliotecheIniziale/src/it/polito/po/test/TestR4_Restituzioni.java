package it.polito.po.test;

import java.util.List;
import java.util.Queue;

import Library.Biblioteca;
import Library.InvalidCode;
import Library.InvalidIsbn;
import Library.Libro;
import Library.Utente;
import junit.framework.TestCase;

public class TestR4_Restituzioni extends TestCase {
    Biblioteca b;
    
    public void setUp(){
    	b = new Biblioteca("Centrale");
    	assertNotNull(b);
    }
    
    public void testRestituzioneEffettuata() {
		Libro l2 = new Libro("Aho,Ullman", "Compilers", "Pearson", "0-321-48681-1");
		try{
			b.addLibro(l2);
		}
		catch(InvalidIsbn i){
			fail("Il codice ISBN dovrebbe essere valido");
		}
		Utente u1 = new Utente(1, "Mario", "Rossi");
		try{
			b.addUtente(u1);
		}
		catch(InvalidCode c){
			fail("Il codice utente dovrebbe essere valido");
		}
		try{
			Libro l = b.prestito(1,"0-321-48681-1");
			assertNotNull(l);
			assertEquals(l,l2);
 			List<Libro> p = u1.prestiti();
 			assertEquals(p.get(0),l2);
			Libro l1 = b.restituzione(1,"0-321-48681-1");
			assertNotNull(l1);
			assertEquals(l1,l2);
 			p = u1.prestiti();
 			assertTrue(p.isEmpty());
		}
		catch(InvalidCode c){
			fail("Il codice utente dovrebbe essere valido");
		}
		catch(InvalidIsbn i){
			fail("Il codice ISBN dovrebbe essere valido");
		}
    }
    
    public void testRestituzioneNegata() {
 		Libro l2 = new Libro("Aho,Ullman", "Compilers", "Pearson", "0-321-48681-1");
 		try{
 			b.addLibro(l2);
 		}
 		catch(InvalidIsbn i){
 			fail("Il codice ISBN dovrebbe essere valido");
 		}
 		Utente u1 = new Utente(1, "Mario", "Rossi");
 		Utente u2 = new Utente(2, "Giuseppe", "Verdi");
 		try{
 			b.addUtente(u1);
 			b.addUtente(u2);
 		}
 		catch(InvalidCode c){
 			fail("Il codice utente dovrebbe essere valido");
 		}
 		try{
 			Libro l = b.prestito(1,"0-321-48681-1");
 			assertNotNull(l);
 			assertEquals(l,l2);
 			Libro l1 = b.restituzione(2,"0-321-48681-1");
 			assertNull(l1);
 			List<Libro> p = u1.prestiti();
 			assertEquals(p.get(0),l2);
 		}
 		catch(InvalidCode c){
 			fail("Il codice utente dovrebbe essere valido");
 		}
 		catch(InvalidIsbn i){
 			fail("Il codice ISBN dovrebbe essere valido");
 		}
     }
  
    public void testRestituzioneLibroRichiesto() {
  		Libro l2 = new Libro("Aho,Ullman", "Compilers", "Pearson", "0-321-48681-1");
  		try{
  			b.addLibro(l2);
  		}
  		catch(InvalidIsbn i){
  			fail("Il codice ISBN dovrebbe essere valido");
  		}
  		Utente u1 = new Utente(1, "Mario", "Rossi");
  		Utente u2 = new Utente(2, "Giuseppe", "Verdi");
		Utente u3 = new Utente(3, "Pietro", "Bianchi");
  		try{
  			b.addUtente(u1);
  			b.addUtente(u2);
  			b.addUtente(u3);
  		}
  		catch(InvalidCode c){
  			fail("Il codice utente dovrebbe essere valido");
  		}
  		try{
  			Libro l = b.prestito(1,"0-321-48681-1");
  			assertNotNull(l);
  			assertEquals(l,l2);
  			Libro l1 = b.prestito(2,"0-321-48681-1");
  			assertNull(l1);
  			Queue<Utente> q = b.getRichieste(l);
  			assertNotNull(q);
  			assertEquals(q.size(),1);
  			assertEquals(q.element(),u2);
  			l1 = b.prestito(3,"0-321-48681-1");
  			assertNull(l1);
  			q = b.getRichieste(l);
  			assertEquals(q.size(),2);
  			l1 = b.restituzione(1,"0-321-48681-1");
  			assertNotNull(l);
  			assertEquals(l,l2);
  			q = b.getRichieste(l);
  			assertEquals(q.size(),1);
  			assertEquals(q.element(),u3);
  			List<Libro> p = u1.prestiti();
 			assertTrue(p.isEmpty());
  			p = u2.prestiti();
 			assertEquals(p.get(0),l2);
  		}
  		catch(InvalidCode c){
  			fail("Il codice utente dovrebbe essere valido");
  		}
  		catch(InvalidIsbn i){
  			fail("Il codice ISBN dovrebbe essere valido");
  		}
     }
}
