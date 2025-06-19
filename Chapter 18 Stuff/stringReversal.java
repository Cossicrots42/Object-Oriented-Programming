package stuff;

import java.util.Scanner;

public class stringReversal {
	
    static Scanner input = new Scanner(System.in);

    public static void main (String[] Args) {
    	
    	System.out.println("This program reverses Strings. There are two methods by which it goes about this. They are identical in output. Anyways, enter the String you'd like reversed.");
    	
    	String reverse = input.next();
    	
    	System.out.println("Ok, and which method would you like to use? 1: Typical 2: Redundant");
    	
    	int option = input.nextInt();
    	
    	if (option == 1) {
    		
    		System.out.println("Here is your string reversed:");
    		
    		StringBuilder REVERSE = new StringBuilder(reverse);
    		System.out.print(REVERSE.reverse());
    		
    	}
    	
    	if (option == 2) {
    		
    		System.out.println("Here is your string reversed:");
    		reverseDisplay(reverse);
    		
    	}
    	
    }
    
    public static void reverseDisplay(String value) {
    	
    	System.out.print(helpReverseDisplay(value, "", value.length()));
    	
    }
    
    public static String helpReverseDisplay(String start, String reversed, int pos) {
    	
    	String result = "";
    	
    	if (pos != 0) {
    	
    		char moveChar = start.charAt(pos - 1);
    		result = reversed + moveChar;
    	
    	} else {
    		
    		return reversed;

    	}
    	
    	return helpReverseDisplay(start, result, pos - 1);
    	 
    	
    }

}
