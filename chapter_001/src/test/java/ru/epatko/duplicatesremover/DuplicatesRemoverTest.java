package ru.epatko.duplicatesremover;

/**
* Tester
* @autor Mikhail Epatko
*/
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class DuplicatesRemoverTest {

	@Test
	public void wenGiveArrayWithDuplicatesThenGetArrayWithoutDuplicates() {

/**
* Create uncleaned test array
*/
		String[] array = {"aaa", "bbb", "ccc", "aaa", "bbb", "aa", "ccc", "c", "aab"};
		
/**
* Create etalon array
*/
		String[] etalonArray = {"aaa", "bbb", "ccc", "", "", "aa", "", "c", "aab"};
		
/**
* Clean test array
*/
		DuplicatesRemover remover = new DuplicatesRemover();
		remover.removeDuplicates(array);
/**
* Compare cleaned test array and etalon array
*/
		assertThat(array, is(etalonArray)); 
	}	
}