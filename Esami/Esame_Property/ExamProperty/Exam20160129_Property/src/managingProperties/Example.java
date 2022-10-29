package managingProperties;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

import javax.swing.JOptionPane;

public class Example {
    static void print(Object s) {
        System.out.println(s);
    }

    public static void main(String[] args) throws Exception {
        PropertyManager pm = new PropertyManager();
        // Some of the errors to be checked are exemplified.
        // R1
        pm.addBuilding("b1", 20);
        pm.addBuilding("b3", 100);
        pm.addBuilding("b2", 20);
        try {
            pm.addBuilding("b1", 50);
        } catch (Exception e) {
            print("building duplicated");
        }

        pm.addOwner("ow1", "b1:1", "b2:5", "b3:5");
        pm.addOwner("ow2", "b3:1", "b2:10");
        pm.addOwner("ow3", "b1:2");
        pm.addOwner("ow8", "b1:18");
        try {
            pm.addOwner("ow3", "b1:2");
        } catch (Exception e) {
            print("the apartment already has the owner");
        }
        
        Map<Integer, List<String>> bMap = pm.getBuildings();
        print(bMap); // {20=[b1, b2], 100=[b3]}
        // R2
        pm.addProfessionals("electrician", "elt1", "elt2", "elt3");
        pm.addProfessionals("plumber", "pl1", "pl4");
        pm.addProfessionals("mason", "mas1", "mas2", "mas3");
        try {
            pm.addProfessionals("decorator", "mas1");
        } catch (Exception e) {
            print("professional duplicated");
        }
        Map<String, Integer> pMap = pm.getProfessions();
        print(pMap); // {electrician=3, mason=3, plumber=2}
        // R3
        int r1 = pm.addRequest("ow1", "b1:1", "electrician");
        int r2 = pm.addRequest("ow2", "b3:1", "electrician");
        int r3 = pm.addRequest("ow1", "b3:5", "plumber");
        pm.addRequest("ow2", "b2:10", "plumber");
        int r5 = pm.addRequest("ow1", "b2:5", "plumber");
        pm.assign(r1, "elt1");
        pm.assign(r2, "elt1");
        pm.assign(r3, "pl1");
        pm.assign(r5, "pl1");
        try {
            pm.assign(r2, "mas1");
        } catch (Exception e) {
            print("wrong professional");
        }
        List<Integer> arList = pm.getAssignedRequests();
        print(arList); // [1, 2, 3, 5]
        // R4
        pm.charge(r1, 500);
        pm.charge(r3, 400);
        pm.charge(r2, 200);
        try {
            pm.charge(6, 900);
        } catch (Exception e) {
            print("undefined request");
        }
        List<Integer> crList = pm.getCompletedRequests();
        print(crList); // [1, 2, 3]
        // R5
        Map<String, Integer> oMap = pm.getCharges();
        print(oMap); // {ow1=900, ow2=200}
        Map<String, Map<String, Integer>> bcMap = pm.getChargesOfBuildings();
        print(bcMap); // {b1={elt1=500}, b3={elt1=200, pl1=400}}

    }

    

    
    
    
    
    
    
    
    
    
    
    
    
    
    /**
     * This code serves the only purpose of checking whether
     * your entered the required personal information in file Student.txt
     */
    private static final String STUDENT_TXT = "Student.txt";
  static {
        Properties p = new Properties();
        try{
            p.load(new FileInputStream(STUDENT_TXT));
        }catch(IOException e){
            System.err.println("Generating file Student.txt");
            System.err.flush();
            p.setProperty("ID", "");
            p.setProperty("LastName", "");
            p.setProperty("FirstName", "");
            try {
                p.store(new FileOutputStream(STUDENT_TXT), "Enter your identification information (Note: ID is Matricola)");
            } catch (IOException e1) {
                JOptionPane.showMessageDialog(null, "The current project folder is not accessible, please notify your teacher!",
                        "Error",JOptionPane.ERROR_MESSAGE);
            }
        }
        if( p.getProperty("ID","").equals("")){
            System.err.println("Missing student information. Please fill in the Student.txt file");
            System.err.flush();
            JOptionPane.showMessageDialog(null, "Missing student information!\n"+
                                                "Please fill in the 'Student.txt' file before submitting the final version.\n\n"+
                                                "Click OK to run your program.",
                                            "Error",JOptionPane.ERROR_MESSAGE);
        }else{
            System.err.println("Project by " + p.getProperty("FirstName") + " " 
                                             + p.getProperty("LastName") 
                                             + " (" + p.getProperty("ID") + ")\n");
            System.err.flush();
        }
    }
}