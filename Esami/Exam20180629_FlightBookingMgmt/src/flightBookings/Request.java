package flightBookings;

public class Request {
	String requestId;
	String destName;
	String fareTypeName;
	int maxPrice;
	int nSeats;
	
	public Request(String requestId, String destName, String fareTypeName, int maxPrice, int nSeats) {
		this.requestId=requestId;
		this.destName=destName;
		this.fareTypeName=fareTypeName;
		this.maxPrice=maxPrice;
		this.nSeats=nSeats;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getDestName() {
		return destName;
	}

	public void setDestName(String destName) {
		this.destName = destName;
	}

	public String getFareTypeName() {
		return fareTypeName;
	}

	public void setFareTypeName(String fareTypeName) {
		this.fareTypeName = fareTypeName;
	}

	public int getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(int maxPrice) {
		this.maxPrice = maxPrice;
	}

	public int getnSeats() {
		return nSeats;
	}

	public void setnSeats(int nSeats) {
		this.nSeats = nSeats;
	}
	
}