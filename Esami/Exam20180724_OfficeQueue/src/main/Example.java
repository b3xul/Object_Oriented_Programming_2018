package main;

import it.polito.oop.office.OfficeQueueManager;
import it.polito.oop.office.QueueException;

public class Example {

    public static void main(String[] args) throws QueueException {
        
        OfficeQueueManager postOffice = new OfficeQueueManager();
        postOffice.addQueueListener(new PublicDisplay());

        postOffice.addRequestType("Packages", 10);
        postOffice.addRequestType("Savings", 8);
        postOffice.addRequestType("Other", 6);
        
        System.out.println("Request types: " + postOffice.getRequestTypes());
        
        postOffice.addCounter("P1", "Packages"); 
        postOffice.addCounter("P2", "Packages");

        postOffice.addCounter("S1", "Savings","Other");
        postOffice.addCounter("S2", "Savings","Other");
        postOffice.addCounter("S3", "Savings","Other");
        
        System.out.println("Counters: " + postOffice.getCounters()); // [P1, P2, S1, S2, S3]
        System.out.println("Packages counters: " + postOffice.getCounters("Packages")); // [P1, P2]
        
        for(int i=0;i<4;++i) 
            System.out.println("Issued ticket " + postOffice.openTicket("Packages")); // tickets 1 to 4
        for(int i=0;i<4;++i)  
            System.out.println("Issued ticket " + postOffice.openTicket("Savings")); // tickets 5 to 8 
        for(int i=0;i<2;++i)  
            System.out.println("Issued ticket " + postOffice.openTicket("Other"));    // tickets 9 to 10
        
        System.out.println("Queues: " + postOffice.queueLengths() );
                
        System.out.println("Serving: " + postOffice.serveNext("P1")); // 1
        
        System.out.println("Serving: " + postOffice.serveNext("S1")); // 5
        System.out.println("Serving: " + postOffice.serveNext("S2")); // 6
        System.out.println("Serving: " + postOffice.serveNext("S2")); // 9

//        double estimate = postOffice.estimatedWaitingTime(3);
//        System.out.println("Estimated waiting time for ticket 3: " + estimate); // 10.0
//
//        System.out.println("Serving: " + postOffice.serveNext("P2")); // 2
//        System.out.println("Serving: " + postOffice.serveNext("P1")); // 3
//        System.out.println("Serving: " + postOffice.serveNext("P2")); // 4
//        
//        int next = postOffice.serveNext("P1");
//        if(next==0) System.out.println("No queue for P1 (Packages) requests!");
//        
//        System.out.println("Served: " +  postOffice.servedByType() ); // {Savings=2, Packages=4, Other=1}
    }    
}
