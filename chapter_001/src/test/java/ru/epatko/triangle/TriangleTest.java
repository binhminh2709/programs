package ru.epatko;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


public class TriangleTest{

	@Test
	public void whenSetTheRightPointsThenGetTheArea(){
					
		Point first = new Point (0, 0);
		Point second = new Point (3, 0);
		Point third = new Point (3, 4);
				
		Triangle triangle = new Triangle(first, second, third);
		
		assertEquals(6, triangle.area(), 1e-6);

	}

/*	@Test
	public void whenWaitWrongAreaThenGetError(){
					
		Point first = new Point (0, 0);
		Point second = new Point (3, 0);
		Point third = new Point (3, 4);
				
		Triangle triangle = new Triangle(first, second, third);
		
		assertEquals(7, triangle.area(), 1e-6);

	}
*/
	@Test
	public void whenSetTheWrongPointThenGetErrorCode(){
					
		Point first = new Point (0, 0);
		Point second = new Point (3, 0);
		Point third = new Point (4, 0);
				
		Triangle triangle = new Triangle(first, second, third);
		
		assertEquals(-1, triangle.area(), 1e-6);
	}

	@Test
	public void whenSetThePointsThenGetALenghtOfSide(){
					
		Point first = new Point (0, 0);
		Point second = new Point (3, 0);
		assertEquals(3, first.distanceTo(second), 1e-6);
	}
		
}		