package ru.epatko.bubblesorter;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class BubbleSorterTest{

	@Test
	public void wenGiveUnsortedArrayThenGetSortedArray(){

		BubbleSorter sorter = new BubbleSorter();
		int [] newArray = {3, 0, 0};
		sorter.sort(newArray);
		assertEquals(3, newArray[2]);
	}	
}