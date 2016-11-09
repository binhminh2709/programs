package ru.epatko.matrixrotator;

/**
* Test MatrixRotator
* @autor Mikhail Epatko 
*/

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class MatrixRotatorTest{

	@Test
	public void whenGiveArrayThenGetRotatedArray(){

/**
* Create test array
*/
		int[][] array = {{0, 1}, {2, 3}};
		
/**
* Create etalon array
*/
		int[][] etalonArray = {{1, 3}, {0, 2}};

/**
* Rotate test array
*/
		MatrixRotator rotator = new MatrixRotator();
		rotator.rotate(array);
/**
* Compare rotated test array and etalon array
*/
		assertThat(array, is(etalonArray));
	}	
}
