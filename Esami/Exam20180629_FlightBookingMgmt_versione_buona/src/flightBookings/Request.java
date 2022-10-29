package flightBookings;

public class Request {

	private String requestId;
	//private String flightId;
	private String destination;
	private String fareTypeName;
	private int maxPrice;
	private int nSeats;
	
	public Request(String requestId, String destName, String fareTypeName, int maxPrice, int nSeats) {
		this.requestId=requestId;
		this.destination=destName;
		this.fareTypeName=fareTypeName;
		this.maxPrice=maxPrice;
		this.nSeats=nSeats;
	}
	
	public String getDestination() {
		return destination;
	}
	public String getFareTypeName() {
		return fareTypeName;
	}
	public int getnSeats() {
		return nSeats;
	}
	public int getMaxPrice() {
		return maxPrice;
	}
}
