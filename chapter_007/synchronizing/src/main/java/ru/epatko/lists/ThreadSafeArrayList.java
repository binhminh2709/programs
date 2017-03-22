package ru.epatko.lists;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         22.03.17
 *
 * @param <E> an element of any type
 */
public class ThreadSafeArrayList <E> implements Iterable {
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
    public ThreadSafeArrayList() {
        this.array = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Constructor.
     *
     * @param size array size
     */
    public ThreadSafeArrayList(int size) {
        this.array = new Object[size];
    }

    /**
     * Add new element to array.
     *
     * @param element  value of new element
     * @throws IndexOutOfBoundsException exception
     */
    public synchronized void add(E element) throws IndexOutOfBoundsException {
        if (this.index == this.array.length) {
            grow();
        }
        this.array[this.index++] = element;
    }

    /**
     *  Increases the capacity.
     */
    private synchronized void grow() {
        int newLength = this.array.length + DEFAULT_CAPACITY;
        this.array = Arrays.copyOf(this.array, newLength);
    }

    /**
     * Get element from container.
     *
     * @param index index of element
     * @return element type E
     * @throws IndexOutOfBoundsException exception
     */
    public synchronized E get(int index) throws IndexOutOfBoundsException {
        rangeCheck(index);
        return (E) this.array[index];
    }

    /**
     * Checks if the given index is in range.
     *
     * @param index index of element of MyArrayList
     */
    private synchronized void rangeCheck(int index) {
        if (index >= this.array.length) {
            throw new IndexOutOfBoundsException("Wrong index.");
        }
    }

    /**
     * Returns an iterator over elements of type {@code E}.
     *
     * @return an Iterator.
     */
    @Override
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
        public synchronized boolean hasNext() {
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
            synchronized (this) {
                if (this.iteratorIndex == array.length) {
                    throw new NoSuchElementException();
                }
                return (E) array[this.iteratorIndex++];
            }
        }
    }

}
