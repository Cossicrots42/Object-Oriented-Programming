package exercise23_7;

/*
Author: Y. Daniel Liang

Listing 23.9 Heap Class
*/

public class Heap<E extends Comparable<E>> {

     private java.util.ArrayList<E> list = new java.util.ArrayList<>();

     /** Create a default heap */
     public Heap() {

     } //public Heap()

     /** Create a heap from an array of objects */
     public Heap(E[] objects) {

          for (int i = 0; i < objects.length; i++) {
        	  
               add(objects[i]);
               
          } //for (int i = 0; i < objects.length; i++)

     } //public Heap(E[] objects)

     /** Add a new object into the heap */
     public void add(E newObject) {

          list.add(newObject); // Append to the heap
          int currentIndex = list.size() - 1; // The index of the last node

          while (currentIndex > 0) {

               int parentIndex = (currentIndex - 1) / 2;

               // Swap if the current object is greater than its parent
               if (list.get(currentIndex).compareTo(list.get(parentIndex)) < 0) {

                    E temp = list.get(currentIndex);
                    list.set(currentIndex, list.get(parentIndex));
                    list.set(parentIndex, temp);

               } else {
            	   
                    break; // The tree is a heap now
                    
               } //if (list.get(currentIndex).compareTo(list.get(parentIndex)) > 0) {...} else 

               currentIndex = parentIndex;

          } //while (currentIndex > 0)

     } //public void add(E newObject)

     /** Remove the root from the heap */
     public E remove() {

          if (list.size() == 0) {
        	  
               return null;
               
          } //if (list.size() == 0)

          E removedObject = list.get(0);
          list.set(0, list.get(list.size() - 1));
          list.remove(list.size() - 1);

          int currentIndex = 0;

          while (currentIndex < list.size()) {

               int leftChildIndex = 2 * currentIndex + 1;
               int rightChildIndex = 2 * currentIndex + 2;

               // Find the maximum between two children
               if (leftChildIndex >= list.size()) {
            	   
                    break; // The tree is a heap
                    
               } //if (leftChildIndex >= list.size())

               int maxIndex = leftChildIndex;

               if (rightChildIndex < list.size()) {
            	   
                    if (list.get(maxIndex).compareTo(list.get(rightChildIndex)) > 0) {
                    	
                         maxIndex = rightChildIndex;
                         
                    } //if (list.get(maxIndex).compareTo(list.get(rightChildIndex)) < 0)
                    
               } //if (rightChildIndex < list.size())

               // Swap if the current node is less than the maximum
               if (list.get(currentIndex).compareTo(list.get(maxIndex)) > 0) {

                    E temp = list.get(maxIndex);
                    list.set(maxIndex, list.get(currentIndex));
                    list.set(currentIndex, temp);
                    currentIndex = maxIndex;

               } else {
            	   
                    break; // The tree is a heap
                    
               } //if (list.get(currentIndex).compareTo(list.get(maxIndex)) < 0) {...} else

          } //while (currentIndex < list.size())

          return removedObject;

     } //public E remove()

     /** Get the number of nodes in the tree */
     public int getSize() {

          return list.size();

     } //public int getSize()

     /** Return true if heap is empty */
     public boolean isEmpty() {

          return list.size() == 0;

     } //public boolean isEmpty()

} //public class Heap<E extends Comparable<E>>
