package abbigliamento;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Abbigliamento {
	
	Map modelli = new HashMap();
	Map colori = new HashMap();
	Map materiali = new HashMap();
	Map capi = new HashMap();
	
	public void leggiFile(String fileName){
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			
			String line;
			while((line=reader.readLine())!=null){
				StringTokenizer tok = new StringTokenizer(line,";");
				String type = tok.nextToken();
				
				if(type.equals("MOD")){
					String nome = tok.nextToken();
					double cf = Double.parseDouble(tok.nextToken());
					double q = Double.parseDouble(tok.nextToken());
					modelli.put(nome,new Modello(nome,cf,q));
				}
				if(type.equals("COL")){
					String nome = tok.nextToken();
					colori.put(nome,new Colore(nome));
				}
				if(type.equals("MAT")){
					String nome = tok.nextToken();
					double c = Double.parseDouble(tok.nextToken());
					materiali.put(nome,new Materiale(nome,c));
				}
				if(type.equals("CAP")){
					String nome = tok.nextToken();
					String mod = tok.nextToken();
					String mat = tok.nextToken();
					String col = tok.nextToken();
					Modello modello = getModello(mod);
					Materiale materiale = getMateriale(mat);
					Colore colore = getColore(col);
					capi.put(nome,new Capo(modello,materiale,colore));
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Modello getModello(String name){
		return (Modello)modelli.get(name);
	}

	public Colore getColore(String name){
		return (Colore)colori.get(name);
	}

	public Materiale getMateriale(String name){
		return (Materiale)materiali.get(name);
	}

	public Capo getCapo(String name){
		return (Capo)capi.get(name);
	}

	public Collezione getCollezione(String name){
		return null;
	}

}
