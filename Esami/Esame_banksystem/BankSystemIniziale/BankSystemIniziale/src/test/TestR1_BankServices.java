package test;
import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import BankServices.Account;
import BankServices.Bank;
import BankServices.InvalidCode;
import BankServices.InvalidValue;


public class TestR1_BankServices extends TestCase {
	Bank b1;

	@Before
	public void setUp() throws Exception {
		b1 = new Bank("Uncle-$crooge");	
		assertNotNull(b1);
	}

	@Test
	
	public void test_getName() {
		String n = b1.getName();
		assertNotNull(n);
		assertEquals(n,"Uncle-$crooge");
	}
	
	public void test_createAccount() {
		int c1 = b1.createAccount("John", 5, 500.5);
		int c2 = b1.createAccount("Mary", 10,  1000.);
		int c3 = b1.createAccount("John", 20,  800.);
		int c4 = b1.createAccount("Paul", 30, 252.3);
		assertEquals(c1,1);
		assertEquals(c2,2);
		assertEquals(c3,3);
		assertEquals(c4,4);
	}
	
	public void test_getAccount() {
		int c1 = b1.createAccount("John", 5, 500.5);
		try{
			assertNotNull(b1.getAccount(c1));
		}
		catch(InvalidCode ic) {
			fail("code 1 is valid");
		}
		try{
			b1.getAccount(2);
			fail("code 2 is not valid");
		}
		catch(InvalidCode ic) {
		}
	}
	
	public void test_deposit() {
		int c1 = b1.createAccount("John", 5, 500.5);
		assertEquals(c1,1);
		try{
			b1.deposit(c1, 7, 360.);
		}
		catch(InvalidCode ic) {
			fail("code 1 is valid");
		}
		try{
			b1.deposit(2, 7, 360.);
			fail("code 2 is not valid");
		}
		catch(InvalidCode ic) {
		}
	}
		
	public void test_withdraw() {
		int c1 = b1.createAccount("John", 5, 500.5);
		assertEquals(c1,1);
		try{
			b1.withdraw(c1, 7, 360.);
		}
		catch(InvalidCode ic) {
			fail("code 1 is valid");
		}
		catch(InvalidValue iv) {
			fail("value 360. is valid");
		}
		try{
			b1.withdraw(2, 7, 360.);
			fail("code 2 is not valid");
		}
		catch(InvalidCode ic) {
		}
		catch(InvalidValue iv) {
		}
		try{
			b1.withdraw(c1, 7, 700.);
			fail("value 700. is not valid");
		}
		catch(InvalidCode ic) {
			fail("code 1 is valid");
		}
		catch(InvalidValue iv) {
		}
	}
		
	public void test_transfer() {
		int c1 = b1.createAccount("John", 5, 500.5);
		assertEquals(c1,1);
		int c2 = b1.createAccount("John", 20,  800.);
		assertEquals(c2,2);
		try{
			b1.transfer(c1, c2, 8, 400);
		}
		catch(InvalidCode ic) {
			fail("codes 1 and 2 are valid");
		}
		catch(InvalidValue iv) {
			fail("value 400. is valid");
		}
		try{
			b1.transfer(c1, 3, 8, 400);
			fail("code 2 is not valid");
		}
		catch(InvalidCode ic) {
		}
		catch(InvalidValue iv) {
		}
		try{
			b1.transfer(3, c1, 8, 400);
			fail("code 2 is not valid");
		}
		catch(InvalidCode ic) {
		}
		catch(InvalidValue iv) {
		}
		try{
			b1.transfer(c1, c2, 8, 600);
			fail("value 600. is not valid");
		}
		catch(InvalidCode ic) {
			fail("codes 1 and 2 are valid");
		}
		catch(InvalidValue iv) {
		}
	}	
		
	public void test_delete() {
		int c1 = b1.createAccount("John", 5, 500.5);
		try{
			Account a1 = b1.deleteAccount(c1,50);
			assertNotNull(a1);
		}
		catch(InvalidCode ic) {
			fail("code 1 is valid");
		}
		try{
			b1.deleteAccount(1,50);
			fail("code 1 is not valid");
		}
		catch(InvalidCode ic) {
		}
		try{
			b1.deleteAccount(c1,50);
			b1.deleteAccount(c1,50);
			fail("account 1 already deleted");
		}
		catch(InvalidCode ic) {
		}
	}
}
