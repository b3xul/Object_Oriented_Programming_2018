package main;

import java.util.TreeMap;
import static java.lang.System.*;
import it.polito.oop.office.QueueListener;

/**
 * Public display mockup implementation for text console. 
 * 
 * This is a sample implementation of the {@link it.polito.oop.office.QueueListener}
 * interface that prints the notifications to the standard output.
 *  
 */
public class PublicDisplay implements QueueListener {
    
    private TreeMap<String,Long> queues = new TreeMap<>();
    private final static String PRE = "\t\t\t\t\t";

    @Override
    public void callTicket(int ticketNo, String counterId) {
        out.flush();
        err.println();
        err.println(PRE+"*-------------------------------*");
        err.format(PRE+"* Ticket %03d Go to Counter %-4s *\n",ticketNo,counterId);
        err.println(PRE+"*-------------------------------*");
        err.flush();
    }

    @Override
    public void queueUpdate(String ticketType, long queueLenght) {
        queues.put(ticketType, queueLenght);
        
        out.flush();
        err.println();
        err.println(PRE+"*-------------------------------*");
        err.println(PRE+"* Request type           Queue  *");
        queues.forEach((t,l) -> {
           err.format(PRE+"* %20s : %5d  *\n",t,l);
        });
        err.println(PRE+"*-------------------------------*");
        err.flush();

    }
}
