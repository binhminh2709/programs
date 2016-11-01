package ru.epatko;

public class StartTriangle{
	
	public static void main (String[] args){
		
		Point first = new Point (0, 0);
		Point second = new Point (3, 0);
		Point third = new Point (3, 4);
				
		Triangle triangle = new Triangle(first, second, third);
		System.out.println(triangle.area());
	}
	
}  