package Exercise21_7;

public class WordOccurance implements Comparable {

    private String word;
    private int count;

    public WordOccurance() {

         // No-Arg Constructor

    }


    public WordOccurance(String word, int count) {

         this.word = word;
         this.count = count;

    }


    public void setWord(String word) {this.word = word;}


    public void setCount(int count) {this.count = count;}


    public String getWord() {return this.word;}


    public int getCount() {return this.count;}

    public void increase() {
    	
    	this.count++;
    	
    }
    
    public void increase(int add) {
    	
    	this.count = this.count + add;
    	
    }
    
    @Override
    public String toString() {
    	
    	return (this.word + ": " + this.count);
    	
    }

    @Override
    public int compareTo(Object o) {

         if (o.getClass() == this.getClass()) {

              WordOccurance compare = (WordOccurance) o;

              if (this.count > compare.count) {

                   return 1;

              } else if (this.count < compare.count) {

                   return -1;

              } else {

                   return 0;

              }

         } else {

        	  throw new IllegalArgumentException("Cannot compare WordOccurance to an Object of different type.");

         }

    }

}
