import java.util.Iterator;

import junit.framework.TestCase;

import elezioni.*;

public class TestR2_Lista extends TestCase {

  public TestR2_Lista(String arg0) {
    super(arg0);
  }
  
  public void setUp() throws Exception {
  	super.setUp();
	elezione = new Elezione();
	elezione.aggiungiElettore("Giuseppe","Verdi");
	elezione.aggiungiElettore("Giovanni","Bianchi");
	elezione.aggiungiElettore("Mario","Rossi");
  }
  
  Elezione elezione;

  public void testCreaLista(){
  	String nome = "Lista 1";
  	String motto = "Ad Maiora";
  	Lista l = new Lista(nome,motto);
  	
  	assertEquals(nome,l.getNome());
  	assertEquals(motto,l.getMotto());
  }
  
  public void testAggiungiCandidati() throws CandidatoNonValido{
	Lista l = new Lista("Lista 1","Ad Maiora");
	
	String nome = "Giuseppe";
	String cognome = "Verdi";

	Cittadino c = elezione.getElettore(nome,cognome);
	l.assegnaCapolista(c);
	l.aggiungiCandidato(elezione.getElettore("Mario","Rossi"));	
	elezione.registraLista(l);
	
	Cittadino capo = l.getCapolista();
	Cittadino cand = (Cittadino)l.getCandidati().iterator().next();

	assertEquals(nome,capo.getNome());
	assertEquals(cognome,capo.getCognome());
	assertTrue(capo.isCapolista());
	assertFalse(cand.isCapolista());
	
	elezione.registraLista(l);
  }
  
  public void testDoppieCandidature() throws CandidatoNonValido{
	Lista l1 = new Lista("Lista 1","Ad Maiora");
	Lista l2 = new Lista("Lista 2","Mens Sana in Corpore Sano");
	
	Iterator it = elezione.getElettori().iterator();
	Cittadino c1 = (Cittadino)it.next();
	Cittadino c2 = (Cittadino)it.next();
	
	l1.assegnaCapolista(c1);
	l1.aggiungiCandidato(c2);
	
	try{
		l2.assegnaCapolista(c1);
		fail("Il cittadino non può essere capolista 2 volte");
	}catch(CandidatoNonValido cnv){	}

	try{
		l2.aggiungiCandidato(c1);
		fail("Il cittadino non può essere capolista e candidato");
	}catch(CandidatoNonValido cnv){	}

	try{
		l2.aggiungiCandidato(c2);
		fail("Il cittadino non può essere candidato 2 volte");
	}catch(CandidatoNonValido cnv){	}
	
  }

}
