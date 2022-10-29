package flightBookings;

public class Offer {

	private String offerId;
	private String flightId;
	private int seatsAvailable;
	private String fareTypeName;
	private int price;
	private String destination;
	
	public Offer(String offerId, String flightId, int nSeats, String fareTypeName, int price,String destination) {
		this.offerId=offerId;
		this.flightId=flightId;
		this.seatsAvailable=nSeats;
		this.fareTypeName=fareTypeName;
		this.price=price;
		this.destination=destination;
	}
	
	public String getDestination() {
		return destination;
	}
	public String getFareTypeName() {
		return fareTypeName;
	}
	public int getSeatsAvailable() {
		return seatsAvailable;
	}
	public void decreaseAvailableSeats(int requiredSeats) {
		this.seatsAvailable-=requiredSeats;
	}
	public int getPrice() {
		return price;
	}
}
