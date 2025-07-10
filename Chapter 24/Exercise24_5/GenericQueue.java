package okLetsDoThis;

import java.util.LinkedList;

public class GenericQueue<E> extends LinkedList{
		
	public GenericQueue() {
		
		//I believe this is known as a No-Arg Constructor
		
	}
	
	public GenericQueue(E[] list) {
		
		for (E thing : list) {
						
			this.add(thing);
			
		}
		
	}
	
	public void enqueue(E e) {

		this.addLast(e);

	}
    
	public E dequeue() {

		return (E) this.removeFirst();

	}
   
	public void test(E e) {
		
		System.out.println("This is the GenericQueue: " + this.toString());
		this.enqueue(e);
		System.out.println("This is the GenericQueue after using the enqueue(E e) method with \"" + e.toString() + "\", like you said: " + this.toString());
		this.dequeue();
		System.out.println("This is the GenericQueue after using the dequeue() method : " + this.toString());
		
	}
    
	@Override
	public String toString() {

		String theQueue = "Queue: ";
		
		for (int i = 0; i < this.size(); i++) {
			
			theQueue = theQueue + (this.get(i).toString() + ", ");
			
		}
		
		return theQueue;

	}
	
}
