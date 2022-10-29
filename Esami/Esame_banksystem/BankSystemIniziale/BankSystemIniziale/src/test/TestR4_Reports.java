package test;

import static org.junit.Assert.*;

import java.util.List;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import BankServices.Account;
import BankServices.Bank;
import BankServices.InvalidCode;
import BankServices.InvalidValue;
import BankServices.Operation;

public class TestR4_Reports extends TestCase {
	Bank b1;

	@Before
	public void setUp() throws Exception {
		b1 = new Bank("Uncle-$crooge");	
		assertNotNull(b1);
		int c1 = b1.createAccount("John", 5, 530.5);
		int c2 = b1.createAccount("Mary", 10,  1000.);
		int c3 = b1.createAccount("John", 20,  800.);
		int c4 = b1.createAccount("Paul", 30, 259.0);
	
		try {
			b1.deposit(c1, 7, 360.);
			b1.deposit(c4, 35, 270.);
			b1.withdraw(c3, 28, 350);
			b1.withdraw(c2, 19, 350);
			b1.withdraw(c3, 41, 158);
			b1.transfer(c1, c3, 8, 400);
			b1.deleteAccount(c3,50);
		}
		catch(InvalidCode ic) {
			ic.printStackTrace();
		}
		catch(InvalidValue iv) {
			iv.printStackTrace();
		}
	}

	@Test
	public void test_getTotalDeposit() {
		assertEquals(1669.5, b1.getTotalDeposit());
	}
	
	public void test_getAccounts() {
		List<Account> a = b1.getAccounts();
		assertEquals(3, a.size());
		Account a1 = a.get(0);
		String[] s1 = a1.toString().split("\\s*,\\s*");
		assertEquals("John", s1[1]);
		Account a2 = a.get(1);
		String[] s2 = a2.toString().split("\\s*,\\s*");
		assertEquals("Mary", s2[1]);
		Account a3 = a.get(2);
		String[] s3 = a3.toString().split("\\s*,\\s*");
		assertEquals("Paul", s3[1]);
	}
	
	public void test_getAccountsByBalance() {
		List<Account> a = b1.getAccountsByBalance(510.0, 680.0);
		assertEquals(2, a.size());
		Account a1 = a.get(0);
		String[] s1 = a1.toString().split("\\s*,\\s*");
		assertEquals("650.0", s1[3]);
		Account a2 = a.get(1);
		String[] s2 = a2.toString().split("\\s*,\\s*");
		assertEquals("529.0", s2[3]);
	}
	
	public void test_getPerCentHigher() {
		assertEquals(33, b1.getPerCentHigher(530.0));
	}
}
