package delivery;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import delivery.Delivery.OrderStatus;


public class Order {
	private Customer customer;
	private int codO;
	private HashMap<Menu, Integer> items=new HashMap<>();
	private OrderStatus status;
	
	public Order(Customer customer, int codO) {
		super();
		this.customer = customer;
		this.codO = codO;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public int getCodO() {
		return codO;
	}
	public void setCodO(int codO) {
		this.codO = codO;
	}
	public int addItem(Menu menu, int qty) {
		int quantita=0;
	
		if(items.containsKey(menu)){
			quantita=qty+items.get(menu);
			items.replace(menu, quantita);		
	}
		
		items.put(menu, qty);
		
		return items.get(menu);
	
	}
	
	public List<String> getItems(){
		
		return items.entrySet().stream()
		       .map(e-> e.getKey().getDescription() + ", " + e.getValue())
		       .collect(Collectors.toList());
		
	}
	
	public double getPrezzo(){
		return items.entrySet().stream()
		      .map(e-> e.getKey().getPrice() * e.getValue())
		      .reduce((a,b)->a=a+b).get();
	}
	public void setStatus(OrderStatus new1) {
		status=new1;
		
	}
	public OrderStatus getStatus() {
		return status;
	}
	public int getMaxTime() {
		return items.keySet().stream()
				.map(m->m.getPrepTime())
				.max((a,b)->a.compareTo(b)).get();
	}
	
	

}
