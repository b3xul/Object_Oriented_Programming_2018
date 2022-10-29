package carShare;

public class Car {

	private Parking parking;
	private String licencePlate;
	private double minRate;
	private double kmRate;
	private boolean prenotata=false;
	private Iscritto iscritto;
	private boolean nelParcheggio=true;

	public Car(Parking parking, String licencePlate, double minRate,
			double kmRate) {
		this.parking=parking;
		this.licencePlate=licencePlate;
		this.minRate=minRate;
		this.kmRate=kmRate;
		parking.addCar(this);
	}

	public Parking getParking() {
		return parking;	}

	public String getLicencePlate() {
		return licencePlate;
	}

	public double getMinRate() {
		return minRate;
	}

	public double getKmRate() {
		return kmRate;
	}

	public boolean isPrenotata() {
		return prenotata;
	}

	public void prenota(Iscritto i) {
		prenotata=true;
		iscritto=i;
	}

	public Iscritto getIscritto() {
		return iscritto;
	}
	public void togliPrenota() {
		prenotata=false;
		iscritto=null;
	}

	public void esci() {
		this.nelParcheggio=false;
		parking.getCars().remove(this.licencePlate); // tolgo la macchina dal parcheggio
	}
	
	public void setParking(Parking parking) {
		this.parking = parking;
	}
}
