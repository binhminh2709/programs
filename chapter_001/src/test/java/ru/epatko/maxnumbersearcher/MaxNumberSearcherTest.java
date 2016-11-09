package ru.epatko.maxnumbersearcher;

/**
* Test MaxNumberSarcher
* @autor Mikhail Epatko
*/

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class MaxNumberSearcherTest {

	@Test
	public void whenGiveNumbersThanGetMaxOfThem() {

		MaxNumberSearcher searcher = new MaxNumberSearcher();
		assertThat(searcher.searchMaxNumber(2.5, 3.76, 1.1), is(3.76));
	}
}