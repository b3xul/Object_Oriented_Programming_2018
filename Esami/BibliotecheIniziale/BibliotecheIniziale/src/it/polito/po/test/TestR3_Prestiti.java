package it.polito.po.test;

import java.util.List;
import java.util.Queue;

import Library.Biblioteca;
import Library.InvalidCode;
import Library.InvalidIsbn;
import Library.Libro;
import Library.Utente;
import junit.framework.TestCase;

public class TestR3_Prestiti extends TestCase {
    Biblioteca b;
    
    public void setUp(){
    	b = new Biblioteca("Centrale");
    	assertNotNull(b);
    }
    
    public void testPrestitoConcesso() {
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
		}
		catch(InvalidCode c){
			fail("Il codice utente dovrebbe essere valido");
		}
		catch(InvalidIsbn i){
			fail("Il codice ISBN dovrebbe essere valido");
		}
    }
    
    public void testPrestitoNegato() {
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
 			Libro l1 = b.prestito(1,"0-321-48681-1");
 			assertNull(l1);
 			l1 = b.prestito(2,"0-321-48681-1");
 			assertNull(l1);
 		}
 		catch(InvalidCode c){
 			fail("Il codice utente dovrebbe essere valido");
 		}
 		catch(InvalidIsbn i){
 			fail("Il codice ISBN dovrebbe essere valido");
 		}
     }
    
    public void testPrestitiUtente() {
    	Libro l1 = new Libro("Deitel,Deitel", "Java How to Program", "Pearson", "0-273-75976-0");
		Libro l2 = new Libro("Aho,Ullman", "Compilers", "Pearson", "0-321-48681-1");
		Libro l3 = new Libro("Tolkien", "Il Signore degli Anelli", "Rusconi", "88-18-12369-6");
		try{
			b.addLibro(l1);
			b.addLibro(l2);
			b.addLibro(l3);
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
			l = b.prestito(1, "88-18-12369-6");
			assertNotNull(l);
			assertEquals(l,l3);
			l = b.prestito(1,"0-273-75976-0");
			assertNotNull(l);
			assertEquals(l,l1);
			List<Libro> p = u1.prestiti();
			assertNotNull(p);
			assertEquals(p.size(),3);
			assertEquals(p.get(0),l2);
			assertEquals(p.get(1),l1);
			assertEquals(p.get(2),l3);
		}
		catch(InvalidCode c){
			fail("Il codice utente dovrebbe essere valido");
		}
		catch(InvalidIsbn i){
			fail("Il codice ISBN dovrebbe essere valido");
		}
    }
    
    public void testRichiesteLibroStessoUtente() {
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
 			Libro l1 = b.prestito(1,"0-321-48681-1");
 			assertNull(l1);
 			Queue<Utente> q = b.getRichieste(l);
 			assertTrue(q==null || q.isEmpty());
 		}
 		catch(InvalidCode c){
 			fail("Il codice utente dovrebbe essere valido");
 		}
 		catch(InvalidIsbn i){
 			fail("Il codice ISBN dovrebbe essere valido");
 		}
    }
    
    public void testRichiesteLibroUtentiDiversi() {
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
  			assertEquals(q.remove(),u2);
  			assertEquals(q.element(),u3);
  		}
  		catch(InvalidCode c){
  			fail("Il codice utente dovrebbe essere valido");
  		}
  		catch(InvalidIsbn i){
  			fail("Il codice ISBN dovrebbe essere valido");
  		}
     }
}
