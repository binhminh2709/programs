package ru.epatko.simpleArray;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         12.02.17.
 */
public class SimpleArrayTest {

    @Test
    public void whenDeleteElementThenNextElementsMovesToPreviousPlaces() {
        SimpleArray<Integer> array = new SimpleArray<Integer>(3);
        array.add(1);
        array.add(2);
        array.add(3);
        array.delete(0);
        assertThat(array.get(0), is(2));
        assertThat(array.get(1), is(3));
    }
    @Test
    public void whenUpdateElementThenCanGetUpdatedValue() {
        SimpleArray<Integer> array = new SimpleArray<Integer>(2);
        array.add(1);
        array.add(2);
        array.update(0, 5);
        assertThat(array.get(0), is(5));
    }
}