package ru.epatko.arraymerger;

/**
* Test ArrayMerger
* @author Mikhail Epatko 
*/

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ArrayMergerTest {

	@Test
	public void wenGiveTwoDifferentArraysThenGetOneMergedArray() {

/**
* Create test arrays
*/
		int[] arrayA = {3, 5};
		int[] arrayB = {2, 4};
		int[] arrayC = new int[4];

/**
* Create etalon array
*/
		int[] etalonArray = {2, 3, 4, 5};

/**
* Merge test arrays
*/
		ArrayMerger merger = new ArrayMerger();
		merger.merge(arrayA, arrayB, arrayC);

/**
* Compare merged arrays and etalon array
*/
		assertThat(arrayC, is(etalonArray));
	}
}