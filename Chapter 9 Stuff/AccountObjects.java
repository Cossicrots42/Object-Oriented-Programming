import java.util.Date;

public class AccountObjects {
	java.util.Date date = new java.util.Date();
	public static void main(String[] args) {
		Date today = new Date();
		Account debitCard = new Account(1122, 20000, 4.5, today);
		
		debitCard.withdraw(2500);
		debitCard.deposit(3000);
		
		System.out.println("The balance of your account is $" + debitCard.balance);
		System.out.println("The monthly interest of your account is $" + debitCard.getMonthlyInterest());
		System.out.println("Your Account was created on " + debitCard.now);
	}

}