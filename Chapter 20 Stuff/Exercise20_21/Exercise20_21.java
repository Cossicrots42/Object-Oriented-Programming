package Exercise20_21;

/*
Author: Malachi Mock
Date: 7/7/2025

Description: This program demonstrates that selectionSort() can sort anything, given a valid comparator.
*/

import java.util.Comparator;
import java.util.PriorityQueue;

public class Exercise20_21 {

     public static void main(String[] args) {

          GeometricObject[] list = {
        		  
               new Circle(5), new Rectangle(4, 5),
               new Circle(5.5), new Rectangle(2.4, 5),
               new Circle(0.5), new Rectangle(4, 65),
               new Circle(4.5), new Rectangle(4.4, 1),
               new Circle(6.5), new Rectangle(4, 5)
               
          };

          Circle[] list1 = {
        		  
               new Circle(2), new Circle(3), new Circle(2),
               new Circle(5), new Circle(6), new Circle(1),
               new Circle(2), new Circle(3), new Circle(14),
               new Circle(12)
               
          };

          selectionSort(list1, new GeometricObjectComparator());

          for (int i = 0; i < list1.length; i++) {
        	  
               System.out.println(list1[i].getArea() + " ");
               
          }


          
     } //public static main void (String[] Args)
     
     public static <E> void selectionSort(E[] list, Comparator<? super E> comparator) {
   	  
    	 PriorityQueue<E> sorted = new PriorityQueue<E>(comparator);
    	     	 
    	 for (E thing : list) {
    		 
    		 sorted.add(thing);
    		 
    	 }
    	 
    	 for (int i = 0; i < list.length; i++) {
    		 
    		 list[i] = sorted.poll();
    		 
    	 }
    	 
     } 

} //class Exercise20_21
