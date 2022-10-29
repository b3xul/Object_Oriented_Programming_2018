package test;

import gestioneTaxi.Corsa;
import gestioneTaxi.InfoI;
import gestioneTaxi.InvalidTaxiName;
import gestioneTaxi.Luogo;
import gestioneTaxi.Passeggero;
import gestioneTaxi.Taxi;
import gestioneTaxi.TaxiCompany;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

public class TestR6_Statistiche extends TestCase {
	
	public void test_statisticheTaxi1() throws InvalidTaxiName {
		TaxiCompany company = new TaxiCompany();
		company.addTaxi("Taxi8");
		company.addTaxi("Taxi9");
		Luogo luogo1 = new Luogo("Via Vela 10", "crocetta");
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
		ArrayList<InfoI> info =  company.statisticheTaxi();
		assertNotNull(info);
		assertEquals(info.size(),2);
	}
	
	public void test_statisticheTaxi2() throws InvalidTaxiName {
		TaxiCompany company = new TaxiCompany();
		company.addTaxi("Taxi8");
		company.addTaxi("Taxi9");
		Luogo luogo1 = new Luogo("Via Vela 10", "crocetta");
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
		ArrayList<InfoI> info =  company.statisticheTaxi();
		assertNotNull(info);
		assertEquals(info.size(),2);
		assertEquals(info.get(0).getId(), "Taxi8");	
		assertEquals(info.get(0).getValore(), 2);
		assertEquals(info.get(1).getId(), "Taxi9");	
		assertEquals(info.get(1).getValore(), 1);
	}
	
	public void test_statisticheQuartieri1() throws InvalidTaxiName {
		TaxiCompany company = new TaxiCompany();
		company.addTaxi("Taxi8");
		company.addTaxi("Taxi9");
		Luogo luogo1 = new Luogo("Via Vela 10", "crocetta");
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
		ArrayList<InfoI> info =  company.statisticheQuartieri();
		assertNotNull(info);
		assertEquals(info.size(),2);
	}
	
	public void test_statisticheQuartieri2() throws InvalidTaxiName {
		TaxiCompany company = new TaxiCompany();
		company.addTaxi("Taxi8");
		company.addTaxi("Taxi9");
		Luogo luogo1 = new Luogo("Via Vela 10", "crocetta");
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
		ArrayList<InfoI> info =  company.statisticheQuartieri();
		assertNotNull(info);
		assertEquals(info.size(),2);
		assertEquals(info.get(0).getId(), "crocetta");	
		assertEquals(info.get(0).getValore(), 2);
		assertEquals(info.get(1).getId(), "cit turin");	
		assertEquals(info.get(1).getValore(), 1);
	}
}
