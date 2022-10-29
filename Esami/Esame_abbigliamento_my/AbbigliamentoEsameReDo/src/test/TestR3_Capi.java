package test;
import abbigliamento.Capo;
import abbigliamento.Colore;
import abbigliamento.Materiale;
import abbigliamento.Modello;
import junit.framework.TestCase;

public class TestR3_Capi extends TestCase {
	
	public void testCapoPrezzo() throws Exception {
		double costoFisso = 3.0;
		double quantita = 1.5;
		double costoMat = 0.5;
		Modello mod = new Modello("pantaloni",costoFisso,quantita);
		Materiale mat = new Materiale("cotone",costoMat);
		Colore col = new Colore("rosso");
		
		Capo c = new Capo(mod,mat,col);
		
		double atteso = costoFisso + quantita * costoMat;
		double prezzo = c.prezzo();
		
		assertEquals(atteso,prezzo,0.001);
	}

	public void testCapoString() throws Exception {
		double costoFisso = 3.0;
		double quantita = 1.5;
		double costoMat = 0.5;
		
		String nomeModello = "pantaloni";
		String nomeMateriale = "cotone";
		String nomeColore = "rosso";
		Modello mod = new Modello(nomeModello,costoFisso,quantita);
		Materiale mat = new Materiale(nomeMateriale,costoMat);
		Colore col = new Colore(nomeColore);
		
		Capo c = new Capo(mod,mat,col);
		
		String atteso = nomeModello + " " + nomeColore + " di " + nomeMateriale; 
		String risultato = c.toString();
		
		assertEquals(atteso,risultato);
	}
}
