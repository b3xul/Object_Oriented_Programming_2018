package test;

import java.util.Queue;

import gestioneTaxi.InvalidTaxiName;
import gestioneTaxi.Luogo;
import gestioneTaxi.Passeggero;
import gestioneTaxi.Taxi;
import gestioneTaxi.TaxiCompany;

import org.junit.Test;

import junit.framework.TestCase;

public class TestR4_GestioneTaxi extends TestCase {

	@Test
	public void test_getLiberi() throws InvalidTaxiName {
		TaxiCompany company = new TaxiCompany();
		Queue<Taxi> queue = company.getLiberi();
		assertNotNull(queue);
		assertEquals(queue.size(),0);
		company.addTaxi("Taxi5");
		queue = company.getLiberi();
		assertEquals(queue.size(),1);
	}
	
	public void test_addTaxi1()  {
		TaxiCompany company = new TaxiCompany();
		Queue<Taxi> queue = company.getLiberi();
		assertNotNull(queue);
		try{
			company.addTaxi("Taxi5");
			company.addTaxi("Taxi5");
			fail("Nome taxi non valido");
		}
		catch(InvalidTaxiName itn){
		}
		queue = company.getLiberi();
		assertEquals(queue.size(),1);
	}
	
	public void test_addTaxi2()  {
		TaxiCompany company = new TaxiCompany();
		Queue<Taxi> queue = company.getLiberi();
		assertNotNull(queue);
		try{
			company.addTaxi("Taxi5");
			company.addTaxi("Taxi6");
		}
		catch(InvalidTaxiName itn){
			fail("Il nome taxi è valido");
		}
		queue = company.getLiberi();
		assertEquals(queue.size(),2);
	}
	
	public void test_chiamataTaxi0() throws InvalidTaxiName {
		TaxiCompany company = new TaxiCompany();
		company.addTaxi("Taxi5");
		Luogo luogo1 = new Luogo("Via Roma 10", "centro");
		Passeggero passeggero1 = new Passeggero(luogo1);
		Taxi t = company.chiamataTaxi(passeggero1);
		assertNotNull(t);
		assertEquals(t.toString(),"Taxi5");
		Queue<Taxi> queue = company.getLiberi();
		assertEquals(queue.size(),0);
	}
	
	public void test_chiamataTaxi1() throws InvalidTaxiName {
		TaxiCompany company = new TaxiCompany();
		company.addTaxi("Taxi5");
		Luogo luogo1 = new Luogo("Via Roma 10", "centro");
		Passeggero passeggero1 = new Passeggero(luogo1);
		Taxi t = company.chiamataTaxi(passeggero1);
		t = company.chiamataTaxi(passeggero1);
		assertNull(t);
		Queue<Taxi> queue = company.getLiberi();
		assertEquals(queue.size(),0);
	}
	
	public void test_chiamataTaxi2() throws InvalidTaxiName {
		TaxiCompany company = new TaxiCompany();
		company.addTaxi("Taxi5");
		company.addTaxi("Taxi6");
		Luogo luogo1 = new Luogo("Via Roma 10", "centro");
		Passeggero passeggero1 = new Passeggero(luogo1);
		Taxi t = company.chiamataTaxi(passeggero1);
		assertNotNull(t);
		assertEquals(t.toString(),"Taxi5");
		t = company.chiamataTaxi(passeggero1);
		assertNotNull(t);
		assertEquals(t.toString(),"Taxi6");
		Queue<Taxi> queue = company.getLiberi();
		assertEquals(queue.size(),0);
	}
	
	public void test_chiamataTaxi3() throws InvalidTaxiName {
		TaxiCompany company = new TaxiCompany();
		company.addTaxi("Taxi5");
		company.addTaxi("Taxi6");
		Luogo luogo1 = new Luogo("Via Roma 10", "centro");
		Passeggero passeggero1 = new Passeggero(luogo1);
		Passeggero passeggero2 = new Passeggero(luogo1);
		Taxi t = company.chiamataTaxi(passeggero1);
		assertNotNull(t);
		t = company.chiamataTaxi(passeggero2);
		assertNotNull(t);
		assertEquals(company.getCorsePerse(),0);

	}
	
	public void test_chiamataTaxi4() throws InvalidTaxiName {
		TaxiCompany company = new TaxiCompany();
		company.addTaxi("Taxi5");
		company.addTaxi("Taxi6");
		Luogo luogo1 = new Luogo("Via Roma 10", "centro");
		Passeggero passeggero1 = new Passeggero(luogo1);
		Passeggero passeggero2 = new Passeggero(luogo1);
		Passeggero passeggero3 = new Passeggero(luogo1);
		Passeggero passeggero4 = new Passeggero(luogo1);
		Taxi t = company.chiamataTaxi(passeggero1);
		assertNotNull(t);
		t = company.chiamataTaxi(passeggero2);
		assertNotNull(t);
		t = company.chiamataTaxi(passeggero3);
		assertNull(t);
		t = company.chiamataTaxi(passeggero4);
		assertNull(t);
		assertEquals(company.getCorsePerse(),2);

	}
	
	public void test_inizio_fineCorsa0() throws InvalidTaxiName {
		TaxiCompany company = new TaxiCompany();
		company.addTaxi("Taxi5");
		Luogo luogo1 = new Luogo("Via Roma 10", "centro");
		Luogo luogo2 = new Luogo("Corso Francia 105", "cit turin");
		Passeggero passeggero1 = new Passeggero(luogo1);
		Taxi t = company.chiamataTaxi(passeggero1);
		Queue<Taxi> queue = company.getLiberi();
		assertEquals(queue.size(),0);
		t.inizioCorsa(luogo2);
		t.fineCorsa();
		assertEquals(passeggero1.getPosizione(),luogo2);
	}
	
	public void test_inizio_fineCorsa1() throws InvalidTaxiName {
		TaxiCompany company = new TaxiCompany();
		company.addTaxi("Taxi5");
		Luogo luogo1 = new Luogo("Via Roma 10", "centro");
		Luogo luogo2 = new Luogo("Corso Francia 105", "cit turin");
		Passeggero passeggero1 = new Passeggero(luogo1);
		Taxi t = company.chiamataTaxi(passeggero1);
		Queue<Taxi> queue = company.getLiberi();
		assertEquals(queue.size(),0);
		t.inizioCorsa(luogo2);
		t.fineCorsa();
		queue = company.getLiberi();
		assertEquals(queue.size(),1);
		assertEquals(queue.element().toString(),"Taxi5");
	}
	
	public void test_inizio_fineCorsa2() throws InvalidTaxiName {
		TaxiCompany company = new TaxiCompany();
		company.addTaxi("Taxi5");
		company.addTaxi("Taxi6");
		Luogo luogo1 = new Luogo("Via Roma 10", "centro");
		Luogo luogo2 = new Luogo("Corso Francia 105", "cit turin");
		Passeggero passeggero1 = new Passeggero(luogo1);
		Taxi t = company.chiamataTaxi(passeggero1);
		Queue<Taxi> queue = company.getLiberi();
		assertEquals(queue.size(),1);
		assertEquals(queue.element().toString(),"Taxi6");
		t.inizioCorsa(luogo2);
		t.fineCorsa();
		queue = company.getLiberi();
		assertEquals(queue.size(),2);
		assertEquals(queue.remove().toString(),"Taxi6");
		assertEquals(queue.element().toString(),"Taxi5");
	}
	
	public void test_inizio_fineCorsa3() throws InvalidTaxiName {
		TaxiCompany company = new TaxiCompany();
		company.addTaxi("Taxi5");
		company.addTaxi("Taxi6");
		Luogo luogo1 = new Luogo("Via Roma 10", "centro");
		Luogo luogo2 = new Luogo("Corso Francia 105", "cit turin");
        Luogo luogo3 = new Luogo("Corso Duca Abruzzi 24", "crocetta");
		Passeggero passeggero1 = new Passeggero(luogo1);
        Passeggero passeggero2 = new Passeggero(luogo2);
		Taxi t1 = company.chiamataTaxi(passeggero1);
		Taxi t2 = company.chiamataTaxi(passeggero2);
		Queue<Taxi> queue = company.getLiberi();
		assertEquals(queue.size(),0);
		t1.inizioCorsa(luogo2);
		t2.inizioCorsa(luogo3);
		t2.fineCorsa();
		assertEquals(passeggero2.getPosizione(),luogo3);
		queue = company.getLiberi();
		assertEquals(queue.size(),1);
		assertEquals(queue.element().toString(),"Taxi6");
		t1.fineCorsa();
		assertEquals(passeggero1.getPosizione(),luogo2);
		queue = company.getLiberi();
		assertEquals(queue.size(),2);
		assertEquals(queue.remove().toString(),"Taxi6");
		assertEquals(queue.element().toString(),"Taxi5");
	}
}
