package transactions;

import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class Carrier {

	private String name;
	private Set<Region> regions;
	
	public Carrier(String name) {
		this.name=name;
		regions=new HashSet<>();
	}

	public String getName() {
		return name;
	}
	public Set<Region> getRegions() {
		return regions;
	}
	
	public void setRegions(Set<Region> regions) {
		this.regions = regions;
	}
}
