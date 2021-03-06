package ru.epatko.lists;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         22.03.17
 *
 * @param <E> an element of any type
 */
public class ThreadSafeList <E> implements Iterable {

    /**
     * Pointer to first node.
     */
    private Node<E> first;

    /**
     * Pointer to last node.
     */
    private Node<E> last;

    /**
     * Number of elements in List.
     */
    private int size = 0;

    /**
     * Add new element to container.
     *
     * @param element new element
     */
    public synchronized void add(E element) {

        final Node<E> node = this.last;
        final Node<E> newNode = new Node<>(node, element, null);
        this.last = newNode;
        if (node == null) {
            this.first = newNode;
        } else {
            node.next = newNode;
        }
        this.size++;
    }

    /**
     * Get element from container.
     *
     * @param index index of element
     * @return element of type E
     * @throws IndexOutOfBoundsException exception
     */
    public synchronized E get(int index) throws IndexOutOfBoundsException {
        Node<E> node = this.first;
        if (index >= 0 && index < this.size) {
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        } else {
            throw new IndexOutOfBoundsException();
        }
        return node.current;
    }

    /**
     * Removes and returns the first element from this list.
     *
     * @return the first element from this list
     * @throws NoSuchElementException if this list is empty
     */
    public synchronized E removeFirst() throws NoSuchElementException {
        final Node<E> firsNode = first;
        if (firsNode == null) throw new NoSuchElementException();

        final E element = firsNode.current;
        final Node<E> next = firsNode.next;
        firsNode.current = null;
        firsNode.next = null;
        first = next;
        if (next == null) {
            last = null;
        } else {
            next.prev = null;
        }
        size--;
        return element;
    }

    /**
     * Removes and returns the last element from this list.
     *
     * @return the last element from this list
     * @throws NoSuchElementException if this list is empty
     */
    public synchronized E removeLast() {
        final Node<E> lastNode = last;
        if (lastNode == null) {
            throw new NoSuchElementException();
        }
        final E element = lastNode.current;
        final Node<E> prev = lastNode.prev;
        lastNode.current = null;
        lastNode.prev = null;
        last = prev;
        if (prev == null) {
            first = null;
        } else {
            prev.next = null;
        }
        size--;
        return element;
    }
    /**
     * Node.
     *
     * @param <E> any type of element
     */
    private class Node<E> {
        /**
         * current element.
         */
        E current;
        /**
         * Next node.
         */
        Node<E> next;
        /**
         * Previous node.
         */
        Node<E> prev;

        /**
         * Node.
         *
         * @param prev previous node
         * @param element current element
         * @param next next node
         */
        Node(Node<E> prev, E element, Node<E> next) {
            this.current = element;
            this.next = next;
            this.prev = prev;
        }
    }

    /**
     * Returns an iterator over elements of type {@code E}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<E> iterator() {
        return new MyIterator<E>();
    }

    /**
     * Iterator.
     *
     * @param <E> type of element of MyLinkedList
     */
    private class MyIterator<E> implements Iterator<E> {

        /**
         * Last required node.
         */
        private Node<E> lastReturned = (Node<E>) first;

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
            return size > this.iteratorIndex;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public synchronized E next() throws NoSuchElementException {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Node<E> node = this.lastReturned;
            E element = node.current;
            this.lastReturned = node.next;
            this.iteratorIndex++;
            return element;
        }
    }

}

