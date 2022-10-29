package carShare;

import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Parking {

	private String name;
	private Map<String,Car> cars = new TreeMap<>();

	public Parking(String name) {
		this.name=name;
	}

	public String getName() {
		return name;
	}

	public void addCar(Car car) {
		cars.put(car.getLicencePlate(),car);  // salvo nella mappa delle car del singolo parcheggio
	}

	public Map<String, Car> getCars() {
		return cars;
	}

	public Car prenota(Iscritto i) {
		String plate =cars.values().stream().filter(c -> c.isPrenotata()==false).limit(1).map(c -> c.getLicencePlate()).collect(Collectors.joining());
		Car c = cars.get(plate);
		c.prenota(i); // nella macchina ho messo prenotata e l'iscritto che l'ha prenotata.
		return c;
	}
	
	public boolean tuttopieno(){
		for(Car c:cars.values()){
			if(c.isPrenotata()==false){
				return false;
			}
		}
		return true;
	}

}
