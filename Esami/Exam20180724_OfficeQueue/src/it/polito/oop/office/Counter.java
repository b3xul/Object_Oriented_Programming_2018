package it.polito.oop.office;

import java.util.ArrayList;
import java.util.List;

public class Counter {
	
	private String id;
	private List<String> canServeRequests=new ArrayList<>();
	
	public Counter(String id,List<String> canServeRequests) {
		this.id=id;
		this.canServeRequests=canServeRequests;
	}
	public String getId() {
		return id;
	}
	public List<String> getCanServeRequests() {
		return canServeRequests;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setCanServeRequests(List<String> canServeRequests) {
		this.canServeRequests = canServeRequests;
	}
}
