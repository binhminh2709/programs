package ru.epatko.evenNumberIterator;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         11.02.17.
 */
public class EvenNumberIteratorTest {
    @Test
    public void next() {
        EvenNumberIterator it = new EvenNumberIterator(new int[] {0,1,2,3,4});

        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(false));
    }
}