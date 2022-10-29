import junit.framework.Test;
import junit.framework.TestSuite;



public class AllTests {

  public static void main(String[] args) {
    junit.textui.TestRunner.run(AllTests.class);
  }

  public static Test suite() {
    TestSuite suite = new TestSuite("Test for default package");
    //$JUnit-BEGIN$
    suite.addTest(new TestSuite(TestR1_Elettori.class));
    suite.addTest(new TestSuite(TestR2_Lista.class));
    suite.addTest(new TestSuite(TestR3_Votazione.class));
    suite.addTest(new TestSuite(TestR4_Risultati.class));
    suite.addTest(new TestSuite(TestR5_Gui.class));
    //$JUnit-END$
    return suite;
  }
}
