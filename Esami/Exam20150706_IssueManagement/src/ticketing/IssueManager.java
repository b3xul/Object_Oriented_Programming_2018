package ticketing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import static java.util.stream.Collectors.*;
import static java.util.Comparator.*;

public class IssueManager {

	Map<String,User> users=new HashMap<>();
	Map<String,Component> components=new HashMap<>();
	Map<String,Component> components_path=new HashMap<>();
	Map<Integer,Ticket> tickets=new TreeMap<>();
    /**
     * Eumeration of valid user classes
     */
    public static enum UserClass {
        /** user able to report an issue and create a corresponding ticket **/
        Reporter, 
        /** user that can be assigned to handle a ticket **/
        Maintainer }
    
    /**
     * Creates a new user
     * 
     * @param username name of the user
     * @param classes user classes
     * @throws TicketException if the username has already been created or if no user class has been specified
     */
    public void createUser(String username) throws TicketException{
    	throw new TicketException();
    }
    public void createUser(String username, UserClass... classes) throws TicketException {
        if(classes==null)
        	throw new TicketException();
        if(users.containsKey(username))
        	throw new TicketException();
        
        Set<UserClass> uc=new HashSet<>();
        
        for(UserClass u:classes) {
        	uc.add(u);
        }
        users.put(username, new User(username,uc));
    }

    /**
     * Creates a new user
     * 
     * @param username name of the user
     * @param classes user classes
     * @throws TicketException if the username has already been created or if no user class has been specified
     */
    public void createUser(String username, Set<UserClass> classes) throws TicketException {
        if(classes.isEmpty())
        	throw new TicketException();
        if(users.containsKey(username))
        	throw new TicketException();

        users.put(username, new User(username,classes));
    }
   
    /**
     * Retrieves the user classes for a given user
     * 
     * @param username name of the user
     * @return the set of user classes the user belongs to
     */
    public Set<UserClass> getUserClasses(String username){
        return users.get(username).getUserClasses();
    }
    
    /**
     * Creates a new component
     * 
     * @param name unique name of the new component
     * @throws TicketException if a component with the same name already exists
     */
    public void defineComponent(String name) throws TicketException {
        if(components.containsKey(name))
        	throw new TicketException();
        
        Component tempC=new Component(name);
        components.put(name, tempC);
        components_path.put(tempC.getPath(),tempC);
    }
    
    /**
     * Creates a new sub-component as a child of an existing parent component
     * 
     * @param name unique name of the new component
     * @param parentPath path of the parent component
     * @throws TicketException if the the parent component does not exist or 
     *                          if a sub-component of the same parent exists with the same name
     */
    public void defineSubComponent(String name, String parentPath) throws TicketException {
        Component pc=components_path.get(parentPath);
    	
        if(pc==null)
    		throw new TicketException();
    	
		if(pc.getSubComponentsNames().contains(name))
    		throw new TicketException();
		
		Component tempC=new Component(name,pc);
		
		pc.addSubComponent(tempC);
        components.put(name, tempC);
        components_path.put(tempC.getPath(),tempC);
    }
    
    /**
     * Retrieves the sub-components of an existing component
     * 
     * @param path the path of the parent
     * @return set of children sub-components
     */
    public Set<String> getSubComponents(String path){
//    	Optional<Component> trovato=components.values().stream().filter(c->c.getPath()==path).findAny();
//    	
//        return trovato.get().getSubComponentsNames();
    	return components_path.get(path).getSubComponentsNames();
    }

    /**
     * Retrieves the parent component
     * 
     * @param path the path of the parent
     * @return name of the parent
     */
    public String getParentComponent(String path){
//    	Optional<Component> trovato=components.values().stream().filter(c->c.getPath()==path).findAny();
//    	
//        return trovato.get().getParentComponent().getPath();
    	
    	if(components_path.get(path).getParentComponent()==null)
    		return null;
        return components_path.get(path).getParentComponent().getPath();
    }

    /**
     * Opens a new ticket to report an issue/malfunction
     * 
     * @param username name of the reporting user
     * @param componentPath path of the component or sub-component
     * @param description description of the malfunction
     * @param severity severity level
     * 
     * @return unique id of the new ticket
     * 
     * @throws TicketException if the user name is not valid, the path does not correspond to a defined component, 
     *                          or the user does not belong to the Reporter {@link IssueManager.UserClass}.
     */
    public int openTicket(String username, String componentPath, String description, Ticket.Severity severity) throws TicketException {
        if(!users.containsKey(username))
        	throw new TicketException();
        if(!users.get(username).getUserClasses().contains(UserClass.Reporter))
        	throw new TicketException();
        if(!components_path.containsKey(componentPath))
        	throw new TicketException();
        
    	int id=tickets.size()+1;
    	tickets.put(id,new Ticket(id,username,componentPath,description,severity));
    	return id;
    }
    
    /**
     * Returns a ticket object given its id
     * 
     * @param ticketId id of the tickets
     * @return the corresponding ticket object
     */
    public Ticket getTicket(int ticketId){
    	if(!tickets.containsKey(ticketId))
    		return null;
        return tickets.get(ticketId);
    }
    
    /**
     * Returns all the existing tickets sorted by severity
     * 
     * @return list of ticket objects
     */
    public List<Ticket> getAllTickets(){
        return tickets.values().stream().sorted(comparing(Ticket::getSeverity)).collect(toList());
    }
    
    /**
     * Assign a maintainer to an open ticket
     * 
     * @param ticketId  id of the ticket
     * @param username  name of the maintainer
     * @throws TicketException if the ticket is in state <i>Closed</i>, the ticket id or the username
     *                          are not valid, or the user does not belong to the <i>Maintainer</i> user class
     */
    public void assingTicket(int ticketId, String username) throws TicketException {
    	if(!tickets.containsKey(ticketId))
    		throw new TicketException();
    	if(!users.containsKey(username))
        	throw new TicketException();
        if(!users.get(username).getUserClasses().contains(UserClass.Maintainer))
        	throw new TicketException();
        Ticket trovato=tickets.get(ticketId);
    	if(trovato.getState()!=Ticket.State.Open)
    		throw new TicketException();
    	
    	trovato.setAssignedUsername(username);
        trovato.setState(Ticket.State.Assigned);
    }

    /**
     * Closes a ticket
     * 
     * @param ticketId id of the ticket
     * @param description description of how the issue was handled and solved
     * @throws TicketException if the ticket is not in state <i>Assigned</i>
     */
    public void closeTicket(int ticketId, String description) throws TicketException {
    	Ticket trovato=tickets.get(ticketId);
    	if(trovato.getState()!=Ticket.State.Assigned)
    		throw new TicketException();
    	
    	trovato.setSolutionDescription(description);
        trovato.setState(Ticket.State.Closed);
        users.get(trovato.getAssignedUsername()).addSolved();
        
    }

    /**
     * returns a sorted map (keys sorted in natural order) with the number of  
     * tickets per Severity, considering only the tickets with the specific state.
     *  
     * @param state state of the tickets to be counted, all tickets are counted if <i>null</i>
     * @return a map with the severity and the corresponding count 
     */
    public SortedMap<Ticket.Severity,Long> countBySeverityOfState(Ticket.State state){
    	SortedMap<Ticket.Severity,Long> res;
    	if(state==null) {
    		res=tickets.values().stream()
    				.collect(groupingBy(Ticket::getSeverity, ()->new TreeMap<Ticket.Severity,Long>(), counting()));
    	}
    	else {
    		res=tickets.values().stream()
    				.filter(t->t.getState()==state)
    				.collect(groupingBy(Ticket::getSeverity, TreeMap::new, counting()));
    	}
        return res;
    }

    /**
     * Find the top maintainers in terms of closed tickets.
     * 
     * The elements are strings formatted as <code>"username:###"</code> where <code>username</code> 
     * is the user name and <code>###</code> is the number of closed tickets. 
     * The list is sorter by descending number of closed tickets and then by username.

     * @return A list of strings with the top maintainers.
     */
    public List<String> topMaintainers(){
        return users.values().stream()
        		.sorted(comparing(User::getSolved,reverseOrder()).thenComparing(User::getUsername))
        		.map(u->u.getUsername() + ":" + u.getSolved())
        		.collect(toList());
    }

}
