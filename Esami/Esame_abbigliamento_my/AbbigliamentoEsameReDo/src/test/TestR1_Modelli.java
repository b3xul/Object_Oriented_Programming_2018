package test;
import abbigliamento.Modello;
import junit.framework.TestCase;

public class TestR1_Modelli extends TestCase {

//	public void testModello() throws Exception {
//		Modello m = new Modello("pantaloni",1.0,1.0);
//	}

	public void testGetters() throws Exception {
		String nome = "pantaloni";
		double costoFisso = 1.0;
		double quantita = .5;
	
		Modello m = new Modello(nome,costoFisso,quantita);
		assertEquals(nome,m.getNome());
		assertEquals(costoFisso,m.getCosto(),0.001);
		assertEquals(quantita,m.getQuantita(),0.001);
	}

}
