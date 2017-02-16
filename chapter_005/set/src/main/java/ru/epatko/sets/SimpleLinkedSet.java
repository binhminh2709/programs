package ru.epatko.sets;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         16.02.17.
 *
 * @param <E> - any of elements type
 */
public class SimpleLinkedSet<E> {
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
     * @return {@code true} if element was added
     */

    public boolean add(E element) {
        Iterator<E> itr = new MyIterator<E>();
        boolean contains = false;
        while (itr.hasNext()) {
            if (element.equals(itr.next())) {
                contains = true;
                break;
            }
        }
        if (!contains) {
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
        return !contains;
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
        private int itIndex = 0;

        /**
         * Returns {@code true} if the iteration has more elements.
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return size > this.itIndex;
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
            Node<E> node = this.lastReturned;
            E element = node.current;
            this.lastReturned = node.next;
            this.itIndex++;
            return element;
        }
    }




}
