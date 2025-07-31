package exercise23_7;

public class HeapSort {

     /** Heap sort method */
     public static <E extends Comparable<E>> void heapSort(E[] list) {

          // Create a Heap of integers
          Heap<E> heap = new Heap<>();

          // Add elements to the heap
          for (int i = 0; i < list.length; i++) {
        	  
               heap.add(list[i]);
               
          } //for (int i = 0; i < list.length; i++)

          // Remove elements from the heap
          for (int i = list.length - 1; i >= 0; i--) {
        	  
               list[i] = heap.remove();
               
          } //for (int i = list.length - 1; i >= 0; i--)

     } //public static <E extends Comparable<E>> void heapSort(E[] list)

     /** A test method */
     public static void main(String[] args) {

          Integer[] list = {-44, -5, -3, 3, 3, 1, -4, 0, 1, 2, 4, 5, 53};
          
//          Integer[] list = {12, 13, 7, 19, 1, 24, -6, 2};
          
          heapSort(list);

          for (int i = 0; i < list.length; i++) {
        	  
               System.out.print(list[i] + " ");
               
          } //for (int i = 0; i < list.length; i++)

     } //public static void main(String[] args)

} //public class HeapSort
