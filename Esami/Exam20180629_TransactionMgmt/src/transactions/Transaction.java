package transactions;

public class Transaction {
	private String transactionId;
	private String carrierName;
	private String requestId;
	private Request request;
	private String offerId;
	private int score;
	
	public Transaction(String transactionId, String carrierName, String requestId, String offerId) {
		this.transactionId=transactionId;
		this.carrierName=carrierName;
		this.requestId=requestId;
		this.offerId=offerId;
	}
	public Request getRequest() {
		return request;
	}
	public void setRequest(Request request) {
		this.request = request;
	}
	public String getCarrierName() {
		return carrierName;
	}
	public String getOfferId() {
		return offerId;
	}
	public String getRequestId() {
		return requestId;
	}
	public int getScore() {
		return score;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
}
