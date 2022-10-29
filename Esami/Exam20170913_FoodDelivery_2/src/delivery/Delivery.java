package delivery;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Delivery {
	private HashMap<Integer, Customer> customers= new HashMap<>();
	private HashMap<Integer, Order> orders=new HashMap<>();
	private List<Customer> customersL=new LinkedList<>();
	private List<Menu> menus=new LinkedList<>();
	private int numProgr=0;
	private int codO=0;
    public static enum OrderStatus { NEW, CONFIRMED, PREPARATION, ON_DELIVERY, DELIVERED } 
    
    
    /**
     * Creates a new customer entry and returns the corresponding unique ID.
     * 
     * The ID for the first customer must be 1.
     * 
     * 
     * @param name name of the customer
     * @param address customer address
     * @param phone customer phone number
     * @param email customer email
     * @return unique customer ID (positive integer)
     */
    public int newCustomer(String name, String address, String phone, String email) throws DeliveryException {
    	for(Customer c:customersL){
    		if(c.getEmail().equals(email))
    			throw new DeliveryException();
    	}
    	
    	numProgr++;
    	Customer c=new Customer(name, address, phone, email, numProgr);
    	customers.put(numProgr, c);
    	customersL.add(c);
        return numProgr;
    }
    
    /**
     * Returns a string description of the customer in the form:
     * <pre>
     * "NAME, ADDRESS, PHONE, EMAIL"
     * </pre>
     * 
     * @param customerId
     * @return customer description string
     */
    public String customerInfo(int customerId){
       Customer c=customers.get(customerId);
       
       return c.getName() + ", " + c.getAddress() + ", " + c.getPhone() + ", " + c.getEmail();
    }
    
    /**
     * Returns the list of customers, sorted by name
     * 
     */
    public List<String> listCustomers(){
    	List<String> string=new LinkedList<>();
    	List<Customer> customerSorted= customersL.stream()
    	                               .sorted(Comparator.comparing(Customer :: getName))
    	                               .collect(Collectors.toList());
    	for(Customer c: customerSorted){
    		string.add(customerInfo(c.getNumProgr()));
    		}
    	
        return string;
    }
    
    /**
     * Add a new item to the delivery service menu
     * 
     * @param description description of the item (e.g. "Pizza Margherita", "Bunet")
     * @param price price of the item (e.g. 5 EUR)
     * @param category category of the item (e.g. "Main dish", "Dessert")
     * @param prepTime estimate preparation time in minutes
     */
    public void newMenuItem(String description, double price, String category, int prepTime){
    	Menu m=new Menu(description, price, category, prepTime);
        menus.add(m);
    }
    
    /**
     * Search for items matching the search string.
     * The items are sorted by category first and then by description.
     * 
     * The format of the items is:
     * <pre>
     * "[CATEGORY] DESCRIPTION : PRICE"
     * </pre>
     * 
     * @param search search string
     * @return list of matching items
     */
    public List<String> findItem(String search){
     return menus.stream()
    		 .filter(m->m.getDescription().toLowerCase().contains(search.toLowerCase()))
    		 .sorted(Comparator.comparing(Menu :: getCategory).thenComparing(Menu :: getDescription))
    		 .map(m->m.toString())
    		 .collect(Collectors.toList());
    }
    
    /**
     * Creates a new order for the given customer.
     * Returns the unique id of the order, a progressive
     * integer number starting at 1.
     * 
     * @param customerId
     * @return order id
     */
    public int newOrder(int customerId){
    	Customer c=customers.get(customerId);
    	codO++;
    	Order o=new Order(c, codO);
    	o.setStatus(OrderStatus.NEW);
    	orders.put(codO, o);
    	c.addOrder(o);
        return codO;
    }
    
    /**
     * Add a new item to the order with the given quantity.
     * 
     * If the same item is already present in the order is adds the
     * given quantity to the current quantity.
     * 
     * The method returns the final total quantity of the item in the order. 
     * 
     * The item is found through the search string as in {@link #findItem}.
     * If none or more than one item is matched, then an exception is thrown.
     * 
     * @param orderId the id of the order
     * @param search the item search string
     * @param qty the quantity of the item to be added
     * @return the final quantity of the item in the order
     * @throws DeliveryException in case of non unique match or invalid order ID
     */
    
    public List<Menu> findItemMenu(String search){
    	return menus.stream()
       		 .filter(m->m.getDescription().toLowerCase().contains(search.toLowerCase()))
       		 .collect(Collectors.toList());
    }
   
    public int addItem(int orderId, String search, int qty) throws DeliveryException {
    	int quantita=0;
    	Order o=orders.get(orderId);
    	List<Menu> items=findItemMenu(search);
    	if(items.size()!=1 || o==null)
    		throw new DeliveryException();
    	
    	quantita=o.addItem(items.get(0), qty);
        return quantita;
    }
    
    /**
     * Show the items of the order using the following format
     * <pre>
     * "DESCRIPTION, QUANTITY"
     * </pre>
     * 
     * @param orderId the order ID
     * @return the list of items in the order
     * @throws DeliveryException when the order ID in invalid
     */
    public List<String> showOrder(int orderId) throws DeliveryException {
    	Order o=orders.get(orderId);
    	if(o==null)
    		throw new DeliveryException();
    	
        return o.getItems();
    }
    
    /**
     * Retrieves the total amount of the order.
     * 
     * @param orderId the order ID
     * @return the total amount of the order
     * @throws DeliveryException when the order ID in invalid
     */
    public double totalOrder(int orderId) throws DeliveryException {
    	Order o=orders.get(orderId);
    	if(o==null)
    		throw new DeliveryException();
    	
        return o.getPrezzo();
    }
    
    /**
     * Retrieves the status of an order
     * 
     * @param orderId the order ID
     * @return the current status of the order
     * @throws DeliveryException when the id is invalid
     */
    public OrderStatus getStatus(int orderId) throws DeliveryException {
    	Order o=orders.get(orderId);
    	if(o==null)
    		throw new DeliveryException();
    	
        return o.getStatus();
    }
    
    /**
     * Confirm the order. The status goes from {@code NEW} to {@code CONFIRMED}
     * 
     * Returns the delivery time estimate computed as the sum of:
     * <ul>
     * <li>start-up delay (conventionally 5 min)
     * <li>preparation time (max of all item preparation time)
     * <li>transportation time (conventionally 15 min)
     * </ul>
     * 
     * @param orderId the order id
     * @return delivery time estimate in minutes
     * @throws DeliveryException when order ID invalid to status not {@code NEW}
     */
    public int confirm(int orderId) throws DeliveryException {
    	int tempo=0;
    	Order o=orders.get(orderId);
    	if(o==null || o.getStatus()!=OrderStatus.NEW)
    		throw new DeliveryException();
    	
    	o.setStatus(OrderStatus.CONFIRMED);
    	tempo=5+ o.getMaxTime() + 15;
    	
        return tempo;
    }

    /**
     * Start the order preparation. The status goes from {@code CONFIRMED} to {@code PREPARATION}
     * 
     * Returns the delivery time estimate computed as the sum of:
     * <ul>
     * <li>preparation time (max of all item preparation time)
     * <li>transportation time (conventionally 15 min)
     * </ul>
     * 
     * @param orderId the order id
     * @return delivery time estimate in minutes
     * @throws DeliveryException when order ID invalid to status not {@code CONFIRMED}
     */
    public int start(int orderId) throws DeliveryException {
    	Order o=orders.get(orderId);
    	if(o==null || o.getStatus()!=OrderStatus.CONFIRMED)
    		throw new DeliveryException();
    	
    	
    	o.setStatus(OrderStatus.PREPARATION);
        return  o.getMaxTime() + 15;
    }

    /**
     * Begins the order delivery. The status goes from {@code PREPARATION} to {@code ON_DELIVERY}
     * 
     * Returns the delivery time estimate computed as the sum of:
     * <ul>
     * <li>transportation time (conventionally 15 min)
     * </ul>
     * 
     * @param orderId the order id
     * @return delivery time estimate in minutes
     * @throws DeliveryException when order ID invalid to status not {@code PREPARATION}
     */
    public int deliver(int orderId) throws DeliveryException {
    	Order o=orders.get(orderId);
    	if(o==null || o.getStatus()!=OrderStatus.PREPARATION)
    		throw new DeliveryException();
    	
    	o.setStatus(OrderStatus.ON_DELIVERY);
        
    	return 15;
    }
    
    /**
     * Complete the delivery. The status goes from {@code ON_DELIVERY} to {@code DELIVERED}
     * 
     * 
     * @param orderId the order id
     * @return delivery time estimate in minutes
     * @throws DeliveryException when order ID invalid to status not {@code ON_DELIVERY}
     */
    public void complete(int orderId) throws DeliveryException {
    	Order o=orders.get(orderId);
    	if(o==null || o.getStatus()!=OrderStatus.ON_DELIVERY)
    		throw new DeliveryException();
    	
    	o.setStatus(OrderStatus.DELIVERED);
    }
    
    /**
     * Retrieves the total amount for all orders of a customer.
     * 
     * @param customerId the customer ID
     * @return total amount
     */
    public double totalCustomer(int customerId){
    	Customer c=customers.get(customerId);
        return c.totalCustomer();
    }
    
    /**
     * Computes the best customers by total amount of orders.
     *  
     * @return the classification
     */
    public SortedMap<Double,List<String>> bestCustomers(){	
  	
 	Map<Double,List<String>> customerM= 
	                                  customers.values().stream()
		                             .collect(Collectors.groupingBy(
	 				                  Customer::totalCustomer,
 				                     Collectors.mapping(
					                Customer::toString,
 						            Collectors.toList())));

    			TreeMap<Double, List<String>> tm = new TreeMap<Double,List<String>>();
    		 	tm.putAll(customerM);

    			return tm.descendingMap();

     

    }
    
// NOT REQUIRED
//
//    /**
//     * Computes the best items by total amount of orders.
//     *  
//     * @return the classification
//     */
//    public List<String> bestItems(){
//        return null;
//    }
//    
//    /**
//     * Computes the most popular items by total quantity ordered.
//     *  
//     * @return the classification
//     */
//    public List<String> popularItems(){
//        return null;
//    }

}
