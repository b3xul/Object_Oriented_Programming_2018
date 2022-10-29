package ticketing;

/**
 * Class representing the ticket linked to an issue or malfunction.
 * 
 * The ticket is characterized by a severity and a state.
 */
public class Ticket {
    
	
	
    /**
     * Enumeration of possible severity levels for the tickets.
     * 
     * Note: the natural order corresponds to the order of declaration
     */
    public enum Severity { Blocking, Critical, Major, Minor, Cosmetic };
    
    /**
     * Enumeration of the possible valid states for a ticket
     */
    public static enum State { Open, Assigned, Closed }
    private int id;
    private String username;
    private String componentPath;
    private String description;
    private Severity severity;
    private String solutionDescription;
    private State state;
    private String assignedUsername;
    
    public Ticket(Integer id,String username, String componentPath, String description, Ticket.Severity severity) {
		this.id=id;
    	this.username=username;
		this.componentPath=componentPath;
		this.description=description;
		this.severity=severity;
		this.state=State.Open;
	}


    public int getId() {
		return id;
	}
    
	public String getAuthor() {
		return username;
	}

	public String getComponent() {
		return componentPath;
	}

	public String getDescription() {
		return description;
	}

	public Severity getSeverity() {
		return severity;
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	
	public String getAssignedUsername() {
		return assignedUsername;
	}
	public void setAssignedUsername(String assignedUsername) {
		this.assignedUsername = assignedUsername;
	}
    public String getSolutionDescription() throws TicketException {
    	if(this.state!=State.Closed)
    		throw new TicketException();
        return this.solutionDescription;
    }
	public void setSolutionDescription(String solutionDescription) {
		this.solutionDescription = solutionDescription;
	}
	
	
}
