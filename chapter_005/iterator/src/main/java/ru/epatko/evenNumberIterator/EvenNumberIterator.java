package ru.epatko.evenNumberIterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Mikhail Epatko (mikhail.epatko@gmail.com).
 *         11.02.17.
 */
public class EvenNumberIterator implements Iterator {

    /**
     * Array.
     */
    private final int[] array;
    /**
     * Array index.
     */
    private int index = 0;

    /**
     * Constructor.
     * @param array - array of integers.
     */
    public EvenNumberIterator(int[] array) {
        this.array = array;
    }


    /**
     * Returns {@code true} if the iteration has more elements.
     * (In other words, returns {@code true} if {@link #next} would
     * return an element rather than throwing an exception.)
     *
     * @return {@code true} if the iteration has more elements.
     */
    @Override
    public boolean hasNext() {

        boolean result = false;

        for (int i = this.index; i < this.array.length; i++) {
            if (this.array[i] != 0 && this.array[i] % 2 == 0) {
                result = true;
                break;
            }
        }

        return result;
    }

    /**
     * Returns the next element in the iteration.
     *
     * @return the next even element in the iteration.
     */
    @Override
    public Object next() throws NoSuchElementException {
        boolean result = false;
        if (this.index >= this.array.length) {
            throw new NoSuchElementException();
        }

        for (int i = this.index; i < this.array.length; i++) {
            if (this.array[i] != 0 && this.array[i] % 2 == 0) {
                this.index = i;
                result = true;
                break;
            }
        }
        if (result) {
            return this.array[this.index++];
        }
        throw new NoSuchElementException();
    }
}
