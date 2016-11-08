package ru.epatko.bubblesorter;

/**
* Test BubbleSorter
* @autor Mikhail Epatko 
*/
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class BubbleSorterTest{

	@Test
	public void wenGiveUnsortedArrayThenGetSortedArray() {

/**
* Create unsorted test array
*/
		int[] array = {3, 1, 2};
		
/**
* Create etalon array
*/
		int[] etalonArray = {1, 2, 3};
		
/**
* Sort test array
*/
		BubbleSorter sorter = new BubbleSorter();
		sorter.sort(array);
/**
* Compare sorted test array and etalon array
*/
		assertThat(array, is(etalonArray)); 
	}	
}