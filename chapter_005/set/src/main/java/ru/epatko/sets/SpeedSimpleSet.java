package ru.epatko.sets;

import ru.epatko.myLists.MyLinkedList;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         17.02.17.
 */
public class SpeedSimpleSet<E> {

    /**
     * Default initial capacity.
     */
    private static final int DEFAULT_CAPACITY = 16;

    /**
     * Array of elements type E.
     */
    private MyLinkedList[] groups;

    /**
     * Index position.
     */
    private int index = 0;

    /**
     * Default constructor.
     */
    public SpeedSimpleSet() {
        this.groups = new MyLinkedList[DEFAULT_CAPACITY];
    }

    /**
     * Constructor.
     *
     * @param groups groups number
     */
    public SpeedSimpleSet(int groups) {
        this.groups = new MyLinkedList[groups];
    }

    /**
     * Add new element to this set.
     *
     * @param element  value of new element
     * @return {@code true} if element was added
     */

    public boolean add(E element) {
        boolean contains = false;
        int groupNumber = Math.abs(element.hashCode() / this.groups.length);
        while (groupNumber > groups.length - 1) {
            groupNumber /= groups.length;
        }
        if (this.groups[groupNumber] == null) {
            this.groups[groupNumber] = new MyLinkedList();
        }
        Iterator<E> itr = this.groups[groupNumber].iterator();

        while (itr.hasNext()) {
            if (element.equals(itr.next())) {
                contains = true;
                break;
            }
        }
        if (!contains) {
            this.groups[groupNumber].add(element);
        }
        return !contains;
    }

    /**
     * Returns an iterator over elements of type {@code E}.
     *
     * @return an Iterator.
     */
    public Iterator iterator() {
        return new SpeedSimpleSet<E>.MyIterator<E>();
    }

    /**
     * Iterator.
     *
     * @param <E> any types
     */
    private class MyIterator<E> implements Iterator<E> {

        /**
         * Iterator index.
         */
        private int iteratorIndex = 0;

        /**
         * Temp iterator.
         *
         * @param <E> any type of objects
         */
        private Iterator<E> tempIterator;

        private boolean iteratorCreated = false;

        /**
         * Returns {@code true} if the iteration has more elements.
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            boolean has = false;
            if (groups.length > this.iteratorIndex && groups[this.iteratorIndex] != null) {
                if (this.iteratorCreated == false) {
                    tempIterator = groups[this.iteratorIndex].iterator();
                    this.iteratorCreated = true;
                }
                if (tempIterator.hasNext()) {
                    has = true;
                }
            }
            return has;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public E next() throws NoSuchElementException {
            E element = null;
            while (this.iteratorIndex <= groups.length) {
                if (hasNext()) {
                    element = tempIterator.next();
                    break;
                } else if (this.iteratorIndex < groups.length) {
                    this.iteratorIndex++;
                    this.iteratorCreated = false;
                } else {
                    throw new NoSuchElementException();
                }
            }
            return element;
        }
    }
}
