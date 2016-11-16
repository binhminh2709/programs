package ru.epatko.substringsearcher;

/**
* Test SubstringSearcher
* @author Mikhail Epatko 
*/

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SubstringSearcherTest {

	@Test
	public void wenGiveStringsThenGetTrue() {

		String string = "aaa bb bbaaa bccvbgasgg  aaba dd dff  gggggg";
		String substring = "cvbgasg";
		
		SubstringSearcher searcher = new SubstringSearcher();
		boolean searcherReturn = searcher.search(substring, string);

		assertThat(searcherReturn, is(true)); 
	}

	@Test
	public void wenGiveStringsThenGetFalse() {

		String string = "aaa bb bbaaa bccvbgasgg  aaba dd dff  gggggg";
		String substring = "cvbgKasg";
		
		SubstringSearcher searcher = new SubstringSearcher();
		boolean searcherReturn = searcher.search(substring, string);
		
		assertThat(searcherReturn, is(false)); 
	}
}