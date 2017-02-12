package ru.epatko.evenNumberIterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         12.02.17.
 */
public class Converter implements Iterator<Integer> {

    /**
     * Iterator of iterators of integers.
     */
    private Iterator<Iterator<Integer>> iteratorOfIterators;
    /**
     * Current iterator to call next value.
     */
    private Iterator<Integer> currentIterator = null;

    /**
     * Convert iterator of iterators.
     *
     * @param iteratorOfIterators - iterator of iterators.
     * @return - iterator of integers.
     */
    public Iterator<Integer> convert(Iterator<Iterator<Integer>> iteratorOfIterators) {
        this.iteratorOfIterators = iteratorOfIterators;
        return this;
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
        boolean result;
        if (currentIterator != null && currentIterator.hasNext()) {
            result = true;
        } else if (iteratorOfIterators.hasNext() && (currentIterator = iteratorOfIterators.next()).hasNext()) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     * @throws NoSuchElementException if the iteration has no more elements
     */
    @Override
    public Integer next() throws NoSuchElementException {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return currentIterator.next();
    }
}
