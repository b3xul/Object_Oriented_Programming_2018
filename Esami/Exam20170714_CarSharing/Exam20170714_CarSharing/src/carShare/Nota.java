package carShare;

public class Nota {

	private String stringa;
	private String card;
	private String plate;
	private Double importo;
	private String partenza;
	private String arrivo;

	public Nota(String not, String card, String plate, Double importo,
			String name, String parking) {
		this.stringa=not;
		this.card=card;
		this.plate=plate;
		this.importo=importo;
		this.partenza=name;
		this.arrivo=parking;
	}

	public String getStringa() {
		return stringa;
	}

	public String getCard() {
		return card;
	}

	public String getPlate() {
		return plate;
	}

	public Double getImporto() {
		return importo;
	}

	public String getPartenza() {
		return partenza;
	}

	public String getArrivo() {
		return arrivo;
	}

}
