package carShare;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class CarService {
	private Map<String,Parking> parcheggi = new TreeMap<>();
	private Map<String,Car> allcars = new TreeMap<>();
	private Map<String, Iscritto> iscritti = new TreeMap<>();
	private List<Nota> note = new LinkedList<>();
	private int NTotimport=0;
	private Double Totimporti=0.0;
	
	
	public void addParking(String name) throws InvalidName{
		Parking p = new Parking(name);
		if(parcheggi.containsKey(name)){throw new InvalidName();}
		parcheggi.put(name,p);  // add parcheggi alla mappa
	}

	public void addCar(String parking, String licencePlate, double minRate, double kmRate) throws InvalidName{
		if(!parcheggi.containsKey(parking) || allcars.containsKey(licencePlate)){throw new InvalidName();}
		Car c = new Car(parcheggi.get(parking),licencePlate,minRate,kmRate);
		allcars.put(c.getLicencePlate(),c);
	}
		
	public SortedSet<String> getAvailableCars(String parking) throws InvalidName{   // TODO
		if(!parcheggi.containsKey(parking)){throw new InvalidName();}
		return parcheggi.get(parking).getCars().values().stream().filter(c -> (c.isPrenotata()==false)).map(c -> (c.getLicencePlate())).collect(Collectors.toCollection(TreeSet::new));
	}
	
	public void addSubscriber(String card) throws InvalidName{
		if(iscritti .containsKey(card)){throw new InvalidName();}
		Iscritto i= new Iscritto(card);
		iscritti.put(card,i);  // aggiungo iscritti alla mappa
	}
	
	public List<String> getSubscribers(){
		return iscritti.keySet().stream().sorted().collect(Collectors.toList());
	}
	
	public String reserve(String card, String parking) throws InvalidName{
		if(!parcheggi.containsKey(parking) || !iscritti.containsKey(card)){throw new InvalidName();}
		Parking p = parcheggi.get(parking);
		Iscritto i = iscritti.get(card);
		if(i.isPrenotato()==true){return null;}
		if(p.tuttopieno()==false){
		Car c= p.prenota(i);
		i.prenota(c);
		return c.getLicencePlate();}
		else{
			return null;
		}
	}
	
	public String release(String card, String plate) throws InvalidName{
		if(!iscritti.containsKey(card) || !allcars.containsKey(plate) ){throw new InvalidName();}
		Car c=allcars.get(plate);
		Iscritto i=iscritti.get(card);
		if((c.getIscritto().getCard().equals(card))==false){return null;}
		// car prenotata dall'iscritto giusto
		c.togliPrenota();
		i.togliPrenota();
		return c.getLicencePlate();
		
	}
	// R3 #################################################################################################################################
	
	public Set<String> getReserved(String parking) throws InvalidName{
		if(!parcheggi.containsKey(parking)){throw new InvalidName();}
		Parking p = parcheggi.get(parking);
		return p.getCars().values().stream().filter(c -> c.isPrenotata()==true).map(c -> c.getLicencePlate()).collect(Collectors.toCollection(TreeSet::new));
	}
	
	public String useCar(String card, String plate) throws InvalidName{
		if(!iscritti.containsKey(card) || !allcars.containsKey(plate) ){throw new InvalidName();}
		Car c=allcars.get(plate);
		//Iscritto i=iscritti.get(card);
		if(c.getIscritto()== null || (c.getIscritto().getCard().equals(card))==false){return null;}
		c.esci();
		return c.getLicencePlate();
		
	}
	
	public String terminate(String card, String plate, String parking, int min, int km) throws InvalidName{
		if(!iscritti.containsKey(card) || !parcheggi.containsKey(parking) ){throw new InvalidName();}
		Car c=allcars.get(plate);
		//Iscritto i=iscritti.get(card);
		Parking p=parcheggi.get(parking);
		if(c.getIscritto()== null || (c.getIscritto().getCard().equals(card))==false){return null;}
		this.release(card, plate);  // parte CRITICA TODO
		Parking partenza = c.getParking();
		c.setParking(p);
		p.addCar(c);
		Double importo= min*c.getMinRate() + km*c.getKmRate();
		Totimporti+=importo;
		NTotimport++;
		String not= card + ":" + plate + ":" + importo + ":" + partenza.getName() + ":" + parking;
		Nota n = new Nota(not,card,plate,importo,partenza.getName(),parking);
		note.add(n);
		return not;
	}

	public List<String> charges() {
		return note.stream().sorted(Comparator.comparingDouble(n -> ((Nota) n).getImporto()).reversed()).map(n -> n.getStringa()).collect(Collectors.toList());
	}
		
	public List<String> subscriberCharges(String card) throws InvalidName{
		if(!iscritti.containsKey(card)){throw new InvalidName();}
		return note.stream().filter(n -> n.getCard().equals(card)==true).map(n -> n.getStringa()).collect(Collectors.toList());
	}
	
	public double averageCharge() {
		return Totimporti/NTotimport;
	}
	
	public long departuresFrom(String parking) throws InvalidName{
		if(!parcheggi.containsKey(parking)){throw new InvalidName();}
		return note.stream().filter(n -> n.getPartenza().equals(parking)==true).collect(Collectors.counting());
	}
}
