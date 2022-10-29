import java.lang.reflect.InvocationTargetException;

import junit.framework.TestCase;

import elezioni.*;
import javax.swing.*;

public class TestR5_Gui extends TestCase {

  public TestR5_Gui(String arg0) {
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
		l = new Lista("Lista 1", "Ad maiora");
		l.assegnaCapolista(elezione.getElettore("Giuseppe", "Verdi"));
		l.aggiungiCandidato(elezione.getElettore("Mario", "Rossi"));
		l.aggiungiCandidato(elezione.getElettore("N2", "C2"));
		elezione.registraLista(l);

		l = new Lista("Lista 2", "Mens Sana in Corpore Sano");
		elezione.registraLista(l);
		
		gui = new GuiCreaListe(elezione);
		gui.show();
	}
	
	public void tearDown(){
		gui.hide();
		gui.dispose();
	}

	Elezione elezione;
	GuiCreaListe gui;
	Lista l;
	

    public void testCreaLista() throws InterruptedException, InvocationTargetException{
    	int nListeIniziale = gui.liste.getModel().getSize();
    	
    	gui.nome.setText("Nuova Lista");
    	gui.motto.setText("Tutto puo' succedere");
    	
    	gui.registraLista.doClick();
    	
    	SwingUtilities.invokeAndWait(new Runnable(){public void run(){}});
    	
		int nListeFinale = gui.liste.getModel().getSize();
    	
    	assertEquals(2,nListeIniziale);
    	assertEquals(nListeIniziale+1,nListeFinale);
    }

	public void testAggiungiCapolista() throws InterruptedException, InvocationTargetException{
		int cIndex=-1;
		for(int i=0; i<elezione.getElettori().size();++i){
			gui.cittadini.setSelectedIndex(i);
			String item = gui.cittadini.getSelectedValue().toString();
			if(item.startsWith("Giovanni")){
				cIndex = i;
				break;
			}
		}
		assertTrue("Giovanni non presente",cIndex>=0);
    	
    	int lIndex=-1;
		for(int i=0; i<2;++i){
			gui.liste.setSelectedIndex(i);
			if(gui.liste.getSelectedValue().toString().endsWith("2")){
				lIndex = i;
				break;
			}
		}
    	assertTrue("Lista 2 non presente",lIndex>=0);
    	
    	gui.addCapoLista.doClick();
    	
		SwingUtilities.invokeAndWait(new Runnable(){public void run(){}});
		
		Cittadino capo = l.getCapolista();
		assertEquals("Giovanni",capo.getNome());
		assertEquals("Bianchi",capo.getCognome());
	}

	public void testAggiungiCandidato() throws InterruptedException, InvocationTargetException{
		int cIndex=-1;
		for(int i=0; i<elezione.getElettori().size();++i){
			gui.cittadini.setSelectedIndex(i);
			String item = gui.cittadini.getSelectedValue().toString();
			if(item.startsWith("Giovanni")){
				cIndex = i;
				break;
			}
		}
		assertTrue("Giovanni non presente",cIndex>=0);
    	
			int lIndex=-1;
		for(int i=0; i<2;++i){
			gui.liste.setSelectedIndex(i);
			if(gui.liste.getSelectedValue().toString().endsWith("2")){
				lIndex = i;
				break;
			}
		}
		assertTrue("Lista 2 non presente",lIndex>=0);
    	
    	gui.addCandidato.doClick();
    	
		SwingUtilities.invokeAndWait(new Runnable(){public void run(){}});

		Cittadino c = (Cittadino) l.getCandidati().iterator().next();
		
		assertEquals("Giovanni",c.getNome());
		assertEquals("Bianchi",c.getCognome());
	}
}
