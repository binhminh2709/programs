package ru.epatko.evenNumberIterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         12.02.17.
 */
public class ArrayIterator implements Iterator<Integer> {

    /**
     * Array of integers.
     */
    private final int[] array;
    /**
     * Current index of array.
     */
    private int index = 0;

    /**
     * Constructor.
     * @param array - array of integers.
     */
    public ArrayIterator(final int[] array) {
        this.array = array;
    }

    /**
     * Returns {@code true} if the iteration has more elements.
     * (In other words, returns {@code true} if {@link #next} would
     * return an element rather than throwing an exception.)
     *
     * @return {@code true} if the iteration has more elements
     */
    @Override
    public boolean hasNext() {
        return this.array.length > this.index;
    }

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     * @throws NoSuchElementException if the iteration has no more elements
     */
    @Override
    public Integer next() throws NoSuchElementException {
        return this.array[index++];
    }
}
