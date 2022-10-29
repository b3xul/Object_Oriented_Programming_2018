package abbigliamento;

public class Capo {
	
	public String nome;
	public Modello modello;
	public Materiale materiale;
	public Colore colore;

	public Capo(Modello modello, Materiale materiale, Colore colore) {
		this.modello = modello;
		this.materiale = materiale;
		this.colore = colore;
	}

	public double prezzo() {
		return modello.getCosto() + modello.getQuantita()*materiale.getCosto();
	}
	
	public String toString() {
		return modello.getNome() + " " + colore.getNome() + " di " + materiale.getNome();
	}

}
