package okLetsDoThis;

public class test {
	
	public static void main (String[] Args) {
		
//		String[] cats = {"Figaro", "Taj", "Scout", "Lizzy", "Juniper", "Mimi", "Bobo"};
		String[] exampleData = {"zeroth", "one", "two", "three", "four"};
		
		GenericQueue Testable = new GenericQueue(exampleData);
		
		String thing = "five";
		
		Testable.test(thing);
		
	}

}
