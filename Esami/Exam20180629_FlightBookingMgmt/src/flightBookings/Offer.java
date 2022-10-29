package flightBookings;

public class Offer {
	String offerId;
	String flightId;
	int nSeats;
	String fareTypeName;
	int price;
	
	public Offer(String offerId, String flightId, int nSeats, String fareTypeName, int price) {
		this.offerId=offerId;
		this.flightId=flightId;
		this.nSeats=nSeats;
		this.fareTypeName=fareTypeName;
		this.price=price;
	}

	public String getOfferId() {
		return offerId;
	}

	public void setOfferId(String offerId) {
		this.offerId = offerId;
	}

	public String getFlightId() {
		return flightId;
	}

	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}

	public int getnSeats() {
		return nSeats;
	}

	public void setnSeats(int nSeats) {
		this.nSeats = nSeats;
	}

	public String getFareTypeName() {
		return fareTypeName;
	}

	public void setFareTypeName(String fareTypeName) {
		this.fareTypeName = fareTypeName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
}
