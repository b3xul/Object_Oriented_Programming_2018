import java.util.Collection;
import java.util.Iterator;

import junit.framework.TestCase;

import elezioni.*;

public class TestR4_Risultati extends TestCase {

  public TestR4_Risultati(String arg0) {
    super(arg0);
  }

  protected void setUp() throws Exception {
    super.setUp();
		elezione = new Elezione();
		elezione.aggiungiElettore("Giuseppe", "Verdi");
		elezione.aggiungiElettore("Giovanni", "Bianchi");
		elezione.aggiungiElettore("Mario", "Rossi");
		elezione.aggiungiElettore("N1", "C1");
		elezione.aggiungiElettore("N2", "C2");
		elezione.aggiungiElettore("N3", "C3");
		elezione.aggiungiElettore("N4", "C4");
		elezione.aggiungiElettore("N5", "C5");
		Lista l = new Lista("Lista 1", "Ad maiora");
		l.assegnaCapolista(elezione.getElettore("Giuseppe", "Verdi"));
		l.aggiungiCandidato(elezione.getElettore("Mario", "Rossi"));
		l.aggiungiCandidato(elezione.getElettore("N2", "C2"));
		elezione.registraLista(l);

		l = new Lista("Lista 2", "Mens Sana in Corpore Sano");
		l.assegnaCapolista(elezione.getElettore("Giovanni", "Bianchi"));
		l.aggiungiCandidato(elezione.getElettore("N1", "C1"));
		elezione.registraLista(l);
		
		Iterator it=elezione.getElettori().iterator();
		
		elezione.vota((Cittadino)it.next(),"Lista 1");
		elezione.vota((Cittadino)it.next(),"Lista 2");
		elezione.vota((Cittadino)it.next(),"Lista 1","Giuseppe","Verdi");
		elezione.vota((Cittadino)it.next(),"Lista 1","Mario","Rossi");
		elezione.vota((Cittadino)it.next(),"Lista 1","N2","C2");
		elezione.vota((Cittadino)it.next(),"Lista 2","Giovanni","Bianchi");
		elezione.vota((Cittadino)it.next(),"Lista 2","N1","C1");
	}

	Elezione elezione;
	
	public void testListe(){
		assertEquals(7,elezione.getNumeroVotanti());
		Collection liste = elezione.getRisultatiListe();
		long totale = elezione.getNumeroVotanti();
		long prev = totale + 1;
		for (Iterator iter = liste.iterator(); iter.hasNext();) {
      		Lista lista = (Lista) iter.next();
      		long n = lista.getNumeroVoti();
      		assertTrue(n<=prev);
      		prev = n;
    	}
	}

	public void testListePercentuali(){
		Collection liste = elezione.getRisultatiListe();
		long totale = elezione.getNumeroVotanti();
		long prev = totale + 1;
		for (Iterator iter = liste.iterator(); iter.hasNext();) {
					Lista lista = (Lista) iter.next();
					long n = lista.getNumeroVoti();
					double perc = ((double)n)/(double)totale;
					assertEquals(perc,lista.getPercentualeVoti(),0.001);
			}
	}

	public void testCandidati(){
		Collection candidati = elezione.getRisultatiCandidati();
		assertEquals(5,candidati.size());
		long totale = elezione.getNumeroVotanti();
		long prev = totale + 1;
		for (Iterator iter = candidati.iterator(); iter.hasNext();) {
			Cittadino candidato = (Cittadino) iter.next();
			long n = candidato.getNumeroVoti();
			assertTrue(n<=prev);
			prev = n;
		}
	}

}
