package carShare;

public class Iscritto {

	private String card;
	private boolean prenotato=false;
	private Car car;

	public Iscritto(String card) {
		this.card=card;
	}

	public String getCard() {
		return card;
	}

	public boolean isPrenotato() {
		return prenotato;
	}

	public void prenota(Car c) {
		this.car=c;
		prenotato=true;
		
	}

	public void togliPrenota() {
		prenotato=false;
		this.car=null;
		
	}
}
