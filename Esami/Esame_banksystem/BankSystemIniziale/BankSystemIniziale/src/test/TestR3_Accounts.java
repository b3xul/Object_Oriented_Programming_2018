package test;

import static org.junit.Assert.*;

import java.util.List;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import BankServices.Account;
import BankServices.Bank;
import BankServices.Deposit;
import BankServices.InvalidCode;
import BankServices.InvalidValue;
import BankServices.Operation;
import BankServices.Withdrawal;

public class TestR3_Accounts extends TestCase {
	Bank b1;

	@Before
	public void setUp() throws Exception {
		b1 = new Bank("Uncle-$crooge");	
		assertNotNull(b1);
	}

	@Test
	public void testtoString() {
		int c1 = b1.createAccount("John", 5, 500.5);
		Account a1 = null;
		try{
			a1 = b1.getAccount(c1);
		}
		catch(InvalidCode ic){	
			fail("code 1 is valid");
		}
		String[] s1 = a1.toString().split("\\s*,\\s*");
		assertEquals(s1.length, 4);
		assertEquals(s1[0], "1");
		assertEquals(s1[1], "John");
		assertEquals(s1[2], "5");
		assertEquals(s1[3], "500.5");
	}
	
	public void testgetMovements1() {
		int c1 = b1.createAccount("John", 5, 500.5);
		Account a1 = null;
		try{
			a1 = b1.getAccount(c1);
		}
		catch(InvalidCode ic) {
			fail("code 1 is valid");
		}
		List<Operation> m = a1.getMovements();
		assertEquals(m.size(), 1);
		Operation o1 = m.get(0);
		String[] s1 = o1.toString().split("\\s*,\\s*");
		assertEquals(s1.length, 2);
		assertEquals(s1[0], "5");
		assertEquals(s1[1], "500.5+");
	}
	
	public void testgetMovements2() {
		int c1 = b1.createAccount("John", 5, 500.5);
		Account a1 = null;
		try{
			a1 = b1.getAccount(c1);
			b1.deposit(c1, 7, 360.);
		}
		catch(InvalidCode ic) {
			fail("code 1 is valid");
		}
		List<Operation> m = a1.getMovements();
		assertEquals(m.size(), 2);
		Operation o3 = m.get(0);
		String[] s3 = o3.toString().split("\\s*,\\s*");
		assertEquals(s3[0], "7");
		assertEquals(s3[1], "360.0+");
		Operation o4 = m.get(1);
		String[] s4 = o4.toString().split("\\s*,\\s*");
		assertEquals(s4[0], "5");
		assertEquals(s4[1], "500.5+");
	}
	
	public void testgetMovements3() {
		int c1 = b1.createAccount("John", 5, 500.5);
		Account a1 = null;
		try{
			a1 = b1.getAccount(c1);
			b1.withdraw(c1, 28, 350);
		}
		catch(InvalidCode ic) {
			fail("code 1 is valid");
		}
		catch(InvalidValue iv) {
			fail("value is valid");
		}
		List<Operation> m = a1.getMovements();
		assertEquals(m.size(), 2);
		Operation o2 = m.get(0);
		String[] s2 = o2.toString().split("\\s*,\\s*");
		assertEquals(s2[0], "28");
		assertEquals(s2[1], "350.0-");
		Operation o4 = m.get(1);
		String[] s4 = o4.toString().split("\\s*,\\s*");
		assertEquals(s4[0], "5");
		assertEquals(s4[1], "500.5+");
	}
	
	public void testgetMovements4() {
		int c1 = b1.createAccount("John", 5, 500.5);
		int c2 = b1.createAccount("Mary", 10,  1000.);
		Account a1 = null;
		try{
			a1 = b1.getAccount(c1);
			b1.transfer(c1, c2, 52, 400);
		}
		catch(InvalidCode ic) {
			fail("code 1 is valid");
		}
		catch(InvalidValue iv) {
			fail("value is valid");
		}
		List<Operation> m = a1.getMovements();
		assertEquals(m.size(), 2);
		Operation o1 = m.get(0);
		String[] s1 = o1.toString().split("\\s*,\\s*");
		assertEquals(s1.length, 2);
		assertEquals(s1[0], "52");
		assertEquals(s1[1], "400.0-");
		Operation o4 = m.get(1);
		String[] s4 = o4.toString().split("\\s*,\\s*");
		assertEquals(s4[0], "5");
		assertEquals(s4[1], "500.5+");
	}
	
	public void testgetMovements5() {
		int c1 = b1.createAccount("John", 5, 500.5);
		int c2 = b1.createAccount("Mary", 10,  1000.);
		Account a1 = null;
		try{
			a1 = b1.getAccount(c1);
			b1.deposit(c1, 7, 360.);
			b1.withdraw(c1, 28, 350);
			b1.transfer(c1, c2, 52, 400);
			b1.deleteAccount(c1, 93);
		}
		catch(InvalidCode ic) {
			fail("code 1 is valid");
		}
		catch(InvalidValue iv) {
			fail("value is valid");
		}
		List<Operation> m = a1.getMovements();
		assertEquals(m.size(), 5);
		Operation o0 = m.get(0);
		String[] s0 = o0.toString().split("\\s*,\\s*");
		assertEquals(s0.length, 2);
		assertEquals(s0[0], "93");
		assertEquals(s0[1], "110.5-");
		Operation o1 = m.get(1);
		String[] s1 = o1.toString().split("\\s*,\\s*");
		assertEquals(s1[0], "52");
		assertEquals(s1[1], "400.0-");
		Operation o2 = m.get(2);
		String[] s2 = o2.toString().split("\\s*,\\s*");
		assertEquals(s2[0], "28");
		assertEquals(s2[1], "350.0-");
		Operation o3 = m.get(3);
		String[] s3 = o3.toString().split("\\s*,\\s*");
		assertEquals(s3[0], "7");
		assertEquals(s3[1], "360.0+");
		Operation o4 = m.get(4);
		String[] s4 = o4.toString().split("\\s*,\\s*");
		assertEquals(s4[0], "5");
		assertEquals(s4[1], "500.5+");
	}
	
	public void testgetDeposits() {
		int c1 = b1.createAccount("John", 5, 500.5);
		Account a1 = null;
		try{
			a1 = b1.getAccount(c1);
			b1.withdraw(c1, 28, 350);					
			b1.deposit(c1, 7, 360.);	
		}
		catch(InvalidCode ic) {
			fail("code 1 is valid");
		}
		catch(InvalidValue iv) {
			fail("value is valid");
		}
		List<Deposit> d = a1.getDeposits();
		assertEquals(d.size(), 2);
		Deposit d1 = d.get(0);
		String[] s1 = d1.toString().split("\\s*,\\s*");
		assertEquals(s1.length, 2);
		assertEquals(s1[0], "5");
		assertEquals(s1[1], "500.5+");
		Deposit d2 = d.get(1);
		String[] s2 = d2.toString().split("\\s*,\\s*");
		assertEquals(s2.length, 2);
		assertEquals(s2[0], "28");
		assertEquals(s2[1], "360.0+");
	}
	
	public void testgetWithdrawals() {
		int c1 = b1.createAccount("John", 5, 500.5);
		Account a1 = null;
		try{
			a1 = b1.getAccount(c1);
			b1.deposit(c1, 28, 360.);
			b1.withdraw(c1, 9, 350);
			b1.deleteAccount(c1, 78);
		}
		catch(InvalidCode ic) {
			fail("code 1 is valid");
		}
		catch(InvalidValue iv) {
			fail("value is valid");
		}
		List<Withdrawal> w = a1.getWithdrawals();
		assertEquals(w.size(), 2);
		Withdrawal w1 = w.get(0);
		String[] s1 = w1.toString().split("\\s*,\\s*");
		assertEquals(s1.length, 2);
		assertEquals(s1[0], "78");
		assertEquals(s1[1], "510.5-");
		Withdrawal w2 = w.get(1);
		String[] s2 = w2.toString().split("\\s*,\\s*");
		assertEquals(s2.length, 2);
		assertEquals(s2[0], "28");
		assertEquals(s2[1], "350.0-");
	}
}
