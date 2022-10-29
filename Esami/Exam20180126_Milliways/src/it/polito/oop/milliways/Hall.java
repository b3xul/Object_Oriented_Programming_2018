package it.polito.oop.milliways;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


public class Hall {

	private int id;
	Set<String> facilities;
	
	public Hall(int id) {
		this.id=id;
		facilities=new TreeSet<>();
	}
	
	public int getId() {
		return this.id;
	}

	public void addFacility(String facility) throws MilliwaysException {
		if(!this.facilities.add(facility))
			throw new MilliwaysException();
	}

	public List<String> getFacilities() {
        return new ArrayList<String>(facilities);
	}
	
	int getNumFacilities(){
        return facilities.size();
	}

	public boolean isSuitable(Party party) {
		boolean flag=true;
		
		Iterator<String> iterator=party.getRequirements().iterator();
		String temp;
		
		while(iterator.hasNext()) {
			temp=iterator.next();
			if(!facilities.contains(temp)) {
				flag=false;
				break;
			}
		}
		
		return flag;
	}

}
