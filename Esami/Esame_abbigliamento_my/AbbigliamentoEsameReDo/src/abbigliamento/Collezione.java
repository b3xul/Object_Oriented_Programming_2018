package abbigliamento;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Collezione {
	
	public String nome;
	List<Capo> capi = new ArrayList<>();

	public void add(Capo capo) {
		capi.add(capo);
	}

	public Collection trova(Colore colore) {
		return capi.stream().filter(c -> c.colore == colore).collect(Collectors.toList());
	}

	public Collection trova(Materiale materiale) {
		return capi.stream().filter(c -> c.materiale == materiale).collect(Collectors.toList());
	}

	public Collection trova(Modello modello) {
		return capi.stream().filter(c -> c.modello == modello).collect(Collectors.toList());
	}

}
