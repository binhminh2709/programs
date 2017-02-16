package ru.epatko.sets;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         16.02.17.
 *
 * @param <E> - any of elements type
 */
public class SimpleSetArray<E> {


    /**
     * Default initial capacity.
     */
    private static final int DEFAULT_CAPACITY = 100;

    /**
     * Array of elements type E.
     */
    private Object[] array;

    /**
     * Index position.
     */
    private int index = 0;

    /**
     * Default constructor.
     */
    public SimpleSetArray() {
        this.array = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Add new element to this set.
     *
     * @param element  value of new element
     * @return {@code true} if element was added
     */

    public boolean add(E element) {
        if (this.index == this.array.length) {
            grow();
        }
        boolean contains = false;
        for (int i = 0; i < this.array.length; i++) {
            if (element.equals(this.array[i])) {
                contains = true;
                break;
            }
        }
        if (!contains) {
            this.array[this.index++] = element;
        }
        return !contains;
    }
    /**
     *  Increases the capacity.
     */
    private void grow() {
        int newLength = this.array.length + DEFAULT_CAPACITY;
        this.array = Arrays.copyOf(this.array, newLength);
    }

    /**
     * Returns an iterator over elements of type {@code E}.
     *
     * @return an Iterator.
     */
    public Iterator iterator() {
        return new MyIterator<E>();
    }

    /**
     * Iterator.
     *
     * @param <E> type o felement of MyArrayList
     */
    private class MyIterator<E> implements Iterator<E> {

        /**
         * Iterator index.
         */
        private int iteratorIndex = 0;

        /**
         * Returns {@code true} if the iteration has more elements.
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return (array.length > this.iteratorIndex && array[this.iteratorIndex] != null);
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public E next() throws NoSuchElementException {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return (E) array[this.iteratorIndex++];
        }
    }


}
