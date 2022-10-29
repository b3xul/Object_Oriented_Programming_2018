package it.polito.po.test;

import java.util.List;

import Library.Biblioteca;
import Library.InvalidCode;
import Library.Utente;
import junit.framework.TestCase;

public class TestR2_Utenti extends TestCase {
    Biblioteca b;
    
    public void setUp(){
    	b = new Biblioteca("Centrale");
    	assertNotNull(b);
    }
    
	public void testNuovoUtente(){
		Utente u = new Utente(1, "Mario", "Rossi");
		assertNotNull(u);
	}
	
	public void testAddUtente(){
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
	}
	
	public void testAddUtentePresente(){
		Utente u1 = new Utente(1, "Mario", "Rossi");
		try{
			b.addUtente(u1);
		}
		catch(InvalidCode c){
			fail("Il codice utente dovrebbe essere valido");
		}
		try{
			b.addUtente(u1);
			fail("Il codice utente è già presente");
		}
		catch(InvalidCode c){
			assertTrue(true);
		}
	}
	
	public void testElencoUtenti(){
		Utente u1 = new Utente(1, "Mario", "Rossi");
		Utente u2 = new Utente(2, "Giuseppe", "Verdi");
		Utente u3 = new Utente(3, "Pietro", "Bianchi");
		try{
			b.addUtente(u2);
			b.addUtente(u1);
			b.addUtente(u3);
		}
		catch(InvalidCode c){
			fail("Il codice utente dovrebbe essere valido");
		}
		List<Utente> el = b.utenti();
		assertNotNull(el);
		assertEquals(el.size(),3);
	}
	
	public void testElencoUtentiOrdinati(){
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
		List<Utente> el = b.utenti();
		assertNotNull(el);
		assertEquals(el.size(),5);
		assertEquals(el.get(0),u1);
		assertEquals(el.get(1),u2);
		assertEquals(el.get(2),u3);
		assertEquals(el.get(3),u4);
		assertEquals(el.get(4),u5);
	}
}
