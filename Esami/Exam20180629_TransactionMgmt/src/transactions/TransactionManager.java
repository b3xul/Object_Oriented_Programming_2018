package transactions;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;
import static java.util.Comparator.*;

public class TransactionManager {
	Map<String,Region> place_region;
	
	Map<String,Region> regions;
	List<String> places;
	Map<String,Carrier> carriers;
	List<String> products;
	Map<String,Request> requests;
	Map<String,Offer> offers;
	Map<String,Transaction> transactions;
	
	public TransactionManager() {
		place_region=new HashMap<>();
		regions=new HashMap<>();
		places=new ArrayList<>();
		carriers=new HashMap<>();
		products=new ArrayList<>();
		requests=new HashMap<>();
		offers=new HashMap<>();
		transactions=new HashMap<>();
	}
	
//R1
	public List<String> addRegion(String regionName, String... placeNames) {
		Region tempR=new Region(regionName);
		Set<String> tempP=new HashSet<>();
		
		regions.put(regionName,tempR);
		for(String place:placeNames)
			if(!places.contains(place)) {
				places.add(place);
				place_region.put(place, tempR);
				tempP.add(place);
			}
		
		List<String> result=new ArrayList<String>(tempP).stream().sorted().collect(toList());
		tempR.setPlaces(result);
		
		return result;
		
		
//		SortedSet<Place> tempP=new TreeSet<>();
//		Region tempR=new Region(regionName);
//		
//		for(String place:placeNames)
//			tempP.add(new Place(place,tempR));
//		
//		places.addAll(tempP);
//		tempR.setPlaces(tempP);
//		regions.put(regionName,tempR);
//		
//		return tempP.stream().map(Place::getName).sorted().collect(toList());
//		List<Place> tempP=new ArrayList<>();
//		Region tempR=new Region(regionName);
//		Place p;
//		
//		for(String place:placeNames) {
//			p=new Place(place,tempR);
//			if(!places.contains(p))
//				tempP.add(p);
//		}
//		
//		places.addAll(tempP);
//		tempR.setPlaces(tempP);
//		regions.put(regionName,tempR);
//		
//		return tempP.stream().map(Place::getName).sorted().collect(toList());
	}
	
	public List<String> addCarrier(String carrierName, String... regionNames) { 
		Carrier tempC=new Carrier(carrierName);
		Set<String> tempR=new HashSet<>();
		Set<Region> r=new HashSet<>();
		Region reg;
		
		carriers.put(carrierName,tempC);
		for(String region:regionNames)
			if(regions.containsKey(region)) {
				reg=regions.get(region);
				tempR.add(region);
				r.add(reg);
				reg.addCarriers(tempC);
			}
		
		List<String> result=new ArrayList<String>(tempR).stream().sorted().collect(toList());
		
		tempC.setRegions(r);
		
		return result;
		
//		SortedSet<Region> tempR=new TreeSet<>();
//		Carrier tempC=new Carrier(carrierName);
//		Region r;
//		
//		for(String region:regionNames) {
//			if(regions.containsKey(region)) {
//				r=regions.get(region);
//				tempR.add(r);
//				r.addCarriers(tempC);
//			}
//		}
//		
//		tempC.setRegions(tempR);
//		
//		return tempR.stream().map(Region::getName).sorted().collect(toList());
	}
	
	public List<String> getCarriersForRegion(String regionName) { 
		return new ArrayList<String>(regions.get(regionName).getCarriers().keySet());
	}
	
//R2
	public void addRequest(String requestId, String placeName, String productId) 
			throws TMException {
		if(!places.contains(placeName)||requests.containsKey(requestId)){
			throw new TMException();
		}
		requests.put(requestId, new Request(requestId,placeName,productId,place_region.get(placeName).getName()));
	}
	
	public void addOffer(String offerId, String placeName, String productId) 
			throws TMException {
		if(!places.contains(placeName)||offers.containsKey(offerId)){
			throw new TMException();
		}
		offers.put(offerId, new Offer(offerId,placeName,productId));
	}
	

//R3
	public void addTransaction(String transactionId, String carrierName, String requestId, String offerId) 
			throws TMException {
		Request tempR=requests.get(requestId);
		Offer tempO=offers.get(offerId);
		
		if(tempR.getTransaction()!=null||tempO.getTransaction()!=null)
			throw new TMException();
		if(tempR.getProductId()!=tempO.getProductId())
			throw new TMException();
		Region partenza=place_region.get(tempR.getPlaceName());
		Region arrivo=place_region.get(tempO.getPlaceName());
		
		boolean flag= carriers.get(carrierName).getRegions().contains(partenza) ;
		if(!flag)
			throw new TMException();
		flag=carriers.get(carrierName).getRegions().contains(arrivo);
		if(!flag)
			throw new TMException();
			
		Transaction tempT=new Transaction(transactionId, carrierName, requestId, offerId);
		tempT.setRequest(requests.get(requestId));
		transactions.put(transactionId,tempT);
		
		tempR.setTransaction(tempT);
		tempO.setTransaction(tempT);
	}
	
	public boolean evaluateTransaction(String transactionId, int score) {
		transactions.get(transactionId).setScore(score);
		return (score>=1&&score<=10)?true:false;
	}
	
//R4
	public SortedMap<Long, List<String>> deliveryRegionsPerNT() {
//		List<Request> richieste=transactions.values().stream()
//				.map(Transaction::getRequest)
//				.collect(toList());
//		
//		Map<String,Long> nomeRegione_count=richieste.stream()
//				.collect(groupingBy(Request::getPlaceName,TreeMap::new,counting()));
		
		Map<String,Long> nomeRegione_count=transactions.values().stream()
				.map(Transaction::getRequest)
				.collect(groupingBy(Request::getDeliveryRegionName,TreeMap::new,counting()));
		
		SortedMap<Long,List<String>> finale=nomeRegione_count.entrySet().stream()
				.collect(groupingBy(Map.Entry::getValue , () -> new TreeMap <Long, List <String> > (reverseOrder()) , mapping(Map.Entry::getKey,toList())));
		
		return finale;
	}
	
	public SortedMap<String, Integer> scorePerCarrier(int minimumScore) {
		SortedMap<String,Integer> finale=transactions.values().stream()
				.filter(t->t.getScore()>=minimumScore)
				.collect(groupingBy(Transaction::getCarrierName, TreeMap::new , summingInt(Transaction::getScore)));
		return finale;
	}
	
	public SortedMap<String, Long> nTPerProduct() {
		SortedMap<String,Long> finale=transactions.values().stream()
				.map(Transaction::getRequest)
				.collect(groupingBy(Request::getProductId , TreeMap::new , counting()));
		
		return finale;
	}
	
	
}

