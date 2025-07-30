package exercise22_1;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

// As you can see, I forgot my hard drive today.

class ConsecutiveStringComparator implements Comparator<String> {
	
    @Override
    public int compare(String s1, String s2) {
   	 	
   	 	String S1 = s1.toLowerCase();
   	 	String S2 = s2.toLowerCase();
   	 	
   	 	char[] S1A = S1.toCharArray();
   	 	char[] S2A = S2.toCharArray();
   	 	
   	 	int lengthS1 = 0;
   	 	int lengthS2 = 0;
   	 	
   	 	for (char a : S1A) {if (Character.isLetter(a)) {lengthS1++;}}
   	 	for (char a : S2A) {if (Character.isLetter(a)) {lengthS2++;}}
    	
    	return lengthS2 - lengthS1;
        
    } //public int compare(String s1, String s2)
    
} //class MyCustomComparator implements Comparator<String> 

public class Exercise_22_1 {

     public static void main(String[] args) {
    	 
          Scanner input = new Scanner(System.in);

          System.out.println("This is a program that takes a string and outputs the longest "
               + "sub-string of that string that maintains alphabetical order of its characters."
               + " (IGNORES ALL NON-LETTERS) "
               + " Enter a string:");

          String thing = input.nextLine();
          
          System.out.println("Maximum consecutive substring: " + actuallyFifthTimesTheCharm(thing));
          
          input.close();

     } //public static void main(String[] args)

     public static String actuallyFifthTimesTheCharm(String original) {
    	 
    	 PriorityQueue<String> consecutiveStrings = new PriorityQueue<>(new ConsecutiveStringComparator());
    	 
    	 if (original == null || original.length() <= 1) {return original;}
    	     	 
    	 StringBuilder thing = new StringBuilder();
    	     	 
    	 for (int i = 0; i < original.length(); i++) {
    		 
    		 //System.out.println("ITERATION NUMBER: " + i);
    		     		 
    		 if (thing.length() == 0) {
    			 
        		 //System.out.println("Thing had nothing in it!");
    			 
    			 thing.append(original.charAt(i));
    			 continue;
    			 
    		 } else if (!(Character.isLetter(thing.charAt(thing.length() - 1)))) {
    			 
        		 //System.out.println("Most recent chatacter was not a letter.");
    			 
    			 thing.append(original.charAt(i));
    			 continue;
    			 
    		 } //if (thing.length() == 0) {...} else if (!(Character.isLetter(thing.charAt(thing.length() - 1)))) {

    		 int biscuit = 1;    		 
    		     		 
    		 while ((!(Character.isLetter(original.charAt(i)))) && (i + 1 < original.length())) {
    			 
    			 //System.out.println("Still not a letter!");
    			 
    			 thing.append(original.charAt(i));
    			 i++;
    			 biscuit++;
    			     			 
    		 } //while ((!(Character.isLetter(original.charAt(i)))) && (i + 1 < original.length()))
    		 
    		 char now = Character.toLowerCase(thing.charAt(thing.length() - biscuit));
    		 
    		 if (Character.isLetter(original.charAt(i))) {
    			 
        		 char next = Character.toLowerCase(original.charAt(i));
        		 
        		 if (now - next <= 0) {
        			 
        			 //System.out.println(thing.toString() + " mantains order!");
        			 
        			 thing.append(original.charAt(i));
        			 
        		 } else {
        			 
        			 //System.out.println(thing.toString() + " DOES NOT mantain order!");
        			     			 
        			 consecutiveStrings.add(thing.toString());
        			 thing.setLength(0);
        			 thing.append(original.charAt(i));
        			 
        		 } //if (now - next <= 0) {...} else {
    			 
    		 } //if (Character.isLetter(original.charAt(i)))
    		 
    		 if (i == original.length() - 1) {
    			 
    			 consecutiveStrings.add(thing.toString());
    			 
    		 } //if (i == original.length() - 1)
    		 
    	 } //for (int i = 0; i < original.length(); i++)
    	 
    	 if (consecutiveStrings.size() == 0) {
    		 
			 //System.out.println("There was nothing inside the data!");
    		 consecutiveStrings.add(thing.toString());
    		 
    	 } //if (consecutiveStrings.size() == 0)
    	 
    	 //System.out.println(consecutiveStrings);
    	 
    	 return consecutiveStrings.peek();
    	 
     } //public static String actuallyFifthTimesTheCharm(String original)

} //public class Exercise_22_1
