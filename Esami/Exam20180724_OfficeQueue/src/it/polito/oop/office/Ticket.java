package it.polito.oop.office;

public class Ticket {
	private long id;
	private String requestType;
	private int requestTime;
	private boolean open=true;
	String counterId;
	public Ticket(Long id,String requestType,Integer requestTime) {
		this.id=id;
		this.requestType=requestType;
		this.requestTime=requestTime;
	}
	public long getId() {
		return id;
	}
	public String getRequestType() {
		return requestType;
	}
	public boolean getOpen() {
		return this.open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	public int getRequestTime() {
		return requestTime;
	}
	public void setRequestTime(int requestTime) {
		this.requestTime = requestTime;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	
}
