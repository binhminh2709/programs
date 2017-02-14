package ru.epatko.myLists;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         14.02.17.
 *
 * @param <E> type of elements
 */
public interface SimpleContainer<E> extends Iterable {
    /**
     * Add new element to container.
     *
     * @param e new element
     */
    void add(E e);
    /**
     * Get element from container.
     *
     * @param index index of element
     * @return element type E
     */
    E get(int index);
}
