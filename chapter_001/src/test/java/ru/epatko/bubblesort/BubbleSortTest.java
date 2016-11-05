package ru.epatko.bubblesort;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import org.apache.commons.io.output.ByteArrayOutputStream;
import java.io.PrintStream;

public class BubbleSortTest{

	@Test
	public void wenGiveArrayThenGetSortedArray(){

		int [] newArray = new int [3];
		newArray[0] = 3;
		BubbleSort.bubbleSort(newArray);
		assertEquals(3, newArray[2]);
	}

	@Test
	public void wenGiveArrayThenPrintArray(){

		ByteArrayOutputStream output = new ByteArrayOutputStream();
		System.setOut (new PrintStream(output));
		
		int[] array = new int[2];
		array[0] = 1;
		array[1] = 2;
		BubbleSort.printArray(array);
		assertThat(output.toString(), is("1, 2, \b\b  \n"));
	}
}