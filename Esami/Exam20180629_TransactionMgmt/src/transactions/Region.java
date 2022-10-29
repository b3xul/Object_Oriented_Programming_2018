package transactions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class Region {
	private String name;
	private List<String> places;
	private Map<String,Carrier> carriers;
	
	public Region(String name) {
		this.name=name;
		places=new ArrayList<>();
		carriers=new HashMap<>();
	}
	
	public String getName() {
		return name;
	}
	public Map<String, Carrier> getCarriers() {
		return carriers;
	}
	
	public void setPlaces(List<String> places) {
		this.places = places;
	}
	
	public void addCarriers(Carrier carrier) {
		carriers.put(carrier.getName(), carrier);
	}
}
