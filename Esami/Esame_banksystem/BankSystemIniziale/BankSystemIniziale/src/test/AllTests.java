package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestR1_BankServices.class, TestR2_Operations.class,
		TestR3_Accounts.class, TestR4_Reports.class })
public class AllTests {

}
