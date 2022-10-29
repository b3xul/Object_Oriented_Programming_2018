import java.util.Iterator;

import junit.framework.TestCase;

import elezioni.*;

public class TestR3_Votazione extends TestCase {

  public TestR3_Votazione(String arg0) {
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
    Lista l = new Lista("Lista 1", "Ad maiora");
    l.assegnaCapolista(elezione.getElettore("Giuseppe", "Verdi"));
    l.aggiungiCandidato(elezione.getElettore("Mario", "Rossi"));
    l.aggiungiCandidato(elezione.getElettore("N2", "C2"));
    elezione.registraLista(l);

    l = new Lista("Lista 2", "Mens Sana in Corpore Sano");
    l.assegnaCapolista(elezione.getElettore("Giovanni", "Bianchi"));
    l.aggiungiCandidato(elezione.getElettore("N1", "C1"));
    elezione.registraLista(l);
  }

  Elezione elezione;

  public void testVotoLista() throws TentatoDoppioVoto{
  	
  	Iterator it = elezione.getElettori().iterator();
  	
  	Cittadino c = (Cittadino)it.next();
  	elezione.vota(c,"Lista 1");
  	
  	assertTrue(c.haVotato());
  }

	public void testVotoListaConPreferenza() throws TentatoDoppioVoto, TaglioNonPermesso{
		Iterator it = elezione.getElettori().iterator();
  	
		Cittadino c = (Cittadino)it.next();
		elezione.vota(c,"Lista 1","Mario","Rossi");
  	
		assertTrue(c.haVotato());
		
		c = (Cittadino)it.next();
		try{
			elezione.vota(c,"Lista 2","Mario","Rossi");
			fail("La preferenza deve essere per un candidato della lista");
		}catch(TaglioNonPermesso e){}
		
	}

	public void testDoppioVoto() throws TentatoDoppioVoto{
		Iterator it = elezione.getElettori().iterator();
  	
		Cittadino c = (Cittadino)it.next();
		elezione.vota(c,"Lista 1");
  	
		try{
			elezione.vota(c,"Lista 1");
			fail("Doppio voto!");
		}catch(TentatoDoppioVoto dv){}

		try{
			elezione.vota(c,"Lista 2");
			fail("Doppio voto!");
		}catch(TentatoDoppioVoto dv){}
	}

}
