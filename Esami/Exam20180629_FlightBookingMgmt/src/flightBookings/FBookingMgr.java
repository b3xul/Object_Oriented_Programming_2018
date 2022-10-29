package flightBookings;
import java.util.*;

//import static java.util.stream.Collectors.*;

public class FBookingMgr {

	private List<String> fareTypes=new ArrayList<>();
	private Boolean chiamato=false;
	private List<String> destinations=new ArrayList<>();
	private SortedSet<String> flights=new TreeSet<>();
	private Map<String,SortedSet<String>> flightsForDestination=new HashMap<>();
	
	private List<Offer> offers=new ArrayList<>();
	private List<Request> requests=new ArrayList<>();
	private Map<String,List<Offer>> offersForFlight=new HashMap<>();
	private Map<String,List<Request>> requestsForFlight=new HashMap<>();
	
	//R1
	public void addFareTypes(String... names) throws FBMException {
		
		if(chiamato==true)
			throw new FBMException("Chiamata multipla del metodo addFareTypes!");
		
		for(String s:names)
			fareTypes.add(s);
		chiamato=true;
		
		return;
	}
	
	public List<String> addFlightsForDestination(String destName, String... flightIds) {
		SortedSet<String> voliDest=new TreeSet<>();
		
		destinations.add(destName);
		for(String s:flightIds) {
			if(!flights.contains(s)) {
				flights.add(s);
				voliDest.add(s);
			}
		}
		
		flightsForDestination.put(destName, voliDest);
		
		return new ArrayList<String>(voliDest);
	}
	
	//R2
	public void addOffer(String offerId, String flightId, int nSeats, String fareTypeName, int price) 
				throws FBMException {
		if(!fareTypes.contains(fareTypeName))
			throw new FBMException("Tariffa non disponibile");
		if(!flights.contains(flightId))
			throw new FBMException("Aereo non presente");
		List<Offer> offerteVolo=new ArrayList<>();
		
		offers.add(new Offer(offerId,flightId,nSeats,fareTypeName,price));
		offerteVolo.add(offers.get(offers.size()-1));
		offersForFlight.put(flightId, offerteVolo);
		
	}
	
	public void addRequest(String requestId, String destName, String fareTypeName, 
			int maxPrice, int nSeats) throws FBMException {
		if(!fareTypes.contains(fareTypeName))
			throw new FBMException("Tariffa non disponibile");
		if(!destinations.contains(destName))
			throw new FBMException("Destinazione non presente");
		List<Request> richiesteVolo=new ArrayList<>();
		
		requests.add(new Request(requestId, destName,fareTypeName,maxPrice,nSeats));
		richiesteVolo.add(requests.get(requests.size()-1));
		requestsForFlight.put(destName, richiesteVolo);
		
	}
	
	public int getNRequests(String destination) {
		int tot=0;
		
		SortedSet<String> voli=flightsForDestination.get(destination);
		for(String v:voli)
			if(offersForFlight.containsKey(v))
				tot+=offersForFlight.get(v).size();
		
		return tot;
	}

	public int getNOffers(String destination) {
		int tot=0;
		
		for(String v:requestsForFlight.keySet())
			if(requestsForFlight.containsKey(destination))
				tot+=requestsForFlight.get(v).size();
		
		return tot;
	}
	
	//R3
	public void addBooking(String bookingId, String requestId, String offerId) 
			throws FBMException {
		
	}

	public int getTotalPrice(String bookingId) throws FBMException {
		return -1;
	}
		
	//R4
	public SortedMap<Integer, List<String>> destinationsPerNSeats() {
		return new TreeMap<Integer, List<String>>();
	}

	public SortedMap<String, Integer> revenuesPerFareType() {
		return new TreeMap<String, Integer>();
	}
}
