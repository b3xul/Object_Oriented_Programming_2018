package delivery;

import java.util.LinkedList;
import java.util.List;

import delivery.Delivery.OrderStatus;

public class Customer {
	private String name;
	private String address;
	private String phone;
	private String email;
	private int numProgr;
	private List<Order> orders=new LinkedList<>();

	public Customer(String name, String address, String phone, String email,
			int numProgr) {
		this.name=name;
		this.address=address;
		this.phone=phone;
		this.email=email;
		this.numProgr=numProgr;


	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getNumProgr() {
		return numProgr;
	}

	public void setNumProgr(int numProgr) {
		this.numProgr = numProgr;
	}

	public void addOrder(Order o) {
		orders.add(o);
		
	}
	
	public List<Order> getOrders(){
		return orders;
	}

	public double totalCustomer() {
		return  getOrders().stream()
        		.filter(o->o.getStatus().equals(OrderStatus.CONFIRMED))
        		.map(o->o.getPrezzo())
        		.reduce((a,b)-> a=a+b).get();
	}
	
	

}
