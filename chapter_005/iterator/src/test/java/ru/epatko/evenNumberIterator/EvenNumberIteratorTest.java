package ru.epatko.evenNumberIterator;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import java.util.NoSuchElementException;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         11.02.17.
 */
public class EvenNumberIteratorTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void whenCallIteratorNextThenGetNextEvenNumber() {
        EvenNumberIterator it = new EvenNumberIterator(new int[] {0, 1, 2, 3, 4});

        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(false));
    }
    @Test
    public void whenCallIteratorOneMoreElementThenGetNoSuchElementException() {

        expectedException.expect(NoSuchElementException.class);

        EvenNumberIterator it = new EvenNumberIterator(new int[] {0, 1, 2, 3});

        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        it.next();
    }

    @Test
    public void whenCallIteratorNextThenGetNextSimpleNumber() {
        SimpleNumberIterator it = new SimpleNumberIterator(new int[] {0, 1, 2, 4, 6, 7, 8, 9});

        assertThat(it.next(), is(2));
        assertThat(it.next(), is(7));
    }
    @Test
    public void whenCallIteratorMoreThenGetNoSuchElementException() {

        expectedException.expect(NoSuchElementException.class);

        SimpleNumberIterator it = new SimpleNumberIterator(new int[] {0, 1, 4, 6, 8, 9});

        it.next();
    }
}