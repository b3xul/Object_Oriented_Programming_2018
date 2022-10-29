package it.polito.oop.office;

import java.util.ArrayList;
import java.util.List;

public class Request {
	private String label;
	private int estimatedTime;
	private List<String> servedBy=new ArrayList<>();
	
	public Request(String label, int estimatedTime) {
		this.label=label;
		this.estimatedTime=estimatedTime;
	}
	
	public String getLabel() {
		return label;
	}
	
	public void addServedBy(String counter) {
		servedBy.add(counter);
	}
	public List<String> getServedBy() {
		return servedBy;
	}
	public int getEstimatedTime() {
		return estimatedTime;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setEstimatedTime(int estimatedTime) {
		this.estimatedTime = estimatedTime;
	}

	public void setServedBy(List<String> servedBy) {
		this.servedBy = servedBy;
	}
}
