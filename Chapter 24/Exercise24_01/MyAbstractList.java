package notDefault;

public abstract class MyAbstractList<E> implements MyList<E> {

     protected int size = 0; // The size of the list

     /** Create a default list */
     protected MyAbstractList() {

     }

     /** Create a list from an array of objects */
     protected MyAbstractList(E[] objects) {

          for (int i = 0; i < objects.length; i++) {
        	  
               add(objects[i]);
               
          }

     }

     /** Add a new element at the end of this list */
     public void add(E e) {

          add(size, e);

     }

     /** Return true if this list contains no elements */
     public boolean isEmpty() {

          return (size == 0);

     }

     /** Return the number of elements in this list */
     public int size() {

          return size;

     }

     /**
      * Remove the first occurrence of the element o from this list.
      * Shift any subsequent elements to the left.
      * Return true if the element is removed.
      */
     public boolean remove(E e) {

          if (indexOf(e) >= 0) {
        	  
               remove(indexOf(e));
               return true;
               
          } else {
        	  
               return false;
               
          }

     }
     
     /** Adds the elements in otherList to this list.
     * Returns true if this list changed as a result of the call */ 
     public boolean addAll(MyList<E> otherList) {
    	 
    	 MyList<E> newList = this;
    	 
    	 if (otherList.size() > 0) {

    		 for (int i = 0; i < otherList.size(); i++) {
    		 
    			 newList.add(otherList.get(i));
    		 
    	 	}
    		 
    	 }
    	 
    	 if (newList.equals(this)) {
    		 
    		 return false;
    		 
    	 } else {
    		 
    		 this.clear();
    		 
    		 for (int i = 0; i < newList.size(); i++) {
    			 
    			 this.add(newList.get(i));
    			 
    		 }
    		 
    		 return true;
    		 
    	 }
    	 
     } //public boolean addAll()

     /** Removes all the elements in otherList from this list
     * Returns true if this list changed as a result of the call */ 
     public boolean removeAll(MyList<E> otherList) {
    	 
    	 MyList<E> newList = this;
    	 
    	 for (int i = 0; i < otherList.size(); i++) {
    		 
    		 for (int j = 0; j < newList.size(); j++) {
    			 
    			 if (otherList.get(i).equals(newList.get(j))) {
    				 
    				 newList.remove(j);
    				 
    			 }
    			 
    		 }
    		 
    	 }
    	 
    	 if (newList.equals(this)) {
    		 
    		 return false;
    		 
    	 } else {
    	 
    		 this.clear();
    		 
    		 for (int i = 0; i < newList.size(); i++) {
    			 
    			 this.add(newList.get(i));
    			 
    		 }
    		 
    		 return true;
    	 
    	 }
    	 
    	 
     } //public boolean removeAll()

     /** Retains the elements in this list that are also in otherList 
     * Returns true if this list changed as a result of the call */ 
     public boolean retainAll(MyList<E> otherList) {
    	 
    	 MyList<E> newList = new MyArrayList<E>();
    	 
    	 boolean retain = false;
    	 
    	 for (int i = 0; i < this.size(); i++) {
    		 
    		 retain = false;
    		 
    		 for (int j = 0; j < otherList.size(); j++) {
    			 
    			 if (this.get(i).equals(otherList.get(j))) {
    				 
    				 retain = true;
    				 break;
    				 
    			 }
    			 
    		 } // inner for loop
    		 
    		 if (retain) {
    		 
    			 newList.add(this.get(i));
    		 
    		 }
    		 
    	 } // for loop
    	 
    	 if (newList.equals(this)) {
    		 
    		 return false;
    		 
    	 } else {
    	 
    		 this.clear();
    		 
    		 for (int i = 0; i < newList.size(); i++) {
    			 
    			 this.add(newList.get(i));
    			 
    		 }
    		 
    		 return true;
    	 
    	 }
    	 
     } //public boolean retainAll()
     
} //public abstract class MyAbstractList<E> implements MyList<E>
