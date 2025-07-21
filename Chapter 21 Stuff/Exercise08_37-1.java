package Exercise21_9;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

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
               {"Massachusettes", "Boston"},
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
          
          HashMap StateCapitals = new HashMap();
          
          for (int i = 0; i < stateCapital.length; i++) {
        	  
        	  StateCapitals.put(stateCapital[i][0], stateCapital[i][1]);
        	  
          }

          Scanner input = new Scanner(System.in);
          
          Set states = StateCapitals.keySet();
          
          Object[] theStates = states.toArray();
          
          while (true) {
        	  
        	  	System.out.println("Enter a U.S. State and this program will tell you the Capital!");
          
          		String state = input.nextLine();
          		
          		for (int j = 0; j < theStates.length; j++) {
          			
          			if (state.equalsIgnoreCase(theStates[j].toString())) {
          				
          				state = theStates[j].toString();
          				break;
          				
          			}
          			
          		}
          
          		if (StateCapitals.containsKey(state)) {
        	  
        	  			System.out.println("The Capitals of " + state + " is: " + StateCapitals.get(state));
          
          		} else {
          			
          			System.out.println("Hmm... I don't think " + state + " is a U.S. state");
          			
          		}
          
          }
          
     }

}
