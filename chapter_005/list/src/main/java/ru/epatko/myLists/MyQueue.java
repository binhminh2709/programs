package ru.epatko.myLists;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         16.02.17.
 */
/**
 * @param <E> any type of elements
 */
public class MyQueue<E> extends MyLinkedList<E> {
    /**
     * Inserts the specified element at the end of this que.
     *
     * @param element the item to be added into this que
     */

    public void addLast(E element) {
        add(element);
    }

    /**
     * Retrieves and remove the first element of this que.
     *
     * @return  The first object of this que
     */
    public E getFirst() {
        return removeFirst();
    }
}
