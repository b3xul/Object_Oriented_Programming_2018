package abbigliamento;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Abbigliamento {
	
	List<Modello> modelli = new ArrayList<>();
	List<Capo> capi = new ArrayList<>();
	List<Materiale> materiali = new ArrayList<>();
	List<Colore> colori = new ArrayList<>();
	List<Collezione> collezioni = new ArrayList<>();
	
	public void leggiFile(String fileName) throws FileNotFoundException{
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		br.lines().forEach(l -> {
			String[] tokens = l.split(";");
			if(tokens[0].compareTo("MOD") == 0) {
				Modello mod = new Modello(tokens[1], Double.parseDouble(tokens[2]), Double.parseDouble(tokens[3]));
				modelli.add(mod);
			}
			else if(tokens[0].compareTo("COL") == 0) {
				if(tokens.length == 2) {
					Colore c = new Colore(tokens[1]);
					colori.add(c);
				}
				else {
					Collezione c = new Collezione();
					Arrays.stream(tokens).skip(2).forEach(s -> {
						c.add(capi.stream().filter(capo -> capo.toString() == s).findFirst().orElse(null));
					});
					collezioni.add(c);
				}
			}
			else if(tokens[0].compareTo("MAT") == 0) {
				Materiale mat = new Materiale(tokens[1], Double.parseDouble(tokens[2]));
				materiali.add(mat);
			}
			else if(tokens[0].compareTo("CAP") == 0) {
				Capo cap = new Capo(modelli.stream().filter(m -> m.getNome().compareTo(tokens[2]) == 0).findFirst().orElse(null), 
								   materiali.stream().filter(m -> m.getNome().compareTo(tokens[3]) == 0).findFirst().orElse(null),
								   colori.stream().filter(m -> m.getNome().compareTo(tokens[4]) == 0).findFirst().orElse(null));
				cap.nome = tokens[1];
				capi.add(cap);
			}
		});
		
	}

	public Modello getModello(String name){
		return modelli.stream().filter(m -> m.getNome().compareTo(name) == 0).findFirst().orElse(null);
	}

	public Colore getColore(String name){
		return colori.stream().filter(c -> c.getNome().compareTo(name) == 0).findFirst().orElse(null);
	}

	public Materiale getMateriale(String name){
		return materiali.stream().filter(m -> m.getNome().compareTo(name) == 0).findFirst().orElse(null);
	}

	public Capo getCapo(String name){
		return capi.stream().filter(c -> c.nome.compareTo(name) == 0).findFirst().orElse(null);
	}

	public Collezione getCollezione(String name){
		return collezioni.stream().filter(c -> c.nome.compareTo(name) == 0).findFirst().orElse(null);
	}

}
