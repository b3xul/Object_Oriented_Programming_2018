package test;
import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import BankServices.Account;
import BankServices.Bank;
import BankServices.InvalidCode;
import BankServices.InvalidValue;


public class TestR2_Operations extends TestCase {
	Bank b1;

	@Before
	public void setUp() throws Exception {
		b1 = new Bank("Uncle-$crooge");	
		assertNotNull(b1);
	}

	@Test
	public void test_initialValue() {
		int c1 = b1.createAccount("John", 5, 500.5);
		Account a1 = null;
		try{
			a1 = b1.getAccount(c1);
		}
		catch(InvalidCode ic) {
			fail("code 1 is valid");
		}
		String[] s = a1.toString().split("\\s*,\\s*");
		assertEquals("500.5", s[3]);
	}
	
	public void test_depositedValue() {
		int c1 = b1.createAccount("John", 5, 500.5);
		Account a1 = null;
		try{
			b1.deposit(c1, 7, 360.);
			a1 = b1.getAccount(c1);
		}
		catch(InvalidCode ic) {
			fail("code 1 is valid");
		}
		String[] s = a1.toString().split("\\s*,\\s*");
		assertEquals("860.5", s[3]);
	}
	
	public void test_withdrawedValue() {
		int c1 = b1.createAccount("John", 5, 500.5);
		Account a1 = null;
		try{
			b1.withdraw(c1, 7, 360.);
			a1 = b1.getAccount(c1);
		}
		catch(InvalidCode ic) {
			fail("code 1 is valid");
		}
		catch(InvalidValue iv) {
			fail("value 360. is valid");
		}
		String[] s = a1.toString().split("\\s*,\\s*");
		assertEquals("140.5", s[3]);
	}
	
	public void test_transferredValue() {
		int c1 = b1.createAccount("John", 5, 500.5);
		int c2 = b1.createAccount("John", 20,  800.);
		Account a1 = null, a2 = null;
		try{
			b1.transfer(c1, c2, 8, 400);
			a1 = b1.getAccount(c1);
			a2 = b1.getAccount(c2);
		}
		catch(InvalidCode ic) {
			fail("codes 1 and 2 are valid");
		}
		catch(InvalidValue iv) {
			fail("value 400. is valid");
		}
		String[] s1 = a1.toString().split("\\s*,\\s*");
		assertEquals("100.5", s1[3]);
		String[] s2 = a2.toString().split("\\s*,\\s*");
		assertEquals("1200.0", s2[3]);
	}
	
	public void test_deletedAcount() {
		int c1 = b1.createAccount("John", 5, 500.5);
		Account a1 = null;
		try{
			a1 = b1.deleteAccount(c1,50);
		}
		catch(InvalidCode ic) {
			fail("code 1 is valid");
		}
		String[] s1 = a1.toString().split("\\s*,\\s*");
		assertEquals("0.0", s1[3]);
	}
}
