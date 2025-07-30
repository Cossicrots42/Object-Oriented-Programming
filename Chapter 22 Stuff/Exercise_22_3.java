package exercise22_3;

import java.util.ArrayList;
import java.util.Scanner;

//First rule of computer science: If you don't know what it does, don't touch it!

public class Exercise_22_3 {

	public static void main (String[] Args) {
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("This program demonstrates the profiency level of the developer to build the core logic behind Find in Text features (Case-insensetive). Start by entering text: ");
		String TEXT = input.nextLine();
		System.out.println("What do you need to find in this text?");
		String FIND = input.nextLine();
		
		System.out.println(FIND + " can be found at these indexes: ");
		
		int[] indexes = findInText(TEXT, FIND);
		
		for (int i : indexes) {
			
			System.out.print(i + " ");
			
		} //for (int i : indexes)
		
		input.close();
		
	} //public static void main (String[] Args)
	
	public static int[] findInText(String text, String find) {
		
		ArrayList<Integer> FINDs = new ArrayList<>();
		
		String TEXT = text.toLowerCase();
		String FIND = find.toLowerCase();
		
		int index = 0;
		
		int place = -1;
		
		for (int i = 0; i < TEXT.length(); i++) {
			
			if (TEXT.charAt(i) == FIND.charAt(index)) {
				
				if (index == 0) {place = i;}
				
				if (FIND.length() == 1) {
					
					FINDs.add(place);
					continue;
					
				} //if (FIND.length() == 1)
				
				if (index == FIND.length() - 1) {
					
					index = 0;

					FINDs.add(place);
					
				} //if (index == FIND.length() - 1)
				
				index++;
				
			} else {
				
				index = 0;
				place = -1;
				
			} //if (TEXT.charAt(i) == FIND.charAt(index)) {...} else {
			
		} //for (int i = 0; i < TEXT.length(); i++)
		
		int[] actualFinds = new int[FINDs.size()];
		
		int iterator = 0;
				
		for (Integer i : FINDs) {
			
			actualFinds[iterator] = i.intValue();
			
			iterator++;
			
		}
		
		return actualFinds;
		
	} //public static int findInText(String text, String find)
	
} //public class Exercise_22_3 
