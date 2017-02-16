package ru.epatko.sets;

import org.junit.*;
import org.junit.rules.ExpectedException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         16.02.17.
 */
public class SimpleLinkedSetTest {

    public SimpleLinkedSet<Integer> sls;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void initialize() {
        sls = new SimpleLinkedSet<>();
        sls.add(1);
        sls.add(2);
    }

    @Test
    public void whenTryAddDuplicateThenGetFalse() {
        assertThat(sls.add(2), is(false));
    }
    @Test
    public void whenTryAddNonDuplicateThenGetTrue() {
        assertThat(sls.add(3), is(true));
    }

    @Test
    public void whenCallIteratorNextThenGetNextElement() {
        Iterator it = sls.iterator();

        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
    }
    @Test
    public void whenCallIteratorOneMoreElementThenGetNoSuchElementException() {
        expectedException.expect(NoSuchElementException.class);
        Iterator it = sls.iterator();

        it.next();
        it.next();
        it.next();
    }
}