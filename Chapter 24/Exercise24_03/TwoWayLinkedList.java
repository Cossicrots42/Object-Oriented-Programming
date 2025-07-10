package Exercise24_3;

import java.util.ListIterator;
//import javax.lang.model.element.*;

class TwoWayLinkedList<E> extends java.util.AbstractSequentialList<E> {
	
     private Node<E> head, tail;
     private int size;

     /** Create a default list */
     public TwoWayLinkedList() {

     }

     /** Create a list from an array of objects */
     public TwoWayLinkedList(E[] objects) {

          for (E e : objects) {
        	  
        	  add(e);
               
          }

     } //public TwoWayLinkedList(E[] objects)

     /** Return the head element in the list */
     public E getFirst() {

          if (size == 0) {
        	  
               return null;
               
          } else {
        	  
               return head.element;
               
          }

     } //public E getFirst()

     /** Return the last element in the list */
     public E getLast() {

          if (size == 0) {
        	  
               return null;
               
          } else {
        	  
               return tail.element;
               
          }

     } //public E getLast()

     /** Add an element to the beginning of the list */
     public void addFirst(E e) {

          Node<E> newNode = new Node<E>(e); // Create a new node
          newNode.next = head; // Link the new node with the head
          head = newNode; // Head points to the new node
          size++; // Increase list size

          if (tail == null) {
        	  
               tail = head;
               
          }

          if (head != tail) {
        	  
               // For a two-way linked list, make the next item's previous element the head
        	  head.next.previous = head;
        	          	  
          }

     } //public void addFirst(E e)

     /** Add an element to the end of the list */
     public void addLast(E e) {

          Node<E> newNode = new Node<E>(e);
          Node<E> temp = tail;

          if (tail == null) {
        	  
               head = tail = newNode;
               
          } else {
        	  
               tail.next = newNode;
               tail = tail.next;
               
          }

          size++;

          // For a two-way linked list, link the tail's previous element to temp
  
          tail.previous = temp;


     } //public void addLast(E e)

     /**
      * Add a new element at the specified index in this list
      * The index of the head element is 0
      */
     public void add(int index, E e) {

          if (index == 0) {
        	  
               addFirst(e);
               
          } else if (index >= size) {
        	  
               addLast(e);
               
          } else {
        	  
               Node<E> current = head;
               
               for (int i = 1; i < index; i++) {
            	   
                    current = current.next;
                    
               }

               Node<E> temp = current.next;
               current.next = new Node<E>(e);
               current.next.next = temp;
               size++;

               // For a two-way linked list, link temp's previous element to current's next element
               temp.previous = current.next;
               // For a two-way linked list, link current's next's previous element to current
               current.next.previous = current;
               
          }

     } //public void add(int index, E e)

     /**
      * Remove the head node and return the object that is contained in the removed node.
      */
     public E removeFirst() {

          if (size == 0) {
        	  
               return null;
               
          } else {
        	  
               Node<E> temp = head;
               head = head.next;
               size--;
               
               if (head == null) {
            	   
                    tail = null;
                    
               }
               
               return temp.element;
               
          }

     } //public E removeFirst()

     /**
      * Remove the last node and return the object that is contained in the removed node.
      */
     public E removeLast() {

          if (size == 0) {
        	  
               return null;
               
          } else if (size == 1) {
        	  
               Node<E> temp = head;
               head = tail = null;
               size = 0;
               
               return temp.element;
               
          } else {
        	  
               Node<E> current = head;
               
               for (int i = 0; i < size - 2; i++) {
            	   
                    current = current.next;
                    
               }

               Node<E> temp = tail;
               tail = current;
               tail.next = null;
               size--;
               
               return temp.element;
          }

     } //public E removeLast()

     /**
      * Remove the element at the specified position in this list.
      * Return the element that was removed from the list.
      */
     public E remove(int index) {

          if (index < 0 || index >= size) {
        	  
               return null;
               
          } else if (index == 0) {
        	  
               return removeFirst();
               
          } else if (index == size - 1) {
        	  
               return removeLast();
               
          } else {
        	  
               Node<E> previous = head;
               
               for (int i = 1; i < index; i++) {
            	   
                    previous = previous.next;
                    
               }

               Node<E> current = previous.next;
               previous.next = current.next;
               current.next.previous = previous; // For a two-way linked list
               size--;
               
               return current.element;
               
          }

     } //public E remove(int index)

     @Override
     public String toString() {

          StringBuilder result = new StringBuilder("[");
          Node<E> current = head;

          for (int i = 0; i < size; i++) {
        	  
               result.append(current.element);
               current = current.next;
               
               if (current != null) {
            	   
                    result.append(", ");
                    
               } else {
            	   
                    result.append("]");
                    
               }
               
          }

          return result.toString();

     } //public String toString()

     /** Clear the list */
     public void clear() {

          head = tail = null;

     } //public void clear()

     /** Return true if this list contains the element o */
     public boolean contains(Object e) {

          System.out.println("Implementation left as an exercise");
          return true;

     } //public boolean contains(Object e)

     /** Return the element from this list at the specified index */
     public E get(int index) {

          System.out.println("Implementation left as an exercise");
          return null;

     } //public E get(int index)

     /**
      * Return the index of the head matching element in this list.
      * Return -1 if no match.
      */
     public int indexOf(Object e) {

          System.out.println("Implementation left as an exercise");
          return 0;

     } //public int indexOf(Object e)

     /**
      * Return the index of the last matching element in this list
      * Return -1 if no match.
      */
     public int lastIndexOf(Object e) {

          System.out.println("Implementation left as an exercise");
          return 0;

     } //public int lastIndexOf(Object e)

     /**
      * Replace the element at the specified position in this list with the specified element.
      */
     public E set(int index, E e) {

          System.out.println("Implementation left as an exercise");
          return null;

     } //public E set(int index, E e)

     /** Override the iterator method defined in Iterable */
     public java.util.ListIterator<E> iterator() {

          return new LinkedListIterator<E>();

     } //public java.util.ListIterator<E> iterator()

     private class LinkedListIterator<E> implements java.util.ListIterator<E> {

          private Node<E> current = (Node<E>) head;
          int index = 0;

          public LinkedListIterator() {

          } //public LinkedListIterator()

          public LinkedListIterator(int index) {

               if (index < 0 || index > size) {
                    throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
               }

               for (int nextIndex = 0; nextIndex < index; nextIndex++) {
                    current = current.next;
               }

          } //public LinkedListIterator(int index)

          public boolean hasNext() {

               return (current != null);

          } //public boolean hasNext()

          public E next() {

               E e = current.element;
               current = current.next;
               return e;

          } //public E next()

          public void remove() {

               System.out.println("Implementation left as an exercise");

          } //public void remove()

          public void add(E e) {

               System.out.println("Implementation left as an exercise");

          } //public void add(E e)

          public boolean hasPrevious() {

               return current != head;

          } //public boolean hasPrevious()

          public int nextIndex() {

               System.out.println("Implementation left as an exercise");
               return 0;

          } //public int nextIndex()

          public E previous() {

               E e = current.element;
               current = current.previous;
               return e;

          } //public E previous()

          public int previousIndex() {

               System.out.println("Implementation left as an exercise");
               return 0;

          } //public int previousIndex()

          @Override
          public void set(E e) {

               current.element = e;

          } //public void set(E e)

     } //private class LinkedListIterator<E> implements java.util.ListIterator<E>

     public class Node<E> {

          E element;
          Node<E> next;
          Node<E> previous;

          public Node(E o) {
        	  
               element = o;
               
          } //public Node(E o)

     } //public class Node<E>

     public int size() {

          return size;

     } //public int size()

     public ListIterator<E> listIterator(int index) {

          return new LinkedListIterator<E>(index);

     } //public ListIterator<E> listIterator(int index)

} //class TwoWayLinkedList extends java.util.AbstractSequentialList<E>
