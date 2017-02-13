package ru.epatko.evenNumberIterator;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Iterator;
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
        EvenNumberIterator it = new EvenNumberIterator(new int[] {0, 1, 2, 3, 4, 5});

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

        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(7));
        assertThat(it.hasNext(), is(false));
    }
    @Test
    public void whenCallIteratorMoreThanItHasShouldGetNoSuchElementException() {

        expectedException.expect(NoSuchElementException.class);

        SimpleNumberIterator it = new SimpleNumberIterator(new int[] {0, 1, 4, 6, 8, 9});

        assertThat(it.hasNext(), is(false));
        it.next();
    }
    @Test
    public void whenCallForthElementThenGetIt() {
        Iterator<Integer> iteratorOne = new ArrayIterator(new int[] {1, 2, 3});
        Iterator<Integer> iteratorTwo = new ArrayIterator(new int[] {4, 5, 6});
        Iterator<Integer> iteratorThree = new ArrayIterator(new int[] {7, 8, 9});
        Iterator[] arrayOfIterators = new  Iterator[] {iteratorOne, iteratorTwo, iteratorThree};
        Iterator<Iterator<Integer>> iteratorOfIterators = new IteratorIterator(arrayOfIterators);

        Converter converter = new Converter();
        Iterator<Integer> iterator = converter.convert(iteratorOfIterators);

        iterator.next();
        iterator.next();
        iterator.next();
        assertThat(iterator.next(), is(4));
    }
    @Test
    public void whenCallOneMoreElementAfterLastThenGetNoSuchElementException() {

        expectedException.expect(NoSuchElementException.class);

        Iterator<Integer> iteratorOne = new ArrayIterator(new int[] {1, 2});
        Iterator<Integer> iteratorTwo = new ArrayIterator(new int[] {4});
        Iterator[] arrayOfIterators = new  Iterator[] {iteratorOne, iteratorTwo};
        Iterator<Iterator<Integer>> iteratorOfIterators = new IteratorIterator(arrayOfIterators);

        Converter converter = new Converter();
        Iterator<Integer> iterator = converter.convert(iteratorOfIterators);

        assertThat(iterator.next(), is(1));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.next(), is(4));
        iterator.next();
    }

    @Test
    public void whenAskIfHasOneMoreElementAfterLastThenGetFalse() {

        Iterator<Integer> iteratorOne = new ArrayIterator(new int[] {1, 2});
        Iterator<Integer> iteratorTwo = new ArrayIterator(new int[] {4});
        Iterator[] arrayOfIterators = new  Iterator[] {iteratorOne, iteratorTwo};
        Iterator<Iterator<Integer>> iteratorOfIterators = new IteratorIterator(arrayOfIterators);

        Converter converter = new Converter();
        Iterator<Integer> iterator = converter.convert(iteratorOfIterators);

        assertThat(iterator.next(), is(1));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.next(), is(4));
        assertThat(iterator.hasNext(), is(false));
    }
}