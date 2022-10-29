package test;
import java.util.Collection;

import abbigliamento.Capo;
import abbigliamento.Collezione;
import abbigliamento.Colore;
import abbigliamento.Materiale;
import abbigliamento.Modello;
import junit.framework.TestCase;

public class TestR4_Collezioni extends TestCase {

	Collezione estate;
	Modello top;
	Modello tshirt;
	Materiale cotone;
	Materiale lino;
	Colore rosa;
	Colore bianco;
	Capo c1;
	Capo c2;
	Capo c3;
	
	public void setUp() throws Exception {
		top = new Modello("top", 5.0 , 0.2);
		tshirt = new Modello("t-shirt", 6.0, 1.0);

	    // materiale cotone, costo 0.50 euro / mq 
		cotone = new Materiale("cotone",0.50);
		lino = new Materiale("lino",1.0);

		rosa = new Colore("rosa");
		bianco = new Colore("bianco");

		c1 = new Capo(top,cotone,rosa);
		c2 = new Capo(tshirt,lino,bianco);
		c3 = new Capo(tshirt,cotone,bianco);

		estate = new Collezione();
		estate.add(c1);
		estate.add(c2);
		estate.add(c3);
	}
	
	
	public void testCercaColore() throws Exception {
		Collection bianchi = estate.trova(bianco);
		
		assertNotNull(bianchi);
		assertEquals(2,bianchi.size());
		
		assertTrue(bianchi.contains(c2));
		assertTrue(bianchi.contains(c3));
	}
	
	public void testCercaMateriale() throws Exception {
		Collection lini = estate.trova(lino);
		
		assertNotNull(lini);
		assertEquals(1,lini.size());
		
		assertTrue(lini.contains(c2));
	}

	public void testCercaModello() throws Exception {
		Collection maglie = estate.trova(tshirt);
		
		assertNotNull(maglie);
		assertEquals(2,maglie.size());
		
		assertTrue(maglie.contains(c2));
		assertTrue(maglie.contains(c3));
	}

}
