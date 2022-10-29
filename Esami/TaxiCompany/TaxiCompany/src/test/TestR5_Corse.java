package test;

import gestioneTaxi.Corsa;
import gestioneTaxi.InvalidTaxiName;
import gestioneTaxi.Luogo;
import gestioneTaxi.Passeggero;
import gestioneTaxi.Taxi;
import gestioneTaxi.TaxiCompany;

import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

public class TestR5_Corse extends TestCase{

	@Test
	public void test_getCorse0() throws InvalidTaxiName {
		TaxiCompany company = new TaxiCompany();
		company.addTaxi("Taxi5");
		Luogo luogo1 = new Luogo("Via Roma 10", "centro");
		Luogo luogo2 = new Luogo("Corso Francia 105", "cit turin");
		Passeggero passeggero1 = new Passeggero(luogo1);
		Taxi t = company.chiamataTaxi(passeggero1);
		t.inizioCorsa(luogo2);
		t.fineCorsa();
		try{
			List<Corsa> corse = company.getCorse("Taxi5");
			assertNotNull(corse);
		}
		catch (InvalidTaxiName itn){
			fail("Il nome taxi è valido");
		}
		try{
			company.getCorse("Taxi6");
			fail("Nome taxi non valido");
		}
		catch (InvalidTaxiName itn){
		}
	}
	
	public void test_getCorse1() throws InvalidTaxiName {
		TaxiCompany company = new TaxiCompany();
		company.addTaxi("Taxi5");
		Luogo luogo1 = new Luogo("Via Roma 10", "centro");
		Luogo luogo2 = new Luogo("Corso Francia 105", "cit turin");
		Passeggero passeggero1 = new Passeggero(luogo1);
		Taxi t = company.chiamataTaxi(passeggero1);
		t.inizioCorsa(luogo2);
		t.fineCorsa();
		List<Corsa> corse = company.getCorse("Taxi5");
		assertNotNull(corse);
		assertEquals(corse.size(),1);
		assertEquals(corse.get(0).toString(),"Via Roma 10,Corso Francia 105");
	}
	
	public void test_getCorse2() throws InvalidTaxiName {
		TaxiCompany company = new TaxiCompany();
		company.addTaxi("Taxi8");
		company.addTaxi("Taxi9");
		Luogo luogo1 = new Luogo("Via Roma 10", "centro");
		Luogo luogo2 = new Luogo("Corso Francia 105", "cit turin");
		Luogo luogo3 = new Luogo("Corso Duca Abruzzi 24", "crocetta");
		Passeggero passeggero1 = new Passeggero(luogo1);
		Passeggero passeggero2 = new Passeggero(luogo1);
		Taxi t1 = company.chiamataTaxi(passeggero1);
		Taxi t2 = company.chiamataTaxi(passeggero2);
		t1.inizioCorsa(luogo2);
		t2.inizioCorsa(luogo3);
		t1.fineCorsa();
		t2.fineCorsa();
		List<Corsa> corse = company.getCorse("Taxi8");
		assertNotNull(corse);
		assertEquals(corse.size(),1);
		assertEquals(corse.get(0).toString(),"Via Roma 10,Corso Francia 105");
		corse = company.getCorse("Taxi9");
		assertNotNull(corse);
		assertEquals(corse.size(),1);
		assertEquals(corse.get(0).toString(),"Via Roma 10,Corso Duca Abruzzi 24");
	}
	
	public void test_getCorse3() throws InvalidTaxiName {
		TaxiCompany company = new TaxiCompany();
		company.addTaxi("Taxi8");
		company.addTaxi("Taxi9");
		Luogo luogo1 = new Luogo("Via Roma 10", "centro");
		Luogo luogo2 = new Luogo("Corso Francia 105", "cit turin");
		Luogo luogo3 = new Luogo("Corso Duca Abruzzi 24", "crocetta");
		Passeggero passeggero1 = new Passeggero(luogo1);
		Passeggero passeggero2 = new Passeggero(luogo2);
		Passeggero passeggero3 = new Passeggero(luogo3);
		Taxi t1 = company.chiamataTaxi(passeggero1);
		Taxi t2 = company.chiamataTaxi(passeggero2);
		t1.inizioCorsa(luogo2);
		t2.inizioCorsa(luogo3);
		t1.fineCorsa();
		Taxi t3 = company.chiamataTaxi(passeggero3);
		t3.inizioCorsa(luogo1);
		t2.fineCorsa();
		t3.fineCorsa();
		List<Corsa> corse = company.getCorse("Taxi8");
		assertNotNull(corse);
		assertEquals(corse.size(),2);
		assertEquals(corse.get(0).toString(),"Via Roma 10,Corso Francia 105");
		assertEquals(corse.get(1).toString(),"Corso Duca Abruzzi 24,Via Roma 10");
		corse = company.getCorse("Taxi9");
		assertNotNull(corse);
		assertEquals(corse.size(),1);
		assertEquals(corse.get(0).toString(),"Corso Francia 105,Corso Duca Abruzzi 24");
	}
}
