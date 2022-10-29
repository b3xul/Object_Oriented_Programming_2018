package warehouse;

public class Order {
	private String code;
	private Product product;
	private String productCode;
	private int quantity;
	private Supplier supplier;
	private String supplierName;
	private boolean statusDelivery=false;
	
	public Order(String code,Product prod, int quantity, Supplier supp) {
		this.code=code;
		this.product=prod;
		this.productCode=prod.getCode();
		this.quantity=quantity;
		this.supplier=supp;
		this.supplierName=supp.getNome();
		product.addOrder(this);
	}
	
	public String getCode(){
		return this.code;
	}
	public String getProductCode() {
		return productCode;
	}
	
	public String getSupplierName() {
		return supplierName;
	}
	
	public int getQuantity() {
		return quantity;
	}
	public boolean delivered(){
		return this.statusDelivery;
	}

	public void setDelivered() throws MultipleDelivery {
		if(this.statusDelivery==true)
			throw new MultipleDelivery();

		this.statusDelivery=true;		
		product.increaseQuantityOf(quantity);
	}
	
	public String toString(){
		return "Order " + code + " for " + quantity + " of " + productCode + " : " + product.getDescription() + " from " + supplierName;
	}
}
