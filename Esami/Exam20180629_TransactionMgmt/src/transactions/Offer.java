package transactions;

public class Offer {

	private String offerId;
	private String placeName;
	private String productId;
	private Transaction transaction=null;
	
	public Offer(String requestId, String placeName, String productId)  {
		this.offerId=requestId;
		this.placeName=placeName;
		this.productId=productId;
	}
	public String getPlaceName() {
		return placeName;
	}
	public String getProductId() {
		return productId;
	}
	public Transaction getTransaction() {
		return transaction;
	}
	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
}