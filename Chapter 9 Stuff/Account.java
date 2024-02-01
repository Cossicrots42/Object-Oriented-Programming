import java.util.Date;
public class Account {
	
	java.util.Date date = new java.util.Date();
	int id = 0;
	double balance = 0;
	double annualInterestRate = 0;
	Date now = new Date();
	
	public Account(int newId, int newBalance, double newAnnualInterestRate, Date today) {
		id = newId;
		balance = newBalance;
		annualInterestRate = newAnnualInterestRate;
		now = today;
	}
	
	double getMonthlyInterestRate() {
		return (annualInterestRate / 12);
	}
	double getMonthlyInterest() {
		return (balance * (1 + ((annualInterestRate / 12) / 100)) - balance);
	}
	void withdraw(int cash) {
		balance = balance - cash;
	}
	void deposit(int check) {
		balance = balance + check;
	}
}