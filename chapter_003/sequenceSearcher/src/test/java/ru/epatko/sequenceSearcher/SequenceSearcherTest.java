package ru.epatko.sequenceSearcher;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         23.01.17.
 */
public class SequenceSearcherTest {
    @Test
    public void whenSetSourceArrayThenGetResultArray() throws Exception {
        SequenceSearcher searcher = new SequenceSearcher();
        int[] test = {3, 5};
        int[] result = searcher.search(new int[]{1,1,1,2,2,3,4,4,4,1,1,2,2,2,2,6,6,6,7,7,8,8,8,1,1,1,2,3,3,3,3,3});
        assertThat(result, is(test));
    }
}