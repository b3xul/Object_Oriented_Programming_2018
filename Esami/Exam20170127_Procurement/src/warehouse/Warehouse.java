package warehouse;

import java.util.Collection;
import static java.util.Comparator.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static java.util.stream.Collectors.*;

public class Warehouse {
	
	private Map<String,Product> products=new HashMap<>();
	private Map<String,Supplier> suppliers=new HashMap<>();
	private Map<String,Order> orders=new HashMap<>();

	
	public Product newProduct(String code, String description){
		Product dummy=new Product(code,description);
		products.put(code, dummy);
		
		return dummy;
	}
	
	public Collection<Product> products(){
		return products.values();
	}

	public Product findProduct(String code){
		return products.get(code);
	}

	public Supplier newSupplier(String code, String name){
		Supplier dummy=new Supplier(code,name);
		suppliers.put(code, dummy);
		
		return dummy;
	}
	
	public Supplier findSupplier(String code){
		return suppliers.get(code);
	}

	public Order issueOrder(Product prod, int quantity, Supplier supp)
		throws InvalidSupplier {
		if(!prod.suppliers().contains(supp))
			throw new InvalidSupplier();
		
		int somma=orders.size()+1;
		
		String code="ORD" + somma;
		
		Order dummy=new Order(code,prod,quantity,supp);
		orders.put(code, dummy);
		
		return dummy;
	}

	public Order findOrder(String code){
		return orders.get(code);
	}
	
	public List<Order> pendingOrders(){
		return orders.values().stream()
				.filter(o->o.delivered()==false)
				.sorted(comparing(Order::getProductCode))
				.collect(toList());
	}
	
	public List<Order> completedOrders(){
		return orders.values().stream()
				.filter(o->o.delivered()==true)
				.sorted(comparing(Order::getProductCode))
				.collect(toList());
	}
	
	public Map<String,List<Order>> ordersByProduct(){
	    return orders.values().stream()
	    		.collect(groupingBy(Order::getProductCode));
	}
	
	public Map<String,Long> orderNBySupplier(){
		List<Order> completati=completedOrders();
		
	    return completati.stream()
	    		.map(Order::getSupplierName)
	    		.sorted()
	    		.collect(groupingBy(n->n,counting()));
	}
	
	public List<String> countDeliveredByProduct(){
		List<Order> completati=completedOrders();
		
	    Map<String,Long> code_count=completati.stream()
//	    		.map(Order::getProductCode)	//lista di ProductCode
//	    		.collect(groupingBy(s->s,counting()));
	    		.collect(groupingBy(Order::getProductCode,counting()));
	    
	    return code_count.entrySet().stream()
	    		.sorted(comparing(Map.Entry<String,Long>::getValue,reverseOrder()))
	    		.map(e->e.getKey() + " - " + e.getValue())
	    		.collect(toList());
	}
}
