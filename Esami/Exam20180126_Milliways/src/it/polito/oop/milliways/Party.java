package it.polito.oop.milliways;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Party {
	
	Map<Race,Integer> members;
	Hall allocated;
	
	public Map<Race, Integer> getMembers() {
		return members;
	}
	
	public Party() {
		members=new HashMap<>();
	}
	
	public void setAllocated(Hall allocated) {
		this.allocated = allocated;
	}
	
    public void addCompanions(Race race, int num) {
    	int oldN=(members.containsKey(race))? members.get(race) : 0;
    	 
    	members.put(race,oldN+num);
    }

	public int getNum() {
        return members.values().stream().mapToInt(i->i).sum();
        //return composition.values().stream().mapToInt(i->i).sum();

	}

	public int getNum(Race race) {
	    return members.get(race);
	}

	public List<String> getRequirements() {
        return 	members.entrySet().stream()
        		.flatMap(e->e.getKey().getRequirements().stream())
        		.distinct()
        		.sorted()
        		.collect(Collectors.toList());
	}

    public Map<String,Integer> getDescription(){
    	
        return 	members.entrySet().stream()
        		.collect(Collectors.toMap(e->e.getKey().getName(),e->e.getValue()));
        
//        return members.keySet().stream()
//        		.forEach(k->{
//        			
//        		});
    }

}
