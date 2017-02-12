package ru.epatko.simpleArray;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         12.02.17.
 */

/**
 * Store.
 *
 * @param <T> any type
 */
public interface Store<T extends Base> {
    /**
     * Add element.
     *
     * @param element new element to adding
     */
    void add(T element);

    /**
     * Delete element.
     * @param id ID of element to deleting
     */
    void delete(String id);

    /**
     * Update element.
     *
     * @param id ID of element to update
     * @param newElement new element
     */
    void update(String id, T newElement);

    /**
     * Get element from storage.
     *
     * @param id - ID of element
     * @return type T element
     */
    T get(String id);
}
