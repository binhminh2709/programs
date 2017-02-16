package ru.epatko.myLists;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         16.02.17.
 */
/**
 * @param <E> any type of elements
 */
public class MyStack<E> extends MyLinkedList<E> {

    /**
     * Pushes an element onto the top of this stack.
     *
     * @param   element   the item to be pushed onto this stack.
     */
    public void push(E element) {
        add(element);
    }

    /**
     * Removes the object at the top of this stack and returns that
     * object as the value of this function.
     *
     * @return  The object at the top of this stack
     */
    public E pop() {
        return removeLast();
    }
}
