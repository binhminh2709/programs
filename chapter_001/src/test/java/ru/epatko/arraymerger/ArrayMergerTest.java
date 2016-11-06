package ru.epatko.arraymerger;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import org.apache.commons.io.output.ByteArrayOutputStream;
import java.io.PrintStream;

public class ArrayMergerTest{

	@Test
	public void wenGiveTwoDifferentArraysThenGetOneMergedArray(){

		Copier copier = new Copier();
		int [] arrayA = {5, 3};
		int [] arrayB = {2, 4};
		int [] arrayC = new int[4];

		copier.copy(arrayA, arrayB, arrayC);
		assertEquals(4, arrayC[3]);
	}
}