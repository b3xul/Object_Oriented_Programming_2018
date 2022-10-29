package it.polito.po.test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

    public static Test suite() {
        TestSuite suite = new TestSuite(AllTests.class.getName());
        //$JUnit-BEGIN$
        suite.addTestSuite(TestR1_Libri.class);
        suite.addTestSuite(TestR2_Utenti.class);
        suite.addTestSuite(TestR3_Prestiti.class);
        suite.addTestSuite(TestR4_Restituzioni.class);
        suite.addTestSuite(TestR5_Elenchi.class);
        //$JUnit-END$
        return suite;
    }
}
