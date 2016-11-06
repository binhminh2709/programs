package ru.epatko.bubblesort;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import org.apache.commons.io.output.ByteArrayOutputStream;
import java.io.PrintStream;

public class BubbleSortTest{

	@Test
	public void wenGiveUnsortedArrayThenGetSortedArray(){

		Sorter sorter = new Sorter();
		int [] newArray = {0, 0, 3};
		sorter.sort(newArray);
		assertEquals(3, newArray[2]);
	}

	@Test
	public void wenGiveArrayThenPrintArray(){

		ByteArrayOutputStream output = new ByteArrayOutputStream();
		System.setOut (new PrintStream(output));
		
		Printer printer = new Printer();
		int[] newArray = {1, 2};
		printer.print(newArray);
		assertThat(output.toString(), is("1, 2, \b\b  \n"));
	}
}