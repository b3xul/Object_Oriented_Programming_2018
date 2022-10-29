package abbigliamento;

public class Modello {
	
	private String nome;
	private double costo;
	private double quantita;
	

	public Modello(String nome, double costoFisso, double quantitaTessuto) {
		this.nome = nome;
		this.costo = costoFisso;
		this.quantita = quantitaTessuto;
	}

	public String getNome(){
		return nome;
	}
	public double getCosto(){
		return costo;
	}
	
	public double getQuantita() {
		return quantita;
	}

}
