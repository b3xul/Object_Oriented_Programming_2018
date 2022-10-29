package it.polito.oop.milliways;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Race {
    
	private String name;
	private Set<String> requirements;
	
	public Race(String name) {
		this.name=name;
		this.requirements=new TreeSet<>();
	}
	
	public void addRequirement(String requirement) throws MilliwaysException {
		if(!this.requirements.add(requirement))
			throw new MilliwaysException();
		
	}
	
	public List<String> getRequirements() {
        return new ArrayList<String>(requirements);
	}
	
	public String getName() {
        return this.name;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
}
