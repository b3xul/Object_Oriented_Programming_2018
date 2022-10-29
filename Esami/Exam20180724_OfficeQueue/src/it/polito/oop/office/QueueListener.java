package it.polito.oop.office;

/**
 * Listener interface that must be implemented by
 * classes intended to show the status of the
 * waiting queue management system
 * <p>
 * A typical class implementing this interface
 * will show the notification on a display panel.
 *
 */
public interface QueueListener {
    /**
     * Method invoked when a new ticket is going
     * to be served by a given counter.
     * 
     * @param ticketNo the number of the ticket to be served
     * @param counterId the counter that will serve the ticket 
     */
    void callTicket(int ticketNo, String counterId);
    
    /**
     * Method called to provide updated information
     * about the waiting queue length for a given type of request.
     * 
     * @param ticketType   type of the ticket
     * @param queueLenght   length of the relative waiting queue
     */
    void queueUpdate(String ticketType, long queueLenght);
}
