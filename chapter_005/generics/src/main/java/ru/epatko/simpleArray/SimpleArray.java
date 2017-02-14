package ru.epatko.simpleArray;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         12.02.17.
 */

/**
 * Simple array.
 *
 * @param <T> any type.
 */
public class SimpleArray<T> {

    /**
     * Array of T elements.
     */
    private Object[] array;
    /**
     * Index position.
     */
    private int index = 0;

    /**
     * Constructor.
     *
     * @param size array size.
     */
    public SimpleArray(int size) {
        this.array = new Object[size];
    }

    /**
     * Add new element to array.
     *
     * @param value  value of new element.
     * @throws ArrayIndexOutOfBoundsException exception.
     */
    public void add(T value) throws ArrayIndexOutOfBoundsException {
        this.array[this.index++] = value;
    }

    /**
     * Get element from array.
     *
     * @param position position of element.
     * @return element from array.
     * @throws ArrayIndexOutOfBoundsException exception.
     * @throws NullPointerException get this exception when call element with a NULL value.
     */
    public T get(int position) throws ArrayIndexOutOfBoundsException, NullPointerException {
        return (T) this.array[position];
    }

    /**
     * Delete element from array and move next elements to previous places.
     *
     * @param position position of element.
     * @throws ArrayIndexOutOfBoundsException exception.
     */
    public void delete(int position) throws ArrayIndexOutOfBoundsException {
        this.array[position] = null;
        for (int i = position + 1; i < this.array.length; i++) {
            if (this.array[i] != null) {
                this.array[i - 1] = this.array[i];
                this.array[i] = null;
                this.index = i;
            }
        }
    }

    /**
     * Update element in array.
     *
     * @param position position of element.
     * @param newValue new value of element.
     * @throws ArrayIndexOutOfBoundsException exception.
     */
    public void update(int position, T newValue) throws ArrayIndexOutOfBoundsException {
        if (this.array[position] != null) {
            this.array[position] = newValue;
        } else {
            System.out.println("You can't update element with a NULL value. To do it use add() method.");
        }
    }
}
