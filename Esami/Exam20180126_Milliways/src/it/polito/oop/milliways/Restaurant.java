package it.polito.oop.milliways;

import java.util.ArrayList;
import java.util.Comparator;
import static java.util.Comparator.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.*;


public class Restaurant {

	Map<String,Race> races;
	Map<String,Party> parties;
	List<Hall> halls;
	Map<Race,Integer> composition;
	
    public Restaurant() {
    	this.races=new HashMap<>();
    	this.parties=new HashMap<>();
    	this.halls=new ArrayList<>();
    	this.composition=new HashMap<>();
	}
	
	public Race defineRace(String name) throws MilliwaysException{
		
		Race tempR=new Race(name);
		
	    if(races.put(name,tempR)!=null)
	    	throw new MilliwaysException();
	    
		return tempR;
	}
	
	public Party createParty() {
	    return new Party();
	}
	
	public Hall defineHall(int id) throws MilliwaysException{
		
		Hall tempH=new Hall(id);
		
	    if(halls.stream().anyMatch(h->h.getId()==id))
	    	throw new MilliwaysException();
	    
	    halls.add(tempH);
	    
		return tempH;
	}

	public List<Hall> getHallList() {
		return halls;
	}

	public Hall seat(Party party, Hall hall) throws MilliwaysException {
		if(!hall.isSuitable(party))
			throw new MilliwaysException();
		
		party.setAllocated(hall);
		
		int oldN;
		Iterator<Race> iterator=party.getMembers().keySet().iterator();
		Race temp;
		
		while(iterator.hasNext()) {
			temp=iterator.next();
			oldN=(composition.containsKey(temp))? composition.get(temp) : 0;
			composition.put(temp,oldN+party.getNum(temp));
		}
		
        return hall;
	}

	public Hall seat(Party party) throws MilliwaysException {
		for(Hall hall:halls)
			if(hall.isSuitable(party)) {
				party.setAllocated(hall);
				int oldN;
				Iterator<Race> iterator=party.getMembers().keySet().iterator();
				Race temp;
				
				while(iterator.hasNext()) {
					temp=iterator.next();
					oldN=(composition.containsKey(temp))? composition.get(temp) : 0;
					composition.put(temp,oldN+party.getNum(temp));
				}
		        return hall;
		        }
		
		throw new MilliwaysException();
	}

	public Map<Race, Integer> statComposition() {
        return composition;
	}

	public List<String> statFacility() {
		Comparator<Map.Entry<String,Long>> c=Comparator.comparing(Map.Entry::getValue,Comparator.reverseOrder());
		c=c.thenComparing(Map.Entry::getKey);
		
        Map<String,Long> temp=halls.stream().flatMap(h->h.getFacilities().stream())
        		.collect(Collectors.groupingBy(s->s, Collectors.counting()));
        
        return temp.entrySet().stream()
        		.sorted(c)
        		.map(Map.Entry::getKey)
        		.collect(Collectors.toList());
        
	}
	
	public Map<Integer,List<Integer>> statHalls() {
		
		return halls.stream()
				.sorted(comparing(Hall::getId))		//ID ORDINATI
				.collect(groupingBy(Hall::getNumFacilities, TreeMap::new, mapping(Hall::getId,toList())));	//Numero Facilities ordinato
		
//		troppo complesso per il compilatore mi sa
//		Map<Integer,Integer> idHall_numFacilities=halls.stream()		//N.B. ID ORDINATI,count
//				.collect(Collectors.toMap(Hall::getId, Hall::getNumFacilities,(id1,id2)->id1,TreeMap::new)); 
//		
//		
//        return idHall_numFacilities.entrySet().stream()		//inverto
//        		.collect(Collectors.groupingBy(Map.Entry<Integer,Integer>::getValue,TreeMap::new,mapping((Map.Entry<Integer,Integer>::getKey),Collectors.toList())));
	}

}
