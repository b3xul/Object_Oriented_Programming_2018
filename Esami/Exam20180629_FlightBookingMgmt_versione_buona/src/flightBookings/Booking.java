package flightBookings;

public class Booking {
	
	private String bookingId;
	private String requestId;
	private String offerId;
	private Request request;
	private Offer offer;
	private String fareTypeName;
	private int totalPrice;
	
	public Booking(String bookingId, String requestId, String offerId,Request request,Offer offer,String fareTypeName,int totalPrice) {
		this.bookingId=bookingId;
		this.requestId=requestId;
		this.offerId=offerId;
		this.request=request;
		this.offer=offer;
		this.fareTypeName=fareTypeName;
		this.totalPrice=totalPrice;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	
	public String getFareTypeName() {
		return fareTypeName;
	}
}
