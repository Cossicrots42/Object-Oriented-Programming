package workingOnStuff;

/*
Author: Malachi Mock
Date: 6/30/2025

Description: Demonstrates that the sort(ArrayList<E> list) method functions properly.
*/
import java.util.ArrayList;

public class Exercise19_09 {
	
    public static void main(String[] args) {
    	
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(14);
        list.add(24);
        list.add(4);
        list.add(42);
        list.add(5);
        
        list.add(10);
        list.add(9);
        list.add(8);
        list.add(7);
        list.add(6);
        list.add(5);
        list.add(4);
        list.add(3);
        list.add(2);
        list.add(1);
        list.add(0);
        list.add(-1);
        list.add(-2);
        list.add(-3);
        list.add(-4);
        list.add(-5);
        list.add(-6);
        list.add(-7);

        Exercise19_09.<Integer>sort(list);

        System.out.print(list);
        
    }
    
    public static <E extends Comparable<E>> void sort(ArrayList<E> list) {
    	
    	list = helpSort(list);
    	
    }
    
    public static <E extends Comparable<E>> ArrayList<E> helpSort(ArrayList<E> list) {
    	
    	ArrayList<E> SortedArray = list;
    		
    	Boolean SORTED = false;
    	
    	Boolean AllSorted;
    		
    	while (!SORTED) {
    		
    		System.out.println("Working on it..."); //JUST FOR DEBUGGING
    	
    		AllSorted = true;
    	
    			for (int i = 0; i < (SortedArray.size() - 1); i++) {
    		
    				if (SortedArray.get(i).compareTo(SortedArray.get(i + 1)) > 0) {
    			
    					AllSorted = false;
    			
    					E big = SortedArray.get(i);
    					E small = SortedArray.get(i + 1);
    			
    					SortedArray.set(i, small);
    					SortedArray.set(i + 1, big);
    			
    				}
    		
    			}
    		
    		SORTED = AllSorted;
    		
    	}
    	
    	return SortedArray;
    	
    }
    
}


