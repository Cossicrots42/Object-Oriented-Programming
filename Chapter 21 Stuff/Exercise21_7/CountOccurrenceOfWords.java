package Exercise21_7;

import java.util.*;

public class CountOccurrenceOfWords {

	  public static void main(String[] args) {
		  
          String text = "Good morning. Have a good class. " +
               "Have a good visit. Have fun!";
          
          aReallyLongString longString = new aReallyLongString();
          
          String NVT = longString.getThatString();

          ArrayList<WordOccurance> WORDS = new ArrayList<>();
          
          String[] words = NVT.split("[\\s+\\p{P}]");

          for (int i = 0; i < words.length; i++) {

               String key = words[i].toLowerCase();

               if (key.length() > 0) {
            	   
            	   Boolean contains = false;
            	   
            	   for (int j = 0; j < WORDS.size(); j++) {
            		   
            		   if (key.equals(WORDS.get(j).getWord())) {
            			   
            			   contains = true;
            			   WORDS.get(j).increase();
            			   break;
            			   
            		   }
            		   
            	   } //for (int j = 0; j < WORDS.size(); j++)
            	   
            	   if (!contains) {
            		   
            		   WORDS.add(new WordOccurance(key, 1));
            		   
            	   }
            	   
               } //if (key.length() > 0)

          } //for (int i = 0; i < words.length; i++)

          Collections.sort(WORDS);
          
          for (int i = 0; i < WORDS.size(); i++) {
        	  
        	  System.out.println(WORDS.get(i).toString());
        	  
          }
          
     } //public static void main(String[] args)

} //public class CountOccurrenceOfWords
