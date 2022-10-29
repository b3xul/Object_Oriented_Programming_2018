package it.polito.oop.office;

import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;


/**
 * Manages a waiting queue for an office.
 * 
 *
 */
public class OfficeQueueManager {
	
	Map<String,Request> requests=new HashMap<>();
	Map<String,Counter> counters=new HashMap<>();
	List<QueueListener> listeners = new ArrayList<>();
//	Map<Counter,ArrayList<String>> counter_requests=new HashMap<Counter,ArrayList<String>>();
//	Map<Request,ArrayList<String>> request_counters=new HashMap<Request,ArrayList<String>>();
	
	List<Ticket> tickets=new ArrayList<>();
	Map<Request,Long> requests_people=new HashMap<>();
	Map<String,Long> requestsId_people=new HashMap<>();
	Map<Request,List<Ticket>> request_tickets=new HashMap<>();
	
    /**
     * Define a new type of request.
     * A type is characterized by a label and an
     * estimated time to serve this type of request.
     * 
     * @param label : the label for the request type
     * @param estimatedTime : the estimated time
     */
    public void addRequestType(String label, int estimatedTime) {
        requests.put(label,new Request(label,estimatedTime));
        
    }
    
    /**
     * Returns a list of all defined request types
     * @return
     */
    public Set<String> getRequestTypes(){
        return requests.keySet();
    }
    
    /**
     * Adds a new counter that will be managed by this class.
     * The counter is described by a unique id and
     * a set of request type that can be served.
     * 
     * @param id : a unique id for the counter
     * @param canServeTypes : set of request types
     */
    public void addCounter(String id, String... canServeTypes) {
    	List<String> canServeRequests=new ArrayList<>();
    	
    	for(String r:canServeTypes) {
    		canServeRequests.add(r);
    		requests.values().stream().filter(req->req.getLabel()==r).forEach(req->req.addServedBy(id));
    	}
    	
        counters.put(id, new Counter(id,canServeRequests));
       
    }

    /**
     * Lists all the counters defined.
     * 
     * @return : list of counter ids
     */
    public Collection<String> getCounters(){
        return counters.keySet();
    }
    
    /**
     * Lists all the counters that can serve the 
     * given request type
     * 
     * @param requestType : the type of request to be served
     * @return : list of counter ids
     */
    public Collection<String> getCounters(String requestType){
        return counters.values().stream()
        		.filter(c->c.getCanServeRequests().contains(requestType))
        		.map(Counter::getId)
        		.collect(toSet());
    }

    /**
     * Creates a new ticket for a specific request type.
     * <p>
     * The method returns a new ticket number that must be
     * unique. The first ticket issued must have number 1
     * and the ensuing must be incremented by 1.
     * <p>
     * The method should trigger calling the method
     * {@link QueueListener#updateList} of all listener
     * to notify the change in the queue length.
     * 
     * @param requestType : type of request, must be one of the predefined request types
     * @return : the numeric id of the ticket
     * 
     * @throws QueueException : if the request type is not defined
     */
    public int openTicket(String requestType) {
    	Long id=(long) tickets.size()+1;
    	int requestTime=requests.get(requestType).getEstimatedTime();
    	
    	Ticket dummy=new Ticket(id,requestType,requestTime);
    	tickets.add(dummy);
    	
    	Long personePrima=(long)0;
    	Request richiesta=requests.get(requestType);
    	
    	if(requests_people.containsKey(richiesta))
    		personePrima=requests_people.get(richiesta);
    	
    	requests_people.put(richiesta, (personePrima+1));
    	requestsId_people.put(requestType, (personePrima+1));
    	for (QueueListener q : listeners)q.queueUpdate(requestType, (personePrima + 1));
    	
    	/*List<Ticket> ticketPrima=new ArrayList<>();
    	ticketPrima=request_tickets.get(richiesta);
    	ticketPrima.add(dummy);
    	
    	request_tickets.put(richiesta, ticketPrima);*/
        return (int)(long)id;
    }
    
    /**
     * Returns the length of the pending tickets queue for each
     * type of request.
     * <p>
     * In case of a request type with no pending ticket
     * the length of the queue must be reported as 0.
     * <p>
     * A ticket is considered pending if it has been opened
     * (with method {@link #openTicket}) but not yet
     * called to any counter.
     * 
     * @return the map of length vs. request type
     */
    public Map<String,Long> queueLengths(){
    	return requestsId_people;
    }
    
    
    /**
     * Signal that the counter is ready to serve next ticket.
     * <p>
     * Based on the pending tickets selects the ticket with 
     * the type corresponding to the longest queue.
     * 
     * If there is no ticket of any of the types served by the counter
     * the method returns 0.
     * <p>
     * The method also marks the previous ticket (if any) as served.
     * <p>
     * This method should trigger the calls, for all listeners,
     * of the methods 
     * <ul>
     *      <li>{@link QueueListener#callTicket} to notify the new ticket to be served
     *      <li>{@link QueueListener#queueUpdate} to update the queue length for the type of ticket to be served
     * </ul>
     *  
     * @param counterId : the counter about to serve the next ticket
     * @return the ticket number to be served or {@code 0} if no ticket available
     */
    public int serveNext(String counterId)  {
    	
    	//int idTicket=requestsId_people
        List<String> reqServeable=counters.get(counterId).getCanServeRequests();
        Long maxQueue=(long) 0;
        int minTime=Integer.MAX_VALUE;
        String toServe="";
        
        for(String s:reqServeable) {
        	if(requestsId_people.containsKey(s) && requestsId_people.get(s)>=maxQueue&&requests.get(s).getEstimatedTime()<minTime) {
        		toServe=s;
        		maxQueue=requestsId_people.get(s);
        		minTime=requests.get(s).getEstimatedTime();
        	}
        }
        Long personePrima=requestsId_people.get(toServe);
        Long finale=(long)0;
        
        if(maxQueue==0)
        	return 0;
        
        /*
         * serveNext giusto
         * */
        for(Ticket t:tickets) {
        	if((t.getOpen()==true)&&(t.getRequestType()==toServe)) {
        		t.counterId = counterId;
        		finale=t.getId();
        		t.setOpen(false);
        		requestsId_people.put(toServe, personePrima-1);
        		break;
        	}
        		
        }
		for (QueueListener q : listeners) {
			q.queueUpdate(toServe, (personePrima - 1));
			q.callTicket((int) (long) finale, counterId);
		}
    	return (int)(long)finale;
    }
    
    /**
     * Adds a new listener for the announcements
     * issues by this queue manager.
     * 
     * @param listener : the new listener
     */
    public void addQueueListener(QueueListener listener) {
    	listeners.add(listener);
    }
    
    /**
     * Estimates the waiting time remaining for a given ticket number.
     * 
     * @param ticket : the ticket number
     * @return the estimated time
     */
    public double estimatedWaitingTime(int ticket){
    	int timeR = tickets.get(ticket - 1).getRequestTime();
		int sportelli = getCounters(tickets.get(ticket - 1).getRequestType()).size();
		int count = 0;
		for (Ticket t : tickets) {if (t.getId() == tickets.get(ticket - 1).getId())break;
			if ((t.getOpen() == true) && (t.getRequestType() == tickets.get(ticket - 1).getRequestType()))count++;}
		return (double) timeR * count / sportelli + 0.5 * timeR;
    }
    
    /**
     * Returns the number of tickets served by type.
     * <p>
     * A ticket is considered as served after it has
     * been called to a counter by method {@link #serveNext(String)}
     * 
     * @return the map of count of served tickets vs. the ticket type 
     */
    public Map<String,Long> servedByType(){
    	return tickets.stream().filter(t -> t.getOpen() == false)
				.collect(groupingBy(Ticket::getRequestType, counting()));
    }

    /**
     * Returns the number of tickets served by counter.
     * The count of each counter is reported as a map
     * reporting the number of tickets by type.
     * <p>
     * A ticket is considered as served after it has
     * been called to a counter by method {@link #serveNext(String)}
     * 
     * @return the map of count of served tickets vs. the ticket type vs. the counter.
     */
    public Map<String,Map<String,Long>> servedByCounter(){
		return tickets.stream().filter(t -> t.getOpen() == false)
				.collect(groupingBy(t -> t.counterId, groupingBy(Ticket::getRequestType, counting())));
    }
}
