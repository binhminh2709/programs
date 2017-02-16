package ru.epatko.sets;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         16.02.17.
 */
public class SimpleSetArrayTest {

    SimpleSetArray<Integer> ssa;

    @Before
    public void initialize() {
        ssa = new SimpleSetArray<>();
        ssa.add(1);
        ssa.add(2);
        ssa.add(3);
    }


    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void whenTryAddDuplicateThenGetFalse() {
        assertThat(ssa.add(2), is(false));
    }
    @Test
    public void whenTryAddNonDuplicateThenGetTrue() {
        assertThat(ssa.add(4), is(true));
    }

    @Test
    public void whenCallIteratorNextThenGetNextElement() {
        Iterator it = ssa.iterator();

        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
    }
    @Test
    public void whenCallIteratorOneMoreElementThenGetNoSuchElementException() {
        expectedException.expect(NoSuchElementException.class);
        Iterator it = ssa.iterator();

        it.next();
        it.next();
        it.next();
        it.next();
    }
}