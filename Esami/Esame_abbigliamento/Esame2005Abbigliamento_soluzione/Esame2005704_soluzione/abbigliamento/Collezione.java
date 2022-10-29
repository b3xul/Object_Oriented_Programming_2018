package abbigliamento;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class Collezione {

	private Collection capi = new LinkedList();
	
	public void add(Capo capo) {
		capi.add(capo);
	}

	public Collection trova(Colore colore) {
		LinkedList richiesti = new LinkedList();
		for (Iterator iter = capi.iterator(); iter.hasNext();) {
			Capo capo = (Capo) iter.next();
			if(capo.colore == colore){
				richiesti.add(capo);
			}
		}
		return richiesti;
	}

	public Collection trova(Materiale materiale) {
		LinkedList richiesti = new LinkedList();
		for (Iterator iter = capi.iterator(); iter.hasNext();) {
			Capo capo = (Capo) iter.next();
			if(capo.materiale == materiale){
				richiesti.add(capo);
			}
		}
		return richiesti;
	}

	public Collection trova(Modello modello) {
		LinkedList richiesti = new LinkedList();
		for (Iterator iter = capi.iterator(); iter.hasNext();) {
			Capo capo = (Capo) iter.next();
			if(capo.modello == modello){
				richiesti.add(capo);
			}
		}
		return richiesti;
	}

}
