
import java.util.HashSet;
import java.util.List;

import ticketing.IssueManager;
import ticketing.Ticket;
import ticketing.TicketException;
import ticketing.IssueManager.UserClass;

import static ticketing.IssueManager.*;

public class Example {

    public static void main(String[] args) throws TicketException {
//        
//        IssueManager ts = new IssueManager();
//
//        HashSet<UserClass> both = new HashSet<>();
//        both.add(UserClass.Reporter);
//        both.add(UserClass.Maintainer);
//        ts.createUser("alpha", UserClass.Reporter);
//        ts.createUser("beta", UserClass.Reporter);
//        ts.createUser("gamma", both);
//        ts.createUser("delta", both);
//        ts.createUser("epsilon", UserClass.Maintainer);
//        
//        System.out.println(ts.getUserClasses("gamma"));
//        
//        ts.defineComponent("System");
//        ts.defineSubComponent("SubA", "/System");
//        ts.defineSubComponent("SubB", "/System");
//        ts.defineSubComponent("SubC", "/System");
//        ts.defineSubComponent("SubB.1", "/System/SubB");
//        ts.defineSubComponent("SubB.2", "/System/SubB");
//        
//        System.out.println("System has " + ts.getSubComponents("/System").size() + " children");
//        System.out.println("SubB.2 has parent " + ts.getParentComponent("/System/SubB/SubB.2"));
//        
//        
//        ts.openTicket("alpha", "/System/SubA", "Initial menu does not show 'open' item", Ticket.Severity.Major);
//        ts.openTicket("alpha", "/System/SubA", "Cannot save form XYZ", Ticket.Severity.Major);
//        ts.openTicket("alpha", "/System/SubB", "The colors in the diagram are hard to tell apart", Ticket.Severity.Minor);
//        int id = ts.openTicket("alpha", "/System", "The system is not responding today", Ticket.Severity.Blocking);
//        Ticket t = ts.getTicket(id);
//        
//        System.out.println("User " + t.getAuthor() + " created ticket " + t.getId() + " on component " + t.getComponent());
//        
//        ts.assingTicket(id, "delta");
//        ts.closeTicket(id, "The user had the network cable unplugged...");
//        
//        System.out.println("The ticket status is " + t.getState() + " solution: " + t.getDescription());
//        
//        System.out.println("Count open tickets:\n"  +ts.countBySeverityOfState(Ticket.State.Open));
//        System.out.println("Count all tickets:\n"  +ts.countBySeverityOfState(null));
//        System.out.println("Most active maintainers:\n"  +ts.topMaintainers());
//        
         IssueManager tm;
       
            tm = new IssueManager();
            tm.createUser("alpha", UserClass.Reporter);
            tm.createUser("beta", UserClass.Reporter);
            tm.createUser("gamma", UserClass.Reporter, UserClass.Maintainer);
            tm.createUser("delta", UserClass.Reporter, UserClass.Maintainer);
            tm.createUser("epsilon", UserClass.Maintainer);

            tm.defineComponent("Car");
            tm.defineSubComponent("Engine", "/Car");
            tm.defineSubComponent("Wheels", "/Car");
            tm.defineSubComponent("Chassis", "/Car");

            tm.openTicket("alpha", "/Car/Wheels", "Got a flat tire", Ticket.Severity.Blocking);
            tm.openTicket("alpha", "/Car", "The color is not uniform", Ticket.Severity.Cosmetic);
            tm.openTicket("alpha", "/Car/Engine", "Noisy when decelerating", Ticket.Severity.Minor);
            tm.openTicket("alpha", "/Car/Engine", "Cut power at 2000 rpm", Ticket.Severity.Critical);
            tm.openTicket("alpha", "/Car/Chassis", "Found some rust spots", Ticket.Severity.Major);
            tm.openTicket("alpha", "/Car/Engine", "Engine turn off every 5 minutes", Ticket.Severity.Critical);
            tm.openTicket("alpha", "/Car", "Windshield is cracked", Ticket.Severity.Major);
            
            tm.assingTicket(1, "gamma");
            tm.closeTicket(1, "Replaced flat tire");
            tm.assingTicket(5, "gamma");
            tm.closeTicket(5, "Painted over rust");
            tm.assingTicket(4, "gamma");

            tm.assingTicket(2, "epsilon");
            tm.closeTicket(2, "Washed car...");

            tm.assingTicket(3, "delta");
            tm.closeTicket(3, "Replaced silencer");
            
            
            List<String> top = tm.topMaintainers();
            
            
            System.out.println(top);
            System.out.println("Top maintainer list must have 3 or five elements, it has " + top.size());
            		    //top.size()==3 || top.size()==5);
            
            String[] first = top.get(0).split(":");
            System.out.println(first[0].trim());	//"gamma"
            System.out.println(first[1].trim());	//"2", 
            
            String[] second = top.get(1).split(":");
            System.out.println(second[0].trim());	//"delta"
            System.out.println(second[1].trim());	//"1"
        
    }

}
