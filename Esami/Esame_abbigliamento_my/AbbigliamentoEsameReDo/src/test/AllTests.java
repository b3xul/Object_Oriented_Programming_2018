package test;
import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for test");
		//$JUnit-BEGIN$
		suite.addTestSuite(TestR1_Modelli.class);
		suite.addTestSuite(TestR2_MatCol.class);
		suite.addTestSuite(TestR3_Capi.class);
		suite.addTestSuite(TestR4_Collezioni.class);
		suite.addTestSuite(TestR5_File.class);
		//$JUnit-END$
		return suite;
	}
}