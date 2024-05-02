//Is this what you meant by a test program? You were kinda vague about it.
import java.util.Scanner;
import java.util.Date;
public class TestAccounts {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("Good evening, would you like to create a default Account (1), a checking Account (2), or a savings Account (3)?");
		
		//takes option for account type.
		int option = 0;
		while (option > 2 || option < 1) {
			System.out.println("Please enter a valid option or we will be mad.");
			option = input.nextInt();
		}
		
		//Sets an ID
		int ID = 0;
		System.out.println("Choose an ID (in the form of an Integer)");
		ID = input.nextInt();
		
		
		//Sets a balance
		double initialBalance = 0;
		System.out.println("Alright, now how much money would you like to put in?");
		initialBalance = input.nextDouble();
		
		MakesAccounts(option, ID, initialBalance);
		
		}
	
	//Yes, accounts can be made.
	public static void MakesAccounts(int option, int ID, double initialBalance) {
		
		double interest = 1;
		Date now = new Date();
		if (option == 1) {
			Account bank = new Account(ID, initialBalance, interest, now);
		}
		if (option == 2) {
			CheckingAccount bank = new CheckingAccount(ID, initialBalance, interest, now);
		}
		if (option == 3) {
			SavingsAccount bank = new SavingsAccount(ID, initialBalance, interest, now);
		}
	}
/*Knowing that you read these, I have to put interesting things in them. Like how I'm still sad
 about the girl I had a massive crush on years ago, even though she hosed me at homecoming. But like,
 certainly, she wasn't very deliberate about it. Afterall, she did send me an apology text the night
 of, and some Macaroons a week later, and her ass is enourmous. But genuinely, though. We had been
 friends a long time ago, and she always seemed sweet and kind. Whatever, I hope this doesn't win me
 a trip to the councilors' office. Like, actually, plz don't. I have a job, a car, a decent gaming
 setup, and a fancy lawn mower. So, according to my science I'm fine.
 */
}