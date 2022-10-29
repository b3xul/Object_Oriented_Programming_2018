package test;

import junit.framework.TestCase;
import gestioneTaxi.*;

import org.junit.Test;

public class TestR2_R3_Luogo_Passeggero extends TestCase{

	@Test
	public void testLuogo() {
		TaxiCompany company = new TaxiCompany();
		Luogo luogo1 = new Luogo("Via Roma 10", "centro");
		assertNotNull(luogo1);
		assertEquals(luogo1.toString(),"Via Roma 10");		
	}
	public void testPasseggero() {
		TaxiCompany company = new TaxiCompany();
		Luogo luogo1 = new Luogo("Via Roma 10", "centro");;
		assertNotNull(luogo1);	
		Passeggero passeggero1 = new Passeggero(luogo1);
		assertNotNull(passeggero1);
		Luogo luogo2 = passeggero1.getPosizione();
		assertEquals(luogo1.toString(),luogo2.toString());
	}
}
