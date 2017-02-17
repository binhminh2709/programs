package ru.epatko.sets;

import org.junit.*;
import org.junit.rules.ExpectedException;
import java.util.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         17.02.17.
 */
public class SpeedSimpleSetTest {

    SpeedSimpleSet<Integer> sss;
    Iterator<Integer> itr;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void initialize() {
        sss = new SpeedSimpleSet<>();
        itr = sss.iterator();
        sss.add(1);
        sss.add(100);
        sss.add(200);
    }

    @Test
    public void whenAddElementsThenCanGetThem() {

        assertThat(itr.next(), is(1));
        assertThat(itr.next(), is(100));
        assertThat(itr.next(), is(200));
    }

    @Test
    public void whenTryAddDuplicatesThenGetFalse() {
        assertThat(sss.add(1), is(false));
        assertThat(sss.add(100), is(false));
        assertThat(sss.add(200), is(false));
    }

    @Test
    public void whenTryAddNonDuplicateThenGetTrue() {
        assertThat(sss.add(256), is(true));
    }

    @Test
    public void whenCallIteratorOneMoreElementThenGetNoSuchElementException() {
        expectedException.expect(NoSuchElementException.class);

        itr.next();
        itr.next();
        itr.next();
        itr.next();
    }
}