package BankServices;

import java.util.List;

public class Bank {
	
	public Bank(String n) {
	}
	
	public String getName() {
		return null;
	}
	
	public int createAccount(String name, int date, double initial) {
		return 0;
	}
	
	public Account deleteAccount(int code, int date) throws InvalidCode {
		return null;
	}
	
	public Account getAccount(int code) throws InvalidCode {
		return null;
	}

	public void deposit(int code, int date, double value) throws InvalidCode {
	}

	public void withdraw(int code, int date, double value) throws InvalidCode, InvalidValue {
	}
	
	public void transfer(int fromCode, int toCode, int date, double value) throws InvalidCode, InvalidValue {
	}
	
	public double getTotalDeposit() {
		return 0.0;
	}
	
	public List<Account> getAccounts() {
		return null;
	}
	
	public List<Account> getAccountsByBalance(double low, double high) {
		return null;
	}
	
	public long getPerCentHigher(double min) {
		return 0;
	}
}
