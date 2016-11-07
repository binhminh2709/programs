package ru.epatko.arraymerger;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ArrayMergerTest {

	@Test
	public void wenGiveTwoDifferentArraysThenGetOneMergedArray() {

		ArrayMerger merger = new ArrayMerger();
		int [] arrayA = {3, 5};
		int [] arrayB = {2, 4};
		int [] arrayC = new int[4];

		merger.merge(arrayA, arrayB, arrayC);
		assertEquals(4, arrayC[2]);
	}
}