package Exercise32_13;

import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ForkJoinPool;

public class ParallelMergeSort {

     public static void main(String[] args) {

          final int SIZE = 7_000_000;
          Integer[] list1 = new Integer[SIZE];
          Integer[] list2 = new Integer[SIZE];
          
          for (int i = 0; i < list1.length; i++) {
        	  
               list1[i] = list2[i] = (int) (Math.random() * 10_000_000);
               
          } //for (int i = 0; i < list1.length; i++)

          long startTime = System.currentTimeMillis();
          parallelMergeSort(list1); // Invoke parallel merge sort
          long endTime = System.currentTimeMillis();

          System.out.println(
               "\nParallel time with " +
               Runtime.getRuntime().availableProcessors() +
               " processors is " + (endTime - startTime) + " milliseconds"
          );

          startTime = System.currentTimeMillis();
          MergeSort.mergeSort(list2); // MergeSort is in Listing 24.5
          endTime = System.currentTimeMillis();

          System.out.println(
               "\nSequential time is " + (endTime - startTime) + " milliseconds"
          );
          
          int line = 0;
          
          for (Integer i : list1) {
        	  
        	  System.out.print(i + " ");
        	  
        	  line++;
        	  
        	  if (line == 9) {
        		  
        		  System.out.println();
        		  line = 0;
        		  
        	  } //if (line == 9)
        	  
          } //for (Integer i : list1)

     } //public static void main(String[] args)
     
     public static <E extends Comparable<E>> void parallelMergeSort(E[] list) {
    	 
         RecursiveAction mainTask = new GenericSortTask(list);
         ForkJoinPool pool = new ForkJoinPool();
         pool.invoke(mainTask);
    	 
     } //public static <E extends Comparable<E>> void parallelMergeSort(E[] list)
     
     private static class GenericSortTask<E extends Comparable<E>> extends RecursiveAction {
 
        private final int THRESHOLD = 500;
        private E[] list;

		public GenericSortTask(E[] list) {
       	  
              this.list = list;
              
        } //public <E extends Comparable<E>> void GenericSortTask(E[] list)
    	 
		@Override
		protected void compute() {
			
            if (list.length < THRESHOLD) {

                java.util.Arrays.sort(list);

           } else {

                // Obtain the first half
                E[] firstHalf = (E[]) (new Comparable[list.length / 2]);
                System.arraycopy(list, 0, firstHalf, 0, list.length / 2);

                // Obtain the second half
                int secondHalfLength = list.length - list.length / 2;
                E[] secondHalf = (E[]) (new Comparable[secondHalfLength]);
                System.arraycopy(list, list.length / 2, secondHalf, 0, secondHalfLength);

                // Recursively sort the two halves
                invokeAll(
                     new GenericSortTask(firstHalf),
                     new GenericSortTask(secondHalf)
                );

                // Merge firstHalf with secondHalf into list
                MergeSort.merge(firstHalf, secondHalf, list);

           } //if (list.length < THRESHOLD) {...} else 
			
		} //protected void compute()
    	
     } //private static class GenericSortTask extends RecursiveAction

} //public class ParallelMergeSort
