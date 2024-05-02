import java.util.Date;

public class CheckingAccount extends Account{

	public CheckingAccount(int newId, double newBalance, double newAnnualInterestRate, Date today) {
		super(newId, newBalance, newAnnualInterestRate, today);
	}
	
	void withdraw(int check) {
		if (balance - check < -100) {
			System.out.print("You can only overdraw your account up $100.");
		} else {
			balance = balance - check;
		}
	}
}