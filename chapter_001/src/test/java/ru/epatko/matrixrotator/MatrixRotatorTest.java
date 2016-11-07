package ru.epatko.matrixrotator;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class MatrixRotatorTest{

	@Test
	public void wenGiveArrayThenGetRotatedArray(){

		int[][] array = {{0,1}, {2,3}};

		MatrixRotator rotator = new MatrixRotator();
		rotator.rotate(array);
		assertEquals(3, array[0][1]);
	}	
}