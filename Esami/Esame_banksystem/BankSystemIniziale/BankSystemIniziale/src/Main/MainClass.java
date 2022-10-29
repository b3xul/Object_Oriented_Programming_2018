package Main;

import BankServices.Account;
import BankServices.Bank;
import BankServices.Deposit;
import BankServices.InvalidCode;
import BankServices.InvalidValue;
import BankServices.Operation;
import BankServices.Withdrawal;

public class MainClass {

	public static void main(String[] args) {
		Bank b1 = new Bank("Uncle-$crooge");
		int c1 = b1.createAccount("John", 5, 500.5);
		int c2 = b1.createAccount("Mary", 10,  1000.);
		int c3 = b1.createAccount("John", 20,  800.);
		int c4 = b1.createAccount("Paul", 30, 252.4);
		Account a1=null, a3=null;
		
		try {
			b1.deposit(c1, 7, 360.0);
			b1.deposit(c4, 35, 270.0);
			b1.withdraw(c3, 28, 350.0);
			b1.withdraw(c2, 19, 350.0);
			b1.withdraw(c3, 41, 158.0);
			b1.transfer(c1, c3, 8, 400.0);
			a1 = b1.getAccount(c1);
			a3 = b1.deleteAccount(c3,50);
		}
		catch(InvalidCode ic) {
			ic.printStackTrace();
		}
		catch(InvalidValue iv) {
			iv.printStackTrace();
		}
		
		for(Account a : b1.getAccounts()) {
			System.out.println("account: " + a);
			System.out.println(" movements:");
			for(Operation o : a.getMovements())
				System.out.println(o);
			System.out.println();
		}
		
/*		account: 1,John,8,460.5
		 movements:
		8,400.0-
		7,360.0+
		5,500.5+

		account: 2,Mary,19,650.0
		 movements:
		19,350.0-
		10,1000.0+

		account: 4,Paul,35,522.4
		 movements:
		35,270.0+
		30,252.4+
*/		
		System.out.println("deleted account: " + a3);
		System.out.println(" movements:");
		for(Operation o : a3.getMovements())
			System.out.println(o);
		System.out.println();
		
/*		deleted account: 3,John,50,0.0
		 movements:
		50,692.0-
		41,400.0+
		41,158.0-
		28,350.0-
		20,800.0+
*/		
		System.out.println("account: " + a1);
		System.out.println(" movements:");
		for(Operation o : a1.getMovements())
			System.out.println(o);		
		System.out.println(" deposits:");
		for(Deposit d : a1.getDeposits())
			System.out.println(d);		
		System.out.println(" withdrawals:");
		for(Withdrawal w : a1.getWithdrawals())
			System.out.println(w);
		System.out.println();
		
/*		account: 1,John,8,460.5
		 movements:
		8,400.0-
		7,360.0+
		5,500.5+
		 deposits:
		5,500.5+
		7,360.0+
		 withdrawals:
		8,400.0-
*/		
		System.out.println("total deposit in the " + b1.getName() + " bank: " + b1.getTotalDeposit());
		System.out.println();
		
//		total deposit in the Uncle-$crooge bank: 1632.9
		
		System.out.println("accounts with balance higher than 500: " + b1.getPerCentHigher(500) + " %");
		System.out.println();
		
//		accounts with balance higher than 500: 66 %
		
		System.out.println("accounts with balance in range 500..700 :");
		for(Account a : b1.getAccountsByBalance(500, 700)) 
			System.out.println(a);
		System.out.println();
		
/*		accounts with balance in range 500..700 :
			2,Mary,19,650.0
			4,Paul,35,522.4
*/			
	}

}
