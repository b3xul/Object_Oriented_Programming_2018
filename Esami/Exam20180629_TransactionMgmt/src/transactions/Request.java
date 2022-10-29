package transactions;

public class Request {

	private String requestId;
	private String placeName;
	private String deliveryRegionName;
	private String productId;
	private Transaction transaction=null;
	
	public Request(String requestId, String placeName, String productId,String deliveryRegionName)  {
		this.requestId=requestId;
		this.placeName=placeName;
		this.productId=productId;
		this.deliveryRegionName=deliveryRegionName;
	}
	public String getPlaceName() {
		return placeName;
	}
	public String getProductId() {
		return productId;
	}
	public String getDeliveryRegionName() {
		return deliveryRegionName;
	}
	public Transaction getTransaction() {
		return transaction;
	}
	
	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
}
