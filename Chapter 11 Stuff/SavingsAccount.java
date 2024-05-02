import java.util.Date;

public class SavingsAccount extends Account{
	/*Well, you see, I'm sad because I broke up with my girlfriend, but I'm
	 also happy because I bought a new, really fancy lawn mower.
	 */

	public SavingsAccount(int newId, double newBalance, double newAnnualInterestRate, Date today) {
		super(newId, newBalance, newAnnualInterestRate, today);
	}
	
	void withdraw(int check) {
		if (balance - check < 0) {
			System.out.print("You cannot overdraw your savings account.");
		} else {
			balance = balance - check;
		}
	}
}