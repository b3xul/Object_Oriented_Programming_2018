package test;

import gestioneTaxi.*;
import junit.framework.TestCase;
import org.junit.Test;

public class TestR1_Agenzia_Taxi extends TestCase {

	@Test
	public void testAgenzia() {
		TaxiCompany company = new TaxiCompany();
		assertNotNull(company);
	}
	public void testTaxi() {
		Taxi taxi = new Taxi("Taxi1");
		assertNotNull(taxi);
		assertEquals(taxi.toString(),"Taxi1");
	}
}
