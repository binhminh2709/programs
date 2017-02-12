package ru.epatko.evenNumberIterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         12.02.17.
 */
public class IteratorIterator implements Iterator<Iterator<Integer>> {

    /**
     * Array of iterators of integers.
     */
    private final Iterator<Integer>[] iterators;
    /**
     * Current index of array.
     */
    private int index = 0;

    /**
     * Constructor.
     * @param iterators - array of iterators of integers.
     */
    public IteratorIterator(final Iterator<Integer>[] iterators) {
        this.iterators = iterators;
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
        return iterators.length > index;
    }

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     * @throws NoSuchElementException if the iteration has no more elements
     */
    @Override
    public Iterator<Integer> next() throws NoSuchElementException {
        return this.iterators[this.index++];
    }
}
