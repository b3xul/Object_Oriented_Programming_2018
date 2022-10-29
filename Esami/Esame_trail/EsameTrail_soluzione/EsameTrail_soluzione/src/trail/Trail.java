package trail;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static java.util.stream.Collectors.*;

import  java.util.stream.Collectors;

import static java.util.Comparator.*;

public class Trail {
	private int pettorale = 0;
	private Map<Integer,Runner> runners = new TreeMap<>();
	private LinkedHashMap<String,Location> locations = new LinkedHashMap<>();
	private Map<String,Delegate> delegates = new HashMap<>();

    public int newRunner(String name, String surname){
    		Runner r = new Runner(++pettorale,name,surname);
    		runners.put(pettorale, r);
        return pettorale;
    }
    
    public Runner getRunner(int bibNumber){
        return runners.get(bibNumber);
    }
    
    public Collection<Runner> getRunner(String surname){
    	    return runners.values().stream()
    	    		.filter( r -> r.getSurname().equals(surname))
    	    		.collect(toList())   // con import static .... Collectors.*;
//    	    		.collect(Collectors.toList())  // con import ... Collectors;
    	    		;
        //return null;
    }
    
    public List<Runner> getRunners(){
        //return new ArrayList<>(runners.values());
    		return runners.values().stream().collect(Collectors.toList());
    }

    public List<Runner> getRunnersByName(){
//    	    ArrayList<Runner> l = new ArrayList<>(runners.values());
//    	    Collections.sort(l,comparing(Runner::getSurname)
//    	    							.thenComparing(Runner::getName));
//        return l;
        // OPPURE
        return runners.values().stream()
        		.sorted(comparing(Runner::getSurname)
						.thenComparing(Runner::getName))
			.collect(Collectors.toList())
			;
        
    }
    
    public void addLocation(String location){
        Location l = new Location(location,locations.size());
        locations.put(location,l);
    }
//    public void addLocation(String name, double lat, double lon, double elevation){
//        
//    }

    public Location getLocation(String location){
        return locations.get(location);
    }

    public List<Location> getPath(){
        return new ArrayList<>(locations.values());
    }
    
    public void newDelegate(String name, String surname, String id){
        Delegate d = new Delegate(name,surname,id);
        delegates.put(id,d);
    }

    public List<String> getDelegates(){
        return delegates.values().stream()
        		//.map( d -> d.getSurname() + ", " + d.getName() + ", " + d.getId())
			.sorted(comparing(Delegate::getSurname)
						.thenComparing(Delegate::getName))
        		.map(Delegate::toString)
        		.collect(Collectors.toList())
        		;
    }
    

    public void assignDelegate(String location, String delegate) throws TrailException {
       Location l = locations.get(location);
       Delegate d = delegates.get(delegate);
       if(l==null || d==null){
    	   	throw new TrailException();
       }
       l.addDelegate(d);
    }
 
    public List<String> getDelegates(String location){
        Location l = locations.get(location);
        return l.getDelegates();
    }
    
    public long recordPassage(String delegate, String location, int bibNumber) throws TrailException {
        Location l = locations.get(location);
        Delegate d = delegates.get(delegate);
        Runner r = runners.get(bibNumber);
        if(l==null || d==null || r==null || !l.hasDelegate(d)){
        		throw new TrailException();
        }

        Passage p = new Passage(l,r);
        return p.timestamp;
    }
    
    public long getPassTime(String position, int bibNumber) throws TrailException {
        Location l = locations.get(position);
        Runner r = runners.get(bibNumber);
        if(l==null || r==null) throw new TrailException();
        return l.getPassTime(r);
    }
    
    public List<Runner> getRanking(String location){
    		return
    			locations.get(location)
	    	    .passages.stream()
	    	    .map(Passage::getRunner)  // they are already recorded in order of passage
	    	    .collect(toList());
    }

    public List<Runner> getRanking(){
    		return
    	    runners.values().stream()
    	    .sorted(comparing(Runner::lastNum,reverseOrder())
    	    			.thenComparing(Runner::lastTime)
    	    		)
    	    .collect(toList());
    }
}
