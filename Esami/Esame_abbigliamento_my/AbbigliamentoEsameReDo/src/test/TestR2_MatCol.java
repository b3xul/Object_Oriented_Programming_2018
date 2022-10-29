package test;
import abbigliamento.Colore;
import abbigliamento.Materiale;
import junit.framework.TestCase;

public class TestR2_MatCol extends TestCase {

//	public void testMateriali() throws Exception {
//		
//		Materiale m = new Materiale("nome",0.1);
//		
//		Colore c = new Colore("rosso");
//	}

	public void testGettersMateriale() throws Exception {
		String nome = "cotone";
		double costo = 0.8;
		Materiale m = new Materiale(nome,costo);
		
		assertEquals(nome,m.getNome());
		assertEquals(costo,m.getCosto(),0.001);
	}

	public void testGetterColore() throws Exception {
		String nome = "verde";
		Colore c = new Colore(nome);
		
		assertEquals(nome,c.getNome());
	}
}
