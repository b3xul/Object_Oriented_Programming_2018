package it.polito.po.test;


import java.util.List;

import Library.Biblioteca;
import Library.InvalidIsbn;
import Library.Libro;
import junit.framework.TestCase;

public class TestR1_Libri extends TestCase {
    Biblioteca b;
    
    public void setUp(){
    	b = new Biblioteca("Centrale");
    	assertNotNull(b);
    }
	
	public void testNuovoLibro(){
		Libro l = new Libro("Deitel,Deitel", "Java How to Program", "Pearson", "0-273-75976-0");
		assertNotNull(l);
	}
	
	public void testgetLibro(){
		Libro l = new Libro("Deitel,Deitel", "Java How to Program", "Pearson", "0-273-75976-0");
		assertNotNull(l);
		try{
			b.addLibro(l);
		}
		catch(InvalidIsbn i){
			fail("Il codice ISBN dovrebbe essere valido");
		}
		assertEquals(b.getLibro("0-273-75976-0"),l);
	}
	
	public void testAddLibro(){
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
	}
	
	public void testAddLibroPresente(){
		Libro l1 = new Libro("Deitel,Deitel", "Java How to Program", "Pearson", "0-273-75976-0");
		try{
			b.addLibro(l1);
		}
		catch(InvalidIsbn i){
			fail("Il codice ISBN dovrebbe essere valido");
		}
		try{
			b.addLibro(l1);
			fail("Il codice ISBN è già presente");
		}
		catch(InvalidIsbn i){
			assertTrue(true);
		}
	}
	
	public void testElencoLibri(){
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
		List<Libro> el = b.libriPerAutore();
		assertNotNull(el);
		assertEquals(el.size(),3);
	}

	public void testElencoLibriOrdinati(){
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
		List<Libro> el = b.libriPerAutore();
		assertNotNull(el);
		assertEquals(el.size(),4);
		assertEquals(el.get(0),l2);
		assertEquals(el.get(1),l1);
		assertEquals(el.get(2),l4);
		assertEquals(el.get(3),l3);
	}
}
