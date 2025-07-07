package notDefault;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Exercise08_37 {
	
    public static void main(String[] args) {
    	    	    	    	
        String[][] stateCapital = {
        		
            {"Alabama", "Montgomery"},
            {"Alaska", "Juneau"},
            {"Arizona", "Phoenix"},
            {"Arkansas", "Little Rock"},
            {"California", "Sacramento"},
            {"Colorado", "Denver"},
            {"Connecticut", "Hartford"},
            {"Delaware", "Dover"},
            {"Florida", "Tallahassee"},
            {"Georgia", "Atlanta"},
            {"Hawaii", "Honolulu"},
            {"Idaho", "Boise"},
            {"Illinois", "Springfield"},
            {"Indiana", "Indianapolis"},
            {"Iowa", "Des Moines"},
            {"Kansas", "Topeka"},
            {"Kentucky", "Frankfort"},
            {"Louisiana", "Baton Rouge"},
            {"Maine", "Augusta"},
            {"Maryland", "Annapolis"},
            {"Massachusetts", "Boston"}, // Corrected typo here
            {"Michigan", "Lansing"},
            {"Minnesota", "Saint Paul"},
            {"Mississippi", "Jackson"},
            {"Missouri", "Jefferson City"},
            {"Montana", "Helena"},
            {"Nebraska", "Lincoln"},
            {"Nevada", "Carson City"},
            {"New Hampshire", "Concord"},
            {"New Jersey", "Trenton"},
            {"New York", "Albany"},
            {"New Mexico", "Santa Fe"},
            {"North Carolina", "Raleigh"},
            {"North Dakota", "Bismarck"},
            {"Ohio", "Columbus"},
            {"Oklahoma", "Oklahoma City"},
            {"Oregon", "Salem"},
            {"Pennsylvania", "Harrisburg"},
            {"Rhode Island", "Providence"},
            {"South Carolina", "Columbia"},
            {"South Dakota", "Pierre"},
            {"Tennessee", "Nashville"},
            {"Texas", "Austin"},
            {"Utah", "Salt Lake City"},
            {"Vermont", "Montpelier"},
            {"Virginia", "Richmond"},
            {"Washington", "Olympia"},
            {"West Virginia", "Charleston"},
            {"Wisconsin", "Madison"},
            {"Wyoming", "Cheyenne"}
            
        };
        
    	ArrayList<String[]> STATE_CAPITAL = new ArrayList<>();

    	for(int i = 0; i < stateCapital.length; i++) {
    		
    		STATE_CAPITAL.add(stateCapital[i]);
    		
    	}
    	
    	Collections.shuffle(STATE_CAPITAL);

        Scanner input = new Scanner(System.in);
        
        int correctCount = 0;

        for (int i = 0; i < STATE_CAPITAL.size(); i++) {
        	
        	String[] question = STATE_CAPITAL.get(i);
            // Prompt the user with a question
            System.out.print("What is the capital of " + question[0] + "? ");
            String capital = input.nextLine().trim().toLowerCase();

            if (capital.toLowerCase().equals(question[1].toLowerCase())) {
            	
                System.out.println("YES!! You are absolutely correct!");
                correctCount++;
                
            } else {
            	
                System.out.println("NO!! The capital of " + question[0] + " is NOT " + capital + "!"
                + " I find it very disturbing that you do not know that. Since you have proven to be"
                + " so pathetically ignorant, I shall attempt to enlighten you. The actual capital of "
                + question[0] + " is " + question[1] + ". And now you know!");
                
            }
            
        }

        System.out.println("The correct count is " + correctCount);
        
    }
    
}