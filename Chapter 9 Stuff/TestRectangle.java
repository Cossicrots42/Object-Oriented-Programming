public class TestRectangle {
	public static void main(String[] args) {
		Rectangle rect2 = new Rectangle(4, 40);
		Rectangle rect3 = new Rectangle(3.5, 35.9);
		
		System.out.println("Rectangle 1");
		System.out.println("Width: " + rect2.width);
		System.out.println("Height: " + rect2.height);
		System.out.println("Area: " + rect2.getArea());
		System.out.println("Perimeter: " + rect2.getPerimeter());
		
		System.out.println("Rectangle 2");
		System.out.println("Width: " + rect3.width);
		System.out.println("Height: " + rect3.height);
		System.out.println("Area: " + rect3.getArea());
		System.out.println("Perimeter: " + rect3.getPerimeter());
	}
}