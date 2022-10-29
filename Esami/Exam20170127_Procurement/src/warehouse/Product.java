package warehouse;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Product {
	
	private String code;
	private String description;
	private int quantity;
	private Map<String,Supplier> suppliers=new HashMap<>();
	private List<Order> orders=new ArrayList<>();
	
	public Product(String code, String description) {
		this.code=code;
		this.description=description;
	}
	public String getCode(){
		return this.code;
	}

	public String getDescription(){
		return this.description;
	}
	
	public void setQuantity(int quantity){
		this.quantity=quantity;
	}

	public void decreaseQuantity(){
		this.quantity--;
	}

	public void decreaseQuantityOf(int quantity) {
		this.quantity-=quantity;
	}
	
	public void increaseQuantityOf(int quantity) {
		this.quantity+=quantity;
	}
	
	public int getQuantity(){
		return this.quantity;
	}

	public void addSupplier(Supplier supplier) {
		suppliers.put(supplier.getCodice(), supplier);
	}
	
	public List<Supplier> suppliers(){
		return suppliers.values().stream()
				.sorted(Comparator.comparing(Supplier::getNome))
				.collect(Collectors.toList());
	}

	public void addOrder(Order order) {
		orders.add(order);
	}
	public List<Order> pendingOrders(){
		return orders.stream()
				.filter(o->o.delivered()==false)
				.sorted(Comparator.comparing(Order::getQuantity,Comparator.reverseOrder()))
				.collect(Collectors.toList());
	}
}
