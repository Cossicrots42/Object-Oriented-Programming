package exercise23_3;

import java.util.Collections;

/*
Author: Malachi Mock
Date: 7/30/2025

Description: 
*/

import java.util.Comparator;

public class Exercise23_03 {

     public static void main(String[] args) {

          Integer[] list = {2, 3, 2, 5, 6, 1, -2, 3, 14, 12};
          quickSort(list);

          for (int i = 0; i < list.length; i++) {
        	  
               System.out.print(list[i] + " ");
               
          } //for (int i = 0; i < list.length; i++)

          System.out.println();

          Circle[] list1 = {
        		  
               new Circle(2), new Circle(3), new Circle(2),
               new Circle(5), new Circle(6), new Circle(1),
               new Circle(2), new Circle(3), new Circle(14), new Circle(12)
               
          }; //Circle[] list1

          quickSort(list1, new GeometricObjectComparator());

          for (int i = 0; i < list1.length; i++) {
        	  
               System.out.println(list1[i] + " ");
               
          } //for (int i = 0; i < list1.length; i++)

     } //public static void main(String[] args)
     
     public static <E extends Comparable<E>> void quickSort(E[] list) {
    	 
    	 sort(list, 0, list.length - 1);
    	 
     } //public static <E extends Comparable<E>> void quickSort(E[] list)
     
     public static <E extends Comparable<E>> void sort(E[] array, int low, int high) {
    	 
    	 if (low < high) {
    		 
    		 int index = getIndex(array, low, high);
    		 
    		 sort(array, low, index - 1);
    		 sort(array, index + 1, high);
    		 
    	 } //if (low < high)
    	 
     } //public static <E extends Comparable<E>> void sort(E[] arr, int low, int high)
     
     public static <E extends Comparable<E>> int getIndex(E[] array, int low, int high) {
    	 
    	 E codex = array[high];
    	 int iterator = low - 1;
    	 
    	 for (int j = low; j < high; j++) {
    		 
    		 if (array[j].compareTo(codex) < 0) {
    			 
    			 iterator++;
    			 
    			 E temp = array[iterator];
    			 array[iterator] = array[j];
    			 array[j] = temp;
    			 
    		 } //if (array[j].compareTo(codex) < 0)
    		 
    	 } //for (int j = low; j < high; j++)
    	 
    	 E temp = array[iterator + 1];
    	 
    	 array[iterator + 1] = array[high];
    	 array[high] = temp;
    	 
    	 return iterator + 1;
    	 
     } //public static <E extends Comparable<E>> int orderize(E[] array, int low, int high)
     
     public static <E> void quickSort(E[] list, Comparator<? super E> comparator) {
    	 
    	 sort(list, 0, list.length - 1, comparator);
    	 
     } //public static <E> void quickSort(E[] list, Comparator<? super E> comparator)
     
     public static <E> void sort(E[] array, int low, int high, Comparator<? super E> comparator) {
    	 
    	 if (low < high) {
    		 
    		 int index = getIndex(array, low, high, comparator);
    		 
    		 sort(array, low, index - 1, comparator);
    		 sort(array, index + 1, high, comparator);
    		 
    	 } //if (low < high)
    	 
     } //public static <E> void sort(E[] arr, int low, int high)
     
     public static <E> int getIndex(E[] array, int low, int high, Comparator<? super E> comparator) {
    	     	 
    	 E codex = array[high];
    	 int iterator = low - 1;
    	 
    	 for (int j = low; j < high; j++) {
    		 
    		 if (comparator.compare(array[j], codex) < 0) {
    			 
    			 iterator++;
    			 
    			 E temp = array[iterator];
    			 array[iterator] = array[j];
    			 array[j] = temp;
    			 
    		 } //if (array[j].compareTo(codex) < 0)
    		 
    	 } //for (int j = low; j < high; j++)
    	 
    	 E temp = array[iterator + 1];
    	 
    	 array[iterator + 1] = array[high];
    	 array[high] = temp;
    	 
    	 return iterator + 1;
    	 
     }

} //public class Exercise23_03


/* 
 * 
 */
