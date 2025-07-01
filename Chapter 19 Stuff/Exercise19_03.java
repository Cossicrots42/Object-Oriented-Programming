package workingOnStuff;

/*
Author: Malachi Mock
Date: 6/30/2025

Description: When it comes to Integers of the same number in an ArrayList, there can only be one!
*/
import java.util.ArrayList;

public class Exercise19_03 {
	
    public static void main(String[] args) {
    	
        ArrayList<Integer> list = new ArrayList<Integer>();
        
        list.add(14);
        list.add(24);
        list.add(24);
        list.add(24);
        list.add(24);
        list.add(24);
        list.add(24);
        list.add(24);
        list.add(24);
        list.add(14);
        list.add(14);
        list.add(14);
        list.add(14);
        list.add(14);
        list.add(14);
        list.add(42);
        list.add(42);
        list.add(42);
        list.add(42);
        list.add(42);
        list.add(25);
        list.add(25);
        list.add(25);
        list.add(25);
        list.add(25);
        list.add(25);
        list.add(25);

        ArrayList<Integer> newList = removeDuplicates(list);

        System.out.println("Not a single dingle duplicate.");
        System.out.print(newList);
        
    }

    // Example implementation of removeDuplicates
    public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list) {
    	
    	ArrayList NoDuplicates = new ArrayList<E>();
    	
		for (int i = 0; i < list.size(); i++) {
			
			E item = list.get(i);
			
			Boolean isDuplicate = false;
			
			for (int j = 0; j < NoDuplicates.size(); j++) {
				
				if (item == NoDuplicates.get(j)) {
					
					isDuplicate = true;
					
				}
	
			}
			
			if (!isDuplicate) {
				
				NoDuplicates.add(item);
				
			}
				
		}
					
		return NoDuplicates;
        
    }
    
}