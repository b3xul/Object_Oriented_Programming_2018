import java.util.Collection;

import junit.framework.TestCase;

import elezioni.*;

public class TestR1_Elettori extends TestCase {

  public TestR1_Elettori(String arg0) {
    super(arg0);
  }
  
  public void testAggiuntaElettore(){
  	Elezione e = new Elezione();
  	
  	String nome = "Giuseppe";
  	String cognome = "Verdi";
  	
  	Cittadino c = e.aggiungiElettore(nome,cognome);
  	
  	assertEquals(nome,c.getNome());
	assertEquals(cognome,c.getCognome());
	assertEquals(false,c.haVotato());
  }
  
  public void testElettori(){
	Elezione e = new Elezione();
	e.aggiungiElettore("Giuseppe","Verdi");
	e.aggiungiElettore("Giovanni","Bianchi");
	e.aggiungiElettore("Mario","Rossi");
	
	Collection elettori = e.getElettori();
	assertEquals(3,elettori.size());
  }
  
  public void testElettore(){
	Elezione e = new Elezione();
	String nome = "Giuseppe";
	String cognome = "Verdi";
  	
	e.aggiungiElettore(nome,cognome);
	e.aggiungiElettore("Giovanni","Bianchi");
	e.aggiungiElettore("Mario","Rossi");
	
	Cittadino c = e.getElettore(nome,cognome);
	
	assertEquals(nome,c.getNome());
	assertEquals(cognome,c.getCognome());
  }

}
