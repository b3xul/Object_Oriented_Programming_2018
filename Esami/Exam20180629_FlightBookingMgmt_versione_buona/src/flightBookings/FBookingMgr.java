package flightBookings;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;
import java.util.stream.Collectors;
import static java.util.Comparator.*;
import java.util.Comparator;

public class FBookingMgr {

	Set<String> fareTypes=new HashSet<>();
	SortedMap<String,String> flight_destination=new TreeMap<>();
	Map<String,Offer> offers=new HashMap<>();
	Map<String,Request> requests=new HashMap<>();
	Map<String,Integer> destination_seatsRequired=new HashMap<>();
	Map<String,Booking> bookings=new HashMap<>();
	
	//R1
	public void addFareTypes(String... names) throws FBMException {
		if(!fareTypes.isEmpty())
			throw new FBMException("Doppia invocazione a addFareTypes");

		for(String f:names)
			fareTypes.add(f);
		
		return;
	}
	
	public List<String> addFlightsForDestination(String destName, String... flightIds) {
		List<String> temp=new ArrayList<>();
		
		for(String f:flightIds) {
			if(!flight_destination.containsKey(f)) {
				flight_destination.put(f, destName);
				temp.add(f);
			}
		}
		
		return temp.stream().sorted().collect(toList());
	}
	
	//R2
	public void addOffer(String offerId, String flightId, int nSeats, String fareTypeName, int price) throws FBMException {
		if(!fareTypes.contains(fareTypeName))
			throw new FBMException("FareType non esistente");
		
		if(!flight_destination.containsKey(flightId))
			throw new FBMException("Volo non presente");
		
		String destination=flight_destination.get(flightId);
		offers.put(offerId, new Offer(offerId,flightId,nSeats,fareTypeName,price,destination));
		
	}
	
	public void addRequest(String requestId, String destName, String fareTypeName, int maxPrice, int nSeats) throws FBMException {
		if(!fareTypes.contains(fareTypeName))
			throw new FBMException("FareType non esistente");
		
		if(!flight_destination.containsValue(destName))
			throw new FBMException("Destinazione non presente");

		requests.put(requestId, new Request(requestId,destName,fareTypeName,maxPrice,nSeats));
		
		int seatsRequired=0;
		if(destination_seatsRequired.containsKey(destName))
			seatsRequired=destination_seatsRequired.get(destName);
		
		destination_seatsRequired.put(destName, (seatsRequired+nSeats));
	}
	
	public int getNRequests(String destination) {
		List<Request> r=reqPerDestination(destination);
		return r.size();
	}

	private List<Request> reqPerDestination(String destination){
		return requests.values().stream()
				.filter(r->r.getDestination()==destination)
				.collect(toList());
	}
	
	public int getNOffers(String destination) {
		List<Offer> o=offPerDestination(destination);
		return o.size();
	}
	
	private List<Offer> offPerDestination(String destination){
		return offers.values().stream()
				.filter(o->o.getDestination()==destination)
				.collect(toList());
	}
	//R3
	public void addBooking(String bookingId, String requestId, String offerId) 
			throws FBMException {
		Request tempR=requests.get(requestId);
		Offer tempO=offers.get(offerId);
		
		if(tempR.getDestination()!=tempO.getDestination())
			throw new FBMException("Destinazioni non compatibili");
		
		if(tempR.getFareTypeName()!=tempO.getFareTypeName())
			throw new FBMException("FareTypes non compatibili");
		
		if(tempR.getMaxPrice()<tempO.getPrice())
			throw new FBMException("Costo troppo alto");
		
		if(tempR.getnSeats()>tempO.getSeatsAvailable()) 
			throw new FBMException("Posti richiesti troppo alti");
		tempO.decreaseAvailableSeats(tempR.getnSeats());
		
		int totalPrice=tempR.getnSeats()*tempO.getPrice();
		
		bookings.put(bookingId, new Booking(bookingId, requestId, offerId, tempR, tempO,tempR.getFareTypeName(),totalPrice));
		
	}

	public int getTotalPrice(String bookingId) throws FBMException {
		if(!bookings.containsKey(bookingId))
			throw new FBMException("Prenotazione non esistente");
		
		return bookings.get(bookingId).getTotalPrice();
	}
		
	//R4
	public SortedMap<Integer, List<String>> destinationsPerNSeats() {
//		SortedMap<Integer,List<String>> destinationsPerSeatsRequired=requests.values()
//				.collect(groupingBy( summingInt(Request::getnSeats) ,
//						()->new TreeMap<Integer,List<String>>(reverseOrder()) ,
//						Request::getDestination));
		SortedMap<Integer,List<String>> finale=destination_seatsRequired.entrySet().stream()
				.collect(Collectors.groupingBy(Map.Entry::getValue,
									()->new TreeMap<Integer,List<String>>(reverseOrder()),
									mapping(Map.Entry::getKey,toList() ) ) );
		
		return finale;
	}

	public SortedMap<String, Integer> revenuesPerFareType() {
		SortedMap<String,Integer> finale=bookings.values().stream()
				.collect(groupingBy(Booking::getFareTypeName, TreeMap::new, summingInt(Booking::getTotalPrice) ) );
		return finale;
	}
}
