package stuff;

import java.util.Scanner;

public class ComputeGCD {
	
	static int GCD = 0;
	
    static Scanner input = new Scanner(System.in);

	public static void main(String[] peopleForgetYouCanNameThisVariableWhateverYouWant) {
		
		System.out.println("Enter 2 numbers and this program will determine their greatest common divisor.");
		
		int ONE = input.nextInt();
		int TWO = input.nextInt();
		
		findGCD(ONE, TWO);
		
		if (GCD == 1) {
			
			System.out.println("There are no common divisors of " + ONE + " and " + TWO + ", except of course 1.");
			
		} else {
		
			System.out.println("The greatest common divisor of " + ONE + " and " + TWO + " is " + GCD);
		
		}
		
	}
	
	//This is literally just the same as finding the greatest common factor.
	
	public static int findGCD(int one, int two) {
		
		if (one % two == 0) {
			
			GCD = two;
			
		} else {
			
			findGCD(two, one%two);
			
		}
		
		return GCD;
		
	}
	
}
